package $packageName.interfaces.services;

import $packageName.exception.JaverianaException;

/**
 * @author wilferac
 * 
 */

public interface IJaverianaExceptionService {

	/**
	 * Carga los datos del error desde la BD
	 * 
	 * @param codigo
	 *            codigo del error
	 * @param dato
	 *            datos para el reemplazo de los comodines EJ: para el error
	 *            "Ocurrio un error con el campo $1, verifique que tenga el
	 *            formato $2" enviariamos un array {"email", "email@dominio"} el
	 *            resultado seria "Ocurrio un error con el campo email,
	 *            verifique que tenga el formato email@dominio" "
	 * @return
	 * @throws Exception
	 */
	public JaverianaException throwException(String codigo, String[] dato);

}
