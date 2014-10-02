package mx.gob.imss.cit.gf.integration.api;

import mx.gob.imss.cit.gf.integration.dto.RequestSesionDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseSesionDTO;

/**
 * Interface de la capa de intergracion
 * para el servicio de sesion
 * 
 * @author Admin
 *
 */

public interface ISesionIntegrator {

	/**
	 * Meotodo que obtiene la sesion.
	 * @param requestSesionDTO datos de usuario para la sesion.
	 * @return sesion obtenida.
	 * @throws GestorFlujosServicesException
	 */
	ResponseSesionDTO getSesion(RequestSesionDTO requestSesionDTO);
	
	/**
	 * Metodo que elimina la sesion de BPM.
	 * 
	 * @param usuarioVO
	 *            contiene los datos de usuario para la sesion.
	 * @return sesionVO contiene la sesion.
	 * @throws GestorFlujosServicesException
	 */
	ResponseSesionDTO deleteSesion(RequestSesionDTO requestSesionDTO);

	
}
