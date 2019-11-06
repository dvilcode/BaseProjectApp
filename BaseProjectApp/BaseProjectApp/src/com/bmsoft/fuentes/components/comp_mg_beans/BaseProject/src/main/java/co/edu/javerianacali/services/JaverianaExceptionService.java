package co.edu.javerianacali.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import $packageName.exception.JaverianaException;
import $packageName.interfaces.persistence.IGenericDAO;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;

/**
 * @author wilferac
 * 
 */
@Service("JaverianaExceptionService")
public class JaverianaExceptionService implements IJaverianaExceptionService {

	private static final Logger bitacora = Logger.getRootLogger();

	@Autowired
	private IGenericDAO genericDao;

	@Override
	public JaverianaException throwException(String codigo, String[] dato) {
		JaverianaException exc = new JaverianaException(codigo, dato);
		try {
			String query = "select codigo, descripcion, solucion, soporte from puj_sysadm.excepciones_app where codigo = :codigo";
			exc = (JaverianaException) genericDao.findByPK(exc, query);
			// Si no se encuentra la excepcion el codigo no existe
			if (exc == null) {
				exc = new JaverianaException();
				exc.setDescripcion(Parameters.DESCRIPCION_EXCEPCION_CODIGO);
				exc.setSolucion(Parameters.SOLUCION_EXCEPCION_CODIGO);
				String[] datos = new String[1];
				datos[0] = codigo;
				exc.setDato(datos);
				exc.loadData();
				return exc;
			}
			exc.setDato(dato);
			exc.loadData();
		} catch (Exception e) {
			bitacora.error("JaverianaException.throwException. Causa: " + e.getMessage() + ".");
			exc.setDescripcion(Parameters.DESCRIPCION_EXCEPCION);
			exc.setSolucion(Parameters.SOLUCION_EXCEPCION);
		}
		return exc;
	}

}
