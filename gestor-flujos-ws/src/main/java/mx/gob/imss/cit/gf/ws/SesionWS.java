package mx.gob.imss.cit.gf.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.gob.imss.cit.gf.integration.api.ISesionIntegrator;
import mx.gob.imss.cit.gf.integration.dto.RequestSesionDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseSesionDTO;

/**
 * Clase que expone un webservice
 * 
 * @author admin
 * 
 */
@WebService
public class SesionWS {
	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private ISesionIntegrator sesionIntegrator;

	/**
	 *  Metodo que inicia una sesion de BPM
	 * @param requestSesionDTO
	 * @return
	 */
	@WebMethod
	@WebResult(name = "responseSesionDTO")
	public ResponseSesionDTO getSesion(
			@WebParam(name = "requestSesionDTO") RequestSesionDTO requestSesionDTO) {
		return sesionIntegrator.getSesion(requestSesionDTO);
	}
	
	/**
	 *  Metodo que elimina una sesion de BPM
	 * @param requestSesionDTO
	 * @return
	 */
	@WebMethod
	@WebResult(name = "responseSesionDTO")
	public ResponseSesionDTO deleteSesion(
			@WebParam(name = "requestSesionDTO") RequestSesionDTO requestSesionDTO) {
		return sesionIntegrator.deleteSesion(requestSesionDTO);
	}


}
