package mx.gob.imss.cit.gf.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.integration.api.IUsuarioIntegrator;
import mx.gob.imss.cit.gf.integration.domain.ParticipanteDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioBPMDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestParticipantesAsigDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseCreateUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseDeleteUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseFindUsuarioDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseParticipantesAsigDTO;
import mx.gob.imss.cit.gf.integration.util.IntegrationResponseExceptionUtil;
import mx.gob.imss.cit.gf.services.IUsuarioService;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra las operaciones referentes a un usuario
 * @author admin
 *
 */
@Remote(IUsuarioIntegrator.class)
@Stateless
public class UsuarioIntegrator implements IUsuarioIntegrator {

	/**
	 * atributo del ejb de servicio
	 */
	@EJB
	private IUsuarioService usuarioService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioIntegrator.class);

	/**
	 * Metodo de crear usuario
	 */
	public ResponseCreateUsuarioDTO executeGuardarMetadatosUsuario(RequestCreateUsuarioDTO request) {

		ResponseCreateUsuarioDTO response = new ResponseCreateUsuarioDTO();
		try {
			DatosUsuarioVO createUsuarioVO = executeMapearCreateUsuarioVO(request);
			DatosUsuarioVO voSalida = null;
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(request.getUsuario().getPassword());
			usuarioVO.setUsuario(request.getUsuario().getUsuario());
			voSalida = usuarioService.executeGuardarMetadatosUsuario(usuarioVO, createUsuarioVO);
			response.setCreateUsuario(executeMapearCreateUsuarioDTO(voSalida));
		} catch(Exception e) {
			LOG.error(e.getMessage());
			IntegrationResponseExceptionUtil.setResponseError(response, e);
		}
		return response;
	}

	/**
	 * Metodo de eliminar usuario
	 */
	public ResponseDeleteUsuarioDTO deleteMetadatosUsuario(RequestDeleteUsuarioDTO request) {

		ResponseDeleteUsuarioDTO response = new ResponseDeleteUsuarioDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(request.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(request.getUsuarioDTO().getUsuario());
			usuarioService.deleteMetadatosUsuario(usuarioVO, request.getIdUsuario(), request.getDatosAplicacion());
		} catch(Exception e) {
			LOG.error(e.getMessage());
			IntegrationResponseExceptionUtil.setResponseError(response, e);
		}
		return response;
	}

	/**
	 * Metodo que mapea los campos.
	 * @param createUsuarioDTO campos a mapear.
	 * @return createUsuarioVO objeto que regresa los datos mapeados.
	 */
	private DatosUsuarioVO executeMapearCreateUsuarioVO(RequestCreateUsuarioDTO request) {

		DatosUsuarioVO createUsuarioVO = new DatosUsuarioVO();
		createUsuarioVO.setApplicationDataType(request.getCreateUsuario().getApplicationDataType());
		createUsuarioVO.setName(request.getCreateUsuario().getName());
		createUsuarioVO.setOwner(request.getCreateUsuario().getOwner());
		createUsuarioVO.setIdentityContext(request.getCreateUsuario().getIdentityContext());
		createUsuarioVO.setData(request.getCreateUsuario().getData());
		return createUsuarioVO;
	}

	/**
	 * Metodo que mapea los campos.
	 * @param createUsuarioDTO campos a mapear.
	 * @return createUsuarioVO objeto que regresa los datos mapeados.
	 */
	private DatosUsuarioVO executeMapearFindUsuarioVO(RequestFindUsuarioDTO request) {

		DatosUsuarioVO createUsuarioVO = new DatosUsuarioVO();
		createUsuarioVO.setApplicationDataType(request.getDatosUsuario().getApplicationDataType());
		createUsuarioVO.setOwner(request.getDatosUsuario().getOwner());
		return createUsuarioVO;
	}

	/**
	 * Metodo que mapea los campos.
	 * @param createUsuarioDTO campos a mapear.
	 * @return createUsuarioVO objeto que regresa los datos mapeados.
	 */
	private List<UsuarioBPMDTO> executeMapearFindUsuarioDTO(List<DatosUsuarioVO> listaDatos) {

		List<UsuarioBPMDTO> listaUsuarios = new ArrayList<UsuarioBPMDTO>();
		UsuarioBPMDTO usuarioBPM = null;
		for(DatosUsuarioVO datosVO : listaDatos){

			usuarioBPM = new UsuarioBPMDTO();
			usuarioBPM.setApplicationDataType(datosVO.getApplicationDataType());
			usuarioBPM.setName(datosVO.getName());
			usuarioBPM.setOwner(datosVO.getOwner());
			usuarioBPM.setData(datosVO.getData());
			usuarioBPM.setId(datosVO.getId());
			usuarioBPM.setIdentityContext(datosVO.getIdentityContext());
			listaUsuarios.add(usuarioBPM);
		}
		return listaUsuarios;
	}

	/**
	 * Metodo que realiza el mapeo.
	 * @param createUsuarioVO campos a mapear.
	 * @return createUsuarioDTO objeto que regresa los datos mapeados.
	 */
	private UsuarioBPMDTO executeMapearCreateUsuarioDTO(DatosUsuarioVO createUsuarioVO) {

		UsuarioBPMDTO createUsuarioDTO = new UsuarioBPMDTO();
		createUsuarioDTO.setApplicationDataType(createUsuarioVO.getApplicationDataType());
		createUsuarioDTO.setName(createUsuarioVO.getName());
		createUsuarioDTO.setOwner(createUsuarioVO.getOwner());
		createUsuarioDTO.setIdentityContext(createUsuarioVO.getIdentityContext());
		createUsuarioDTO.setId(createUsuarioVO.getId());
		createUsuarioDTO.setData(createUsuarioVO.getData());
		return createUsuarioDTO;
	}

	/**
	 * Metodo que realiza la consulta de los participantes asignables a una instancia
	 * @param request
	 * @return ResponseParticipantesAsigDTO
	 */
	@Override
	public ResponseParticipantesAsigDTO findParticipantesAsignablesAActividad(RequestParticipantesAsigDTO request) {

		ResponseParticipantesAsigDTO response = new ResponseParticipantesAsigDTO();
		ConsultaParticipantesVO consultaParticipantes = new ConsultaParticipantesVO();
		UsuarioVO usuarioVO = new UsuarioVO();
		List<ParticipanteDTO> participantesResp = new ArrayList<ParticipanteDTO>();
		ParticipanteDTO participanteDTO = null;

		try {
			usuarioVO.setPassword(request.getUsuario().getPassword());
			usuarioVO.setUsuario(request.getUsuario().getUsuario());

			consultaParticipantes.setUsuario(usuarioVO);
			consultaParticipantes.setIdInstanciaProceso(request.getIdInstanciaProceso());
			consultaParticipantes.setIdInstanciaActividad(request.getIdInstanciaActividad());

			List<ParticipanteVO> participantes = usuarioService.findParticipantesAsignablesAActividad(consultaParticipantes);

			for (ParticipanteVO participanteVO: participantes) {
				participanteDTO = new ParticipanteDTO();

				participanteDTO.setUsuario(participanteVO.getUsuario());
				participanteDTO.setNombre(participanteVO.getNombre());
				participanteDTO.setApellidoPaterno(participanteVO.getApellidoPaterno());
				participanteDTO.setRol(participanteVO.getRol());
				participanteDTO.setCorreoElectronico(participanteVO.getCorreoElectronico());

				participantesResp.add(participanteDTO);
			}

			response.setParticipantes(participantesResp);

		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(response, e);
		}

		return response;
	}

	/**
	 * Metodo que realiza la consulta de los daots de los usuarios
	 * @param request
	 * @return ResponseFindUsuarioDTO
	 */
	public ResponseFindUsuarioDTO findMetadatosUsuarios(RequestFindUsuarioDTO request) {

		ResponseFindUsuarioDTO response = new ResponseFindUsuarioDTO();
		try {
			DatosUsuarioVO createUsuarioVO = executeMapearFindUsuarioVO(request);
			List<DatosUsuarioVO> datos = null;
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(request.getUsuarioSesion().getPassword());
			usuarioVO.setUsuario(request.getUsuarioSesion().getUsuario());
			datos = usuarioService.findMetadatosUsuarios(usuarioVO, createUsuarioVO);
			response.setResultadosUsuario(executeMapearFindUsuarioDTO(datos));
		} catch(Exception e) {
			LOG.error(e.getMessage());
			IntegrationResponseExceptionUtil.setResponseError(response, e);
		}
		return response;
	}

}