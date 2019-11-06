package $packageName.exception;

import java.io.Serializable;

import org.apache.log4j.Logger;

import co.edu.javerianacali.utils.Parameters;

public class JaverianaException extends Exception implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getRootLogger();
	/**
	 * Codigo del Exception
	 */
	private String codigo;
	/**
	 * Descripcion del Exception
	 */
	private String descripcion;
	/**
	 * Solucion sugerida al Exception
	 */
	private String solucion;
	/**
	 * Descripcion mas detallada del Exception para el personal de soporte
	 */
	private String soporte;
	/**
	 * Datos para el reemplazo de los comodines en la descripcion del error
	 */
	private String[] dato;
	/**
	 * Objeto referencia que causo la Exception
	 */
	private Object referencia;

	public JaverianaException() {
	}
	
	/**
	 * Usar cuando se quiera referenciar al objecto de causo la Exception
	 * @param referencia
	 * @param descripcion
	 */
	public JaverianaException(Object referencia, String descripcion) {
		this.referencia = referencia;
		this.descripcion = descripcion;
	}

	/**
	 * Construir a parte de un Exception
	 * 
	 * @author wilferac
	 * @since
	 * @param e
	 */
	public JaverianaException(Exception e) {
		e.printStackTrace();
		codigo = Parameters.CODIGO_EXCEPCION;
		descripcion = Parameters.DESCRIPCION_EXCEPCION;
		solucion = Parameters.SOLUCION_EXCEPCION;
	}

	/**
	 * @author wilferac
	 * @since
	 * @param codigo
	 *            codigo del error
	 * @param dato
	 *            datos para el reemplazo de comodines en la descripcion
	 * @throws Exception
	 */
	public JaverianaException(String codigo, String[] dato) {
		this.codigo = codigo;
		this.dato = dato;
	}

	/**
	 * Reemplazar datos en la descripcion
	 * 
	 * @author wilferac
	 * @since
	 */
	public void loadData() {
		// Buscamos si hay datos para asignarle al mensaje
		if (dato != null) {
			for (int i = 0; i < dato.length; i++) {
				if (descripcion.indexOf("$" + (i + 1)) != -1) {
					descripcion = descripcion.replaceAll("\\$" + (i + 1), dato[i]);
				}
			}
		}

		logException();
	}

	/** Setters and Getters **/
	public String[] getDato() {
		return dato;
	}

	public void setDato(String[] dato) {
		this.dato = dato;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}

	public void setSoporte(String soporte) {
		this.soporte = soporte;
	}

	public void logException() {
		logger.error("ERROR " + codigo + ": " + descripcion + " " + solucion);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getSolucion() {
		return solucion;
	}

	public String getSoporte() {
		return soporte;
	}

	public Object getReferencia() {
		return referencia;
	}
	
	
}
