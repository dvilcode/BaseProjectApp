package $packageName.interfaces.services;

import java.util.List;

import $packageName.security.PortalColaborador;
import $packageName.security.PortalEstudiante;

public interface ISecurityService {

	String procesar(String usuario, int sec);

	PortalEstudiante procesar(String p_idEntrada);

	PortalColaborador procesarPortalColaborador(String p_idEntrada);

	List<String> Roles(String usuario);

	String findEmplId(String userName, int secuencia) throws Exception;
}
