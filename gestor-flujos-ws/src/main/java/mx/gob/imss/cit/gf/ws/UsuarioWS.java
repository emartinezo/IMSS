package mx.gob.imss.cit.gf.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.gob.imss.cit.gf.integration.api.IUsuarioIntegrator;
import mx.gob.imss.cit.gf.integration.dto.RequestCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestParticipantesAsigDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseParticipantesAsigDTO;

/**
 * Clase que expone un webservice
 * 
 * @author admin
 * 
 */
@WebService
public class UsuarioWS {
	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private IUsuarioIntegrator usuarioIntegrator;

	/**
	 * Metodo que crea un usuario
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult(name = "responseCreateUsuarioDTO")
	public ResponseCreateUsuarioDTO executeGuardarMetadatosUsuario(
			@WebParam(name = "requestCreateUsuarioDTO") RequestCreateUsuarioDTO request) {

		return usuarioIntegrator.executeGuardarMetadatosUsuario(request);
	}

	/**
	 * Metodo que borra un usuario
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult(name = "responseDeleteUsuarioDTO")
	public ResponseDeleteUsuarioDTO deleteMetadatosUsuario(
			@WebParam(name = "requestDeleteUsuarioDTO") RequestDeleteUsuarioDTO request) {

		return usuarioIntegrator.deleteMetadatosUsuario(request);
	}

	/**
	 * Metodo que borra un usuario
	 * @param request
	 * @return
	 */
	@WebMethod
	@WebResult(name = "responseFindUsuarioDTO")
	public ResponseFindUsuarioDTO findMetadatosUsuarios(
			@WebParam(name = "requestFindUsuarioDTO") RequestFindUsuarioDTO request) {

		return usuarioIntegrator.findMetadatosUsuarios(request);
	}


	/**
	 * Metodo que realiza la consulta de los participantes asignables a una instancia
	 * @param requestParticipantesAsigDTO
	 * @return ResponseParticipantesAsigDTO
	 */
	@WebMethod
	@WebResult(name = "responseParticipantesAsigDTO")
	public ResponseParticipantesAsigDTO findParticipantesAsignablesAActividad(
			@WebParam(name = "requestParticipantesAsigDTO") RequestParticipantesAsigDTO requestParticipantesAsigDTO) {

		return usuarioIntegrator.findParticipantesAsignablesAActividad(requestParticipantesAsigDTO);
	}

}
