package mx.gob.imss.cit.gf.integration.api;

import mx.gob.imss.cit.gf.integration.dto.RequestCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestParticipantesAsigDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseParticipantesAsigDTO;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 18:06:00
 */
public interface IUsuarioIntegrator {

	/**
	 * Crea el usuario
	 * 
	 * @return CreateUsuarioVO
	 */
	ResponseCreateUsuarioDTO executeGuardarMetadatosUsuario(RequestCreateUsuarioDTO request);

	/**
	 * borra el usuario
	 */
	ResponseDeleteUsuarioDTO deleteMetadatosUsuario(RequestDeleteUsuarioDTO request);

	/**
	 * Metodo que realiza la consulta de los participantes asignables a una instancia
	 * @param requestParticipantesAsigDTO
	 * @return ResponseParticipantesAsigDTO
	 */
	ResponseParticipantesAsigDTO findParticipantesAsignablesAActividad(RequestParticipantesAsigDTO requestParticipantesAsigDTO);

	/**
	 * Método que obtiene los usuarios enmarcados dentro de los datos
	 * 
	 * @param requestFindUsuarioDTO
	 * @return ResponseFindUsuarioDTO
	 */
	ResponseFindUsuarioDTO findMetadatosUsuarios(RequestFindUsuarioDTO requestFindUsuarioDTO);
}