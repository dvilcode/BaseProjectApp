package $packageName.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import $packageName.entities.ParametroDTO;
import $packageName.entities.RolesAplicacionDTO;
import $packageName.entities.UserDTO;
import $packageName.exception.JaverianaException;
import $packageName.interfaces.services.IJaverianaExceptionService;
import $packageName.interfaces.services.IParametroService;
import $packageName.interfaces.services.IRolesAplicacionService;
import $packageName.interfaces.services.ISecurityService;
import $packageName.interfaces.services.IUserService;
import $packageName.managedbeans.DatosSesionBean;
import $packageName.managedbeans.MensajesBean;
import $packageName.utils.Parameters;
import $packageName.utils.StringUtil;

public class SessionFilter extends OncePerRequestFilter {

	@Autowired
	private ISecurityService securityService;
	@Autowired
	private IUserService userService;
	@Autowired
	IJaverianaExceptionService javerianaExceptionService;
	private static final String SPRING_SECURITY = "SPRING_SECURITY_CONTEXT";
	@Autowired
	private IRolesAplicacionService rolesAplicacionService;
	@Autowired
	private IParametroService parametroService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		// Parametros acceso portales puj
		String strUsuario, secuencia, p_idEntrada, p_portal;

		// Parametros Autenticacion LDAP
		String userLogin;
		String passwLogin;

		boolean autenticado = false;
		DatosSesionBean datosSesion = null;
		boolean isDebug = true;

		try {
			// Se obtiene el valor de los parametros de los portales de PUJ
			p_idEntrada = request.getParameter("p_idEntrada");
			strUsuario = request.getParameter("pp");
			secuencia = request.getParameter("pp1");
			p_portal = request.getParameter("portal");

			// CAMPOS DE AUTENTICACION POR LDAP
			userLogin = request.getParameter("form1:txtUsuarioLogin");
			passwLogin = request.getParameter("form1:txtPassLogin");

			// No la cree si no existe (false)
			HttpSession session = null;

			// Si viene los parametros de autenticacion se obtiniene una nueva sesion de lo
			// contrario se espera la misma
			if (p_idEntrada != null || strUsuario != null || userLogin != null || request.getRequestURI().equals("/parqueaderopuj/faces/login.xhtml")) {
				session = request.getSession(true);
			} else {
				if (isDebug) {
					session = request.getSession(true);
				} else {
					session = request.getSession(false);
				}

			}

			if (session != null) {

				if (!StringUtil.isEmptyOrNull(p_idEntrada) || !StringUtil.isEmptyOrNull(strUsuario)
						|| !StringUtil.isEmptyOrNull(userLogin)) {
					session.removeAttribute(SPRING_SECURITY);
				}
				SecurityContext ctx = (SecurityContext) session.getAttribute(SPRING_SECURITY);

				if (ctx == null) {

					datosSesion = new DatosSesionBean();

					if (isDebug) {
						// Validamos si vienen por request los parametros
						if (!StringUtil.isEmptyOrNull(request.getParameter("emplid"))) {
							datosSesion.setEmplId(request.getParameter("emplid"));
						} else {
							datosSesion.setEmplId("0000051608");
						}
						if (!StringUtil.isEmptyOrNull(request.getParameter("userid"))) {
							datosSesion.setUserId(request.getParameter("userid"));
						} else {
							datosSesion.setUserId("LEBELA");
						}
						if (!StringUtil.isEmptyOrNull(request.getParameter("rol"))) {
							datosSesion.setIdRolActual(new Long(request.getParameter("rol")));
						} else {
							datosSesion.setIdRolActual(new Long(1));
						}
						if (!StringUtil.isEmptyOrNull(request.getParameter("portal"))) {
							datosSesion.setPortal(request.getParameter("portal"));
						} else {
							datosSesion.setPortal("col");
						}

						autenticado = true;
					} else {
						if ((!StringUtil.isEmptyOrNull(strUsuario) && !StringUtil.isEmptyOrNull(secuencia))) {
							int strSecuencia = Integer.valueOf(secuencia.trim());
							String emplId = "";
							emplId = securityService.findEmplId(strUsuario.trim(), strSecuencia);
							String usuario = securityService.procesar(strUsuario.trim(), strSecuencia);
							if (!StringUtil.isEmptyOrNull(usuario)) {
								datosSesion.setEmplId(emplId);
								datosSesion.setUserId(usuario.toLowerCase());
								datosSesion.setPortal(Parameters.PARAMETRO_PORTAL_PEOPLESOFT);
								autenticado = true;
							}
						} else if (!StringUtil.isEmptyOrNull(p_idEntrada) && !StringUtil.isEmptyOrNull(p_portal)
								&& p_portal.equals(Parameters.PARAMETRO_PORTAL_ESTUDIANTE)) {
							PortalEstudiante portal = securityService.procesar(p_idEntrada.trim());
							if (portal != null) {
								datosSesion.setEmplId(portal.getEmplid());
								datosSesion.setUserId(portal.getUsuario());
								datosSesion.setPortal(Parameters.PARAMETRO_PORTAL_ESTUDIANTE);
								autenticado = true;
							}
						} else if (!StringUtil.isEmptyOrNull(p_idEntrada) && !StringUtil.isEmptyOrNull(p_portal)
								&& p_portal.equals(Parameters.PARAMETRO_PORTAL_COLABORADOR)) {
							PortalColaborador portal = securityService.procesarPortalColaborador(p_idEntrada.trim());
							if (portal != null) {
								datosSesion.setEmplId(portal.getEmplid());
								datosSesion.setUserId(portal.getUsuario());
								datosSesion.setPortal(Parameters.PARAMETRO_PORTAL_COLABORADOR);
								autenticado = true;
							}
						} else if (!StringUtil.isEmptyOrNull(userLogin) && !StringUtil.isEmptyOrNull(passwLogin)) {
							UserDTO userSelected = new UserDTO();
							userSelected.setOprid(userLogin);
							userSelected.setPass(passwLogin);

							// Se valida si es usuario del LDap.
							boolean isUserLdap = userService.autenticarUsuario(userSelected);

							if (isUserLdap) {

								// Si encuentra el usuario en el sistema, se cargan los datos en sesion.
								UserDTO userFilter = null;
								userFilter = userService.findUserByUserName(userSelected);
								
								if (userFilter != null) {
									datosSesion = new DatosSesionBean();
									datosSesion.setUserId(userFilter.getOprid());
									datosSesion.setEmplId(userFilter.getEmplid());
									//TODO JAL
									//datosSesion.setPortal(Parameters.PARAMETRO_PORTAL_MOBILITY);

									autenticado = true;
								} else {
									// Se redirecciona con parametro para mostrar mensaje de error.
									response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
									return;
								}
							} else {
								// Se redirecciona con parametro para mostrar mensaje de error.
								response.sendRedirect(request.getContextPath() + "/faces/login.xhtml");
								return;
							}
						}
					}

					session.setAttribute(Parameters.LLAVE_DATOS_SESION, datosSesion);

					if (autenticado) {
						ArrayList<GrantedAuthority> grantedAuthorities = null;
						List<String> roles = null;
						List<RolesAplicacionDTO> rolesUsuarioList = new ArrayList<>();
						RolesAplicacionDTO filtroRol = null;

						grantedAuthorities = new ArrayList<GrantedAuthority>();
						roles = securityService.Roles(datosSesion.getUserId());
						filtroRol = new RolesAplicacionDTO();
						for (String rol : roles) {
							// Por cada rol se consulta sus correspondientes rol de aplicacion
							filtroRol.setRolesPeople(rol);
							List<RolesAplicacionDTO> rolesList = null;
							rolesList = rolesAplicacionService.findByCriteria(filtroRol, "NOMBRE", false);
							if (rolesList != null && !rolesList.isEmpty()) {
								boolean existente = false;
								for (RolesAplicacionDTO rolAplicacion : rolesList) {
									for (RolesAplicacionDTO rolUsuario : rolesUsuarioList) {
										// Validamos si el rol ya no esta en la lista
										if (rolAplicacion.getIdRol().intValue() == rolUsuario.getIdRol().intValue()) {
											existente = true;
											break;
										}
									}
									if (!existente) {
										// Si aun no se ha adicionado se agrega a la lista de roles usuario
										rolesUsuarioList.add(rolAplicacion);
										// Se registra con spring security
										grantedAuthorities.add(new SimpleGrantedAuthority(rolAplicacion.getNombre()));
									}
								}
							}
						}

						// Si se encontro al menos un rol se asigna el rol actual el primero de la lista
						if (!rolesUsuarioList.isEmpty()) {
							datosSesion.setIdRolActual(rolesUsuarioList.get(0).getIdRol());
							// Se asginan los roles encontrados en session
							datosSesion.setRolesList(rolesUsuarioList);
							// Se crea los selectitems del menu de roles
							List<SelectItem> itemRoles = new ArrayList<SelectItem>();
							for (RolesAplicacionDTO role : rolesUsuarioList) {
								itemRoles.add(new SelectItem(role.getIdRol(), role.getNombre()));
							}
							datosSesion.setItemRoles(itemRoles);
						}

						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("", null,
								grantedAuthorities);
						SecurityContext securityContext = SecurityContextHolder.getContext();
						securityContext.setAuthentication(auth);
						session.setAttribute(SPRING_SECURITY, securityContext);
					}

					chain.doFilter(request, response);
				} else {

					chain.doFilter(request, response);
				}

			} else {
				if(request.getRequestURI().equals("/parqueaderopuj/faces/login.xhtml")) {
					chain.doFilter(request, response);
					return;
				}
				
				// La sesion se invalido
				boolean ajax = isAJAXRequest(request);
				String redirectURL = response
						.encodeRedirectURL(request.getContextPath() + Parameters.URI_LOGOUT);

				if (ajax) {
					StringBuilder sb = new StringBuilder();
					sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"")
							.append(redirectURL).append("\"></redirect></partial-response>");
					response.setHeader("Cache-Control", "no-cache");
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/xml");
					PrintWriter pw = response.getWriter();
					pw.println(sb.toString());
					pw.flush();
				} else {
					response.sendRedirect(request.getContextPath() + Parameters.URI_LOGOUT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SessionFilter.doFilterInternal. Causa: " + e.getMessage(), e);
			chain.doFilter(request, response);
		}
	}

	private boolean isAJAXRequest(HttpServletRequest request) {
		boolean check = false;
		String facesRequest = request.getHeader("Faces-Request");
		if (facesRequest != null && facesRequest.equals(Parameters.HEADER_AJAX_REQUEST)) {
			check = true;
		}
		return check;
	}
}
