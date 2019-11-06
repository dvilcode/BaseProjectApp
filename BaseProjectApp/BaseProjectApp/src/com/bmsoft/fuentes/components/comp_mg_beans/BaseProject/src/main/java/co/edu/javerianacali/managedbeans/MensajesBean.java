package $packageName.managedbeans;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import $packageName.exception.JaverianaException;
import $packageName.utils.Parameters;

@ManagedBean(name = "mensajesBean")
@SessionScoped
public class MensajesBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int MENSAJE_OK = 1;
	public static final int MENSAJE_ERROR = 2;
	public static final int MENSAJE_ALERTA = 3;

	/**
	 * Mostrar mensaje especificando el tipo
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param mensaje
	 *            el msg a mostrar
	 * @param titulo
	 *            el titulo del msg
	 * @param tipoMensaje
	 *            el tipo del msg
	 */
	public void mostrarMensaje(String mensaje, String titulo, int tipoMensaje) {
		FacesMessage message = new FacesMessage(
				tipoMensaje == MENSAJE_OK ? FacesMessage.SEVERITY_INFO : (tipoMensaje == MENSAJE_ALERTA ? FacesMessage.SEVERITY_WARN : FacesMessage.SEVERITY_ERROR), mensaje,
				titulo);

		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Metodo encargado de mostrar el mensaje de las excepciones controladas.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param jException
	 *            JaverianaException a mostrar
	 */
	public void mostrarMensaje(JaverianaException jException) {
		mostrarMensajeError(jException.getDescripcion(), jException.getSolucion());
	}

	/**
	 * Metodo encargado de mostrar el mensaje de las excepciones NO controladas.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param ex
	 *            Exception a mostrar
	 */
	public void mostrarMensaje(Exception ex) {
		JaverianaException jException = new JaverianaException(ex);
		mostrarMensajeError(jException.getDescripcion(), jException.getSolucion());
	}

	/**
	 * Metodo encargado de determinar el mensaje a mostrar.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param mensaje
	 *            msg a mostrar
	 * @param codigo
	 *            tipo de msg
	 */
	public void mostrarMensaje(String mensaje, int codigo) {
		if (codigo == MENSAJE_ALERTA) {
			mostrarMensajeAlerta(mensaje);
		} else if (codigo == MENSAJE_ERROR) {
			mostrarMensajeError(mensaje, null);
		} else if (codigo == MENSAJE_OK) {
			mostrarMensajeOk(mensaje);
		}
	}

	/**
	 * Metodo encargado de mostrar la ventana de mensajes con el icono de error.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param descripcion
	 *            descripcion del error que ocurrio
	 * @param solucion
	 *            solucion al error que ocurrio
	 */
	public void mostrarMensajeError(String descripcion, String solucion) {
		String mensaje = "";
		if (solucion != null) {
			mensaje = descripcion + ". " + solucion + ".";
		} else {
			mensaje = descripcion;
		}
		mostrarMensaje(mensaje, "ERROR", MENSAJE_ERROR);
	}

	/**
	 * Metodo encargado de mostrar la ventana de mensajes con el icono de
	 * alerta.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param descripcion
	 *            descripcion del error que ocurrio
	 */
	public void mostrarMensajeAlerta(String descripcion) {
		mostrarMensaje(descripcion, "ALERTA", MENSAJE_ALERTA);
	}

	/**
	 * Metodo encargado de mostrar la ventana de mensajes con el icono Ok.
	 * 
	 * @author jhon.andrey
	 * @since V1.0
	 * @param descripcion
	 *            descripcion del error que ocurrio
	 */
	public void mostrarMensajeOk(String descripcion) {
		mostrarMensaje(descripcion, "OK", MENSAJE_OK);
	}

	public void mostrarMensajeBundle(int tipo, String messageKey) {

		FacesContext facesContext = FacesContext.getCurrentInstance();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(Parameters.RESOURCE_BUNDLE_BASE, locale);
		String mensaje = bundle.getString(messageKey);
		mostrarMensaje(mensaje, tipo);
	}

}
