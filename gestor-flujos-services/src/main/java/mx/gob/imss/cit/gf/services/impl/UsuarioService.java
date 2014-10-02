package mx.gob.imss.cit.gf.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.constant.NombreParametroConstants;
import mx.gob.imss.cit.gf.constant.ValidacionesConstants;
import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IUsuarioService;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesExceptionBuilder;
import mx.gob.imss.cit.gf.util.ValidatorUtil;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 18:08:45
 */
@Remote(IUsuarioService.class)
@Stateless
public class UsuarioService implements IUsuarioService {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

	/**
	 * Interfaz de AdapterBPM
	 */
	@EJB
	private IAdapterBPM adapterBPM;

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Dar de alta cuenta de usuario de administrador de procesos.
	 *Descripcion : Metodo que crea un usuario en BPM
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param createUsuario
	 * 		  Contiene los datos para crear un usuario.
	 * 		 {name(String),applicationDataType(String),
	 * 			owner(String)}
	 * @return CreateUsuarioVO
	 * 			Contiene los datos de alta del usuario.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */

	public DatosUsuarioVO executeGuardarMetadatosUsuario(UsuarioVO usuario, DatosUsuarioVO createUsuario) throws GestorFlujosServicesException {

		LOG.debug("iniciando find participantes");
		DatosUsuarioVO createUsuarioVO = null;
		try {
			validateCrearUsuario(usuario, createUsuario);
			adapterBPM.getSesion(usuario);
			createUsuarioVO = adapterBPM.executeGuardarMetadatosUsuario(usuario, createUsuario);

		} catch(GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} finally {
			adapterBPM.executeCerrarBPMContext(usuario.getUsuario());
		}
		return createUsuarioVO;
	}

	/**
	 * Borra el usuario del sistema
	 * 
	 * @return UserApplicationDataType
	 */

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Dar de baja cuenta de usuario de administrador de procesos.
	 *Descripcion : Metodo que elimina una cuenta del BPM.
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param idUsuario
	 * 		  El identificador de usuario a eliminar.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	public void deleteMetadatosUsuario(UsuarioVO usuario, String idUsuario, String datosAplicacion) throws GestorFlujosServicesException {

		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuario.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuario.getPassword());
			parametros.put(NombreParametroConstants.USUARIO_ID, idUsuario);
			parametros.put(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, datosAplicacion);
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO, usuario.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD, usuario.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_ID, idUsuario, ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, datosAplicacion, ValidacionesConstants.ER_ALFANUMERICO1A50);

			adapterBPM.getSesion(usuario);
			adapterBPM.deleteMetadatosUsuario(usuario, idUsuario, datosAplicacion);

		} catch(GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} finally {
			adapterBPM.executeCerrarBPMContext(usuario.getUsuario());
		}
	}

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Obtener lista de datos del usuario
	 *Descripcion : Metodo que obtiene los datos del usuario
	 **************************** PARMETROS ********************************
	 * @param usuario
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param datosUsuario
	 * 		  Contiene los datos para obtener un usuario.
	 * 		 {applicationDataType(String),
	 * 			owner(String)}
	 * @return CreateUsuarioVO
	 * 			Contiene los datos de alta del usuario.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	public List<DatosUsuarioVO> findMetadatosUsuarios(UsuarioVO usuario, DatosUsuarioVO datosUsuario) throws GestorFlujosServicesException{

		List<DatosUsuarioVO> listaUsuarios = null;
		try {
			Map<String, Object> parametros = new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuario.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuario.getPassword());
			parametros.put(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, datosUsuario.getApplicationDataType());
			parametros.put(NombreParametroConstants.USUARIO_OWNER, datosUsuario.getOwner());
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO, usuario.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD, usuario.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, datosUsuario.getApplicationDataType(),
					ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_OWNER, datosUsuario.getOwner(),ValidacionesConstants.ER_ALFANUMERICO1A50);			
			

			adapterBPM.getSesion(usuario);
			listaUsuarios = adapterBPM.findMetadatosUsuarios(usuario, datosUsuario);
		} catch(GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} finally {
			adapterBPM.executeCerrarBPMContext(usuario.getUsuario());			
		}
		return listaUsuarios;
	}

	/**
	 * Metodo que retorna los participantes que pueden ser asignados a una instancia de actividad
	 * 
	 * @param consultaParticipantes
	 * 
	 * @return List<ParticipanteVO>
	 * 
	 * @throws Exception
	 */
	@Override
	public List<ParticipanteVO> findParticipantesAsignablesAActividad(ConsultaParticipantesVO consultaParticipantes) throws GestorFlujosServicesException {

		List<ParticipanteVO> participantes = null;

		try {
			participantes = adapterBPM.findParticipantesAsignablesAActividad(consultaParticipantes);
		} catch(AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		}

		return participantes;
	}

	/**
	 * Metodo que realiza la validasion los valores de  UsuarioVO y CreateUsuarioVO.
	 * @param usuario Contiene los datos de sesion.
	 * @param createUsuario contiene los datos de crear usuario.
	 */
	private void validateCrearUsuario(UsuarioVO usuario, DatosUsuarioVO createUsuario) throws GestorFlujosException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put(NombreParametroConstants.USUARIO, usuario.getUsuario());
		parametros.put(NombreParametroConstants.PASSWORD, usuario.getPassword());
		parametros.put(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, createUsuario.getApplicationDataType());
		parametros.put(NombreParametroConstants.USUARIO_OWNER, createUsuario.getOwner());
		parametros.put(NombreParametroConstants.USUARIO_NAME, createUsuario.getName());
		ValidatorUtil.validateParametros(parametros);

		//validacion de tipo formato
		ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO, usuario.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD, usuario.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_APPLICATION_DATA_TYPE, 
				createUsuario.getApplicationDataType(),ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_OWNER, createUsuario.getOwner(),ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO_NAME, createUsuario.getName(),ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.DATA, createUsuario.getData(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.IDENTITY_CONTEXT, createUsuario.getIdentityContext(), ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A50);
		
	}
}