package mx.gob.imss.cit.gf.services;

import java.util.List;

import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 18:06:00
 */
public interface IUsuarioService {

	/**
	 *  Metodo que crea el usuario en el administrador de procesos del BPM
	 * @param usuario contiene los datos para iniciar la sesion.
	 * @param createUsuario objeto que contiene los datos para la alta.
	 * @return createUsuarioVO contiene los datos de una alta de un usuario.
	 * @throws Exception
	 */
	DatosUsuarioVO executeGuardarMetadatosUsuario(UsuarioVO usuario, DatosUsuarioVO createUsuario) throws GestorFlujosServicesException;

	/**
	 *  Metodo que borra el usuario en el administrador de procesos del BPM
	 * @param usuario contiene los datos para iniciar la sesion.
	 * @param idUsuario identificador del usuario.
	 * @throws Exception
	 */
	void deleteMetadatosUsuario(UsuarioVO usuario, String idUsuario, String datosAplicacion) throws GestorFlujosServicesException;

	/**
	 * Método que obtiene los usuarios enmarcados dentro de los datos
	 * 
	 * @param usuario
	 * @param datosUsuario
	 * @return List<CreateUsuarioVO>
	 */
	List<DatosUsuarioVO> findMetadatosUsuarios(UsuarioVO usuario, DatosUsuarioVO datosUsuario) throws GestorFlujosServicesException;


	/**
	 * Metodo que retorna los participantes que pueden ser asignados a una instancia de actividad
	 * 
	 * @param consultaParticipantes
	 * 
	 * @return List<ParticipanteVO>
	 * 
	 * @throws Exception
	 */
	List<ParticipanteVO> findParticipantesAsignablesAActividad(ConsultaParticipantesVO consultaParticipantes) throws GestorFlujosServicesException;

}