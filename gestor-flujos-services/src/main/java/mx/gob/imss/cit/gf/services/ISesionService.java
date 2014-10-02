package mx.gob.imss.cit.gf.services;

import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * Interface que contiene metodos referentes al manejo de la sesion del BPM
 * 
 * @author Admin
 * 
 */

public interface ISesionService {

	/**
	 * Metodo que obtiene la sesion de BPM.
	 * 
	 * @param usuarioVO
	 *            contiene los datos de usuario para la sesion.
	 * @return sesionVO contiene la sesion.
	 * @throws GestorFlujosServicesException
	 */
	SesionVO getSesion(UsuarioVO usuarioVO)
			throws GestorFlujosServicesException;

	/**
	 * Metodo que obtiene la sesion de BPM.
	 * 
	 * @param usuarioVO
	 *            contiene los datos de usuario para la sesion.
	 * @return sesionVO contiene la sesion.
	 * @throws GestorFlujosServicesException
	 */
	Boolean deleteSesion(UsuarioVO usuarioVO)
			throws GestorFlujosServicesException;

}
