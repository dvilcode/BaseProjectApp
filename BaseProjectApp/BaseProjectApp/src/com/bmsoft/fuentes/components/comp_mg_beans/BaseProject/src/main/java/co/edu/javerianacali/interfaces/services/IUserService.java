package $packageName.interfaces.services;

import $packageName.entities.ParametroDTO;
import $packageName.entities.UserDTO;
import $packageName.exception.JaverianaException;

public interface IUserService {

	/**
	 * Metodo para validar el usuario en LDap.
	 * 
	 * @author Dv
	 * @param String userName
	 * @param String pass
	 */
	public boolean autenticarUsuario(UserDTO entity) throws JaverianaException;

	/**
	 * Consulta los datos de usuario por nombre de usuario.
	 * 
	 * @author Dv
	 * @param UserDTO user [name], [idEmpresa]
	 */
	public UserDTO findUserByUserName(UserDTO user) throws JaverianaException;

	/**
	 * Consulta el rol por defecto.
	 * 
	 * @author Dv
	 * @param parametroDTO idParametro
	 */
	public ParametroDTO getRolDefault(ParametroDTO idRol) throws JaverianaException;
}
