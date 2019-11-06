package $packageName.security;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import $packageName.entities.ParametroDTO;
import $packageName.interfaces.services.IParametroService;
import $packageName.managedbeans.DatosSesionBean;
import $packageName.utils.Parameters;

public class LogoutHandler implements LogoutSuccessHandler {

	@Autowired
	IParametroService objParametroService;
	private static final Logger bitacora = Logger.getRootLogger();

	@Override
	public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication)
			throws IOException, ServletException {
		String portal = null;
		String idCentro=null;
		DatosSesionBean datosSesion=null;
		try {
			//Se obtiene el valor del atributo portal
			portal=httpServletRequest.getParameter("portal");
			idCentro=httpServletRequest.getParameter("idc");
			
			// Se invalida la sesion
			httpServletRequest.getSession().invalidate();

			httpServletResponse.setStatus(HttpServletResponse.SC_OK);
			// Se redirecciona al login dependiente del portal donde venga
			ParametroDTO parametro = new ParametroDTO();
			if (portal == null
					|| portal.equals(Parameters.PARAMETRO_PORTAL_ESTUDIANTE)) {
				parametro.setIdParametro(Parameters.ParametrosTable.URL_Login_Portal_Estudiante.getValue().longValue());
				parametro = objParametroService.findByPK(parametro);
				httpServletResponse.sendRedirect(parametro.getValor());
			} else if(portal.equals(Parameters.PARAMETRO_PORTAL_COLABORADOR)) {
				parametro.setIdParametro(Parameters.ParametrosTable.URL_Login_Portal_Colaborador.getValue().longValue());
				parametro = objParametroService.findByPK(parametro);
				httpServletResponse.sendRedirect(parametro.getValor());
			} else if(portal.equals(Parameters.PARAMETRO_PORTAL_PEOPLESOFT)) {
				parametro.setIdParametro(Parameters.ParametrosTable.URL_Login_Peoplesoft.getValue().longValue());
				parametro = objParametroService.findByPK(parametro);
				httpServletResponse.sendRedirect(parametro.getValor());
			}
			else if(portal.equals(Parameters.PARAMETRO_PORTAL_USUARIO)) {
				parametro.setIdParametro(Parameters.ParametrosTable.URL_Login_Usuario_Externo.getValue().longValue());
				parametro = objParametroService.findByPK(parametro);
				httpServletResponse.sendRedirect(parametro.getValor()+"?idc="+idCentro);
			}
			else{
				//Se envia por defecto al portal del estudiante
				parametro.setIdParametro(Parameters.ParametrosTable.URL_Login_Portal_Estudiante.getValue().longValue());
				parametro = objParametroService.findByPK(parametro);
				httpServletResponse.sendRedirect(parametro.getValor());
			}
		} catch (Exception e) {
			bitacora.error("LogoutHandler.onLogoutSuccess error logica cierre sesion y redireccionamiento en el logout de la aplicacion",e);
		}		
	}
}
