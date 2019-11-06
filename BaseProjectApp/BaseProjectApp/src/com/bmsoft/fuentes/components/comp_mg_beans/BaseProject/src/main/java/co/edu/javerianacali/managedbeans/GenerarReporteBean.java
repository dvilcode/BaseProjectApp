package $packageName.managedbeans;

import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.utils.Parameters;

@ManagedBean(name = "GenerarReporteBean")
@SessionScoped
public class GenerarReporteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger bitacora = Logger.getRootLogger();

	@ManagedProperty(value = "#{JaverianaExceptionService}")
	private IJaverianaExceptionService javerianaExceptionService;

	@ManagedProperty(value = "#{ParametroBean}")
	private ParametroBean objParametroBean;

	/**
	 * Metodo para generar reporte publicado en el JasperServer
	 * 
	 * @param codigoParametroReporte
	 *            codigo en la tabla ParametrosXEmpresa que define la ruta del
	 *            reporte en el JasperServer
	 * @param parametros
	 *            HashMap con los parametros que se le deben enviar al reporte
	 * @return StreamedContent el archivo a ser descargado utilziando el
	 *         componente p:fileDownload de Primefaces
	 */
	public StreamedContent getFile(String nombreReporte, HashMap<String, String> parametros, String nombreArchivo, String mimeArchivo) {
		StringBuilder urlJasperServer = new StringBuilder();
		URL url = null;
		URLConnection conn = null;
		StreamedContent archivoReporte = null;
		try {
			urlJasperServer.append(objParametroBean.getParametro(Parameters.URL_JASPER_SERVER));

			// Se agrega el formato del archivo
			urlJasperServer.append("&output=" + mimeArchivo);
			// se agrega el nombre del reporte
			urlJasperServer.append("&reporte=" + nombreReporte);
			// Adicionamos los parametros si el reporte los tiene
			if (parametros != null) {
				for (Map.Entry<String, String> entry : parametros.entrySet()) {
					// Se adiciona cada parametro a la ruta
					urlJasperServer.append("&" + entry.getKey() + "=" + entry.getValue());
				}
			}

			// Invocamos la URL para obtener el archivo del reporte
			url = new URL(urlJasperServer.toString());
			conn = url.openConnection();
			archivoReporte = new DefaultStreamedContent(conn.getInputStream(), Parameters.PREFIJO_MIME + mimeArchivo, nombreArchivo);
		} catch (Exception e) {
			bitacora.error("Error en GenerarReporteBean.getFile por:" + e.getMessage());
		}

		return archivoReporte;
	}

	public void setJaverianaExceptionService(IJaverianaExceptionService javerianaExceptionService) {
		this.javerianaExceptionService = javerianaExceptionService;
	}

	public void setObjParametroBean(ParametroBean objParametroBean) {
		this.objParametroBean = objParametroBean;
	}

}
