/**
 * 
 */
package mx.gob.imss.cit.gf.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.integration.api.ITareaIntegrator;
import mx.gob.imss.cit.gf.integration.domain.TareaDTO;
import mx.gob.imss.cit.gf.integration.domain.TareaRolDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaRolDTO;
import mx.gob.imss.cit.gf.integration.util.IntegrationResponseExceptionUtil;
import mx.gob.imss.cit.gf.services.ITareaService;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra los servicios referentes a una Tarea
 * 
 * @author ahernandezd
 * 
 */
@Remote(ITareaIntegrator.class)
@Stateless
public class TareaIntegrator implements ITareaIntegrator {

	/**
	 * aributo de servicio
	 */
	@EJB
	private ITareaService tareaService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(TareaIntegrator.class);

	/**
	 * Metodo que reliza la consulta de tareas por instancia.
	 */
	@Override
	public ResponseTareaDTO findTareasInstancia(
			RequestInstanciaDTO requestInstanciaDTO) {

		List<TareaVO> listaTareaVO = null;
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();

		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO()
					.getPassword());
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO()
					.getUsuario());
			InstanciaVO instanciaVO = new InstanciaVO();
			instanciaVO.setIdInstancia(requestInstanciaDTO.getInstanciaDTO()
					.getIdInstancia());
			instanciaVO.setEstado(requestInstanciaDTO.getInstanciaDTO()
					.getEstado());
			instanciaVO.setFechaCreacion(requestInstanciaDTO.getInstanciaDTO()
					.getFechaCreacion());
			instanciaVO.setUsuarioCreador(requestInstanciaDTO.getInstanciaDTO()
					.getUsuarioCreador());
			
			listaTareaVO = tareaService.findTareasInstancia(usuarioVO,
					requestInstanciaDTO.getInstanciaDTO().getIdInstancia());

			responseTareaDTO.setListaTareaDTO(executeMapearTarea(listaTareaVO));

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}
		return responseTareaDTO;
	}

	/**
	 * Metodo que busca las tareas por usuario y/o estado.
	 */
	@Override
	public ResponseTareaDTO findTareas(RequestTareaDTO requestTareaDTO) {
		List<TareaVO> listaTareaVO = null;
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();

		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO
					.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			TareaVO tareaVO = new TareaVO();
			tareaVO.setEstado(requestTareaDTO.getTareaDTO().getEstado());
			tareaVO.setUsuarioCreador(requestTareaDTO.getTareaDTO()
					.getUsuarioCreador());

			listaTareaVO = tareaService.findTareas(usuarioVO, tareaVO);
			responseTareaDTO.setListaTareaDTO(executeMapearTarea(listaTareaVO));

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}
		return responseTareaDTO;
	}

	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (TareaVO a
	 * TareaDTO).
	 * 
	 * @param listaInstanciaVO
	 *            lista de tareas a mapeadas.
	 * @return listaInstanciaDTO lista de tareas mapeadas.
	 */
	private List<TareaDTO> executeMapearTarea(List<TareaVO> listaTareaVO) {
		List<TareaDTO> listaTareaDTO = new ArrayList<TareaDTO>();
		if (listaTareaVO != null) {
			for (TareaVO tareaVO : listaTareaVO) {
				TareaDTO tareaDTO = new TareaDTO();
				tareaDTO.setEstado(tareaVO.getEstado());
				tareaDTO.setFechaFinEjecucion(tareaVO.getFechaFinEjecucion());
				tareaDTO.setFechaInicioEjecucion(tareaVO.getFechaInicioEjecucion());
				tareaDTO.setFechaCompromiso(tareaVO.getFechaCompromiso());
				tareaDTO.setFechaInicio(tareaVO.getFechaInicio());
				tareaDTO.setIdTarea(tareaVO.getIdTarea());
				tareaDTO.setNombreTarea(tareaVO.getNombreTarea());
				tareaDTO.setNumeroTarea(tareaVO.getNumeroTarea());
				tareaDTO.setPrioridad(tareaVO.getPrioridad());
				tareaDTO.setUrlTarea(tareaVO.getUrlTarea());
				tareaDTO.setUsuarioCreador(tareaVO.getUsuarioCreador());
				listaTareaDTO.add(tareaDTO);
			}
		}
		return listaTareaDTO;
	}

	/**
	 * Metodo que permite rechazar o posponer una actividad
	 */
	@Override
	public ResponseTareaDTO updateEstadoTarea(RequestTareaDTO requestTareaDTO) {
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();

		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			TareaVO tareaVO = new TareaVO();
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			usuarioVO
					.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());
			tareaVO.setIdTarea(requestTareaDTO.getTareaDTO().getIdTarea());
			tareaVO.setEstado(requestTareaDTO.getTareaDTO().getEstado());

			responseTareaDTO.setExitoso(tareaService.updateEstadoTarea(
					usuarioVO, tareaVO));

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}

		return responseTareaDTO;

	}

	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una
	 * instancia.
	 * 
	 * @param requestTareaDTO
	 *            contiene el id de instancia y los datos del usuario
	 * @return ResponseTareaDTO contiene la lista de tareas con sus respectivos
	 *         roles
	 */
	public ResponseTareaRolDTO findRolTareasInstancia(
			RequestTareaDTO requestTareaDTO) {
		ResponseTareaRolDTO responseTarearolDTO = new ResponseTareaRolDTO();

		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			usuarioVO
					.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());
			
			TareaVO tareaVO= new TareaVO();
			tareaVO.setIdTarea(requestTareaDTO.getTareaDTO().getIdTarea());
			
			responseTarearolDTO.setListaTareaRolDTO(executeMapearTareaRol(tareaService
					.findRolTareasByInstancia(usuarioVO, tareaVO)));

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTarearolDTO, e);
		}
		return responseTarearolDTO;
	}

	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (List TareaVO a
	 * List TareaDTO).
	 * 
	 * @param listaInstanciaVO
	 *            lista de tareas a mapeadas.
	 * @return listaInstanciaDTO lista de tareas mapeadas.
	 */
	private List<TareaRolDTO> executeMapearTareaRol(List<TareaVO> listaTareaVO) {
		List<TareaRolDTO> listaTareaRolDTO = new ArrayList<TareaRolDTO>();
		if (listaTareaVO != null) {			
			for (TareaVO tareaVO : listaTareaVO) {
				listaTareaRolDTO.add(tareaVOToTareaRolDTO(tareaVO));
			}
		}
		return listaTareaRolDTO;
	}
	
	
	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (TareaVO a
	 * TareaDTO).
	 * 
	 * @param listaInstanciaVO
	 *            lista de tareas a mapeadas.
	 * @return tareaRolDTO tareas mapeadas.
	 */	
	private TareaRolDTO tareaVOToTareaRolDTO(TareaVO tareaVO){
		TareaRolDTO tareaRolDTO = new TareaRolDTO();
		tareaRolDTO.setIdTarea(tareaVO.getIdTarea());
		tareaRolDTO.setNombreTarea(tareaVO.getNombreTarea());
		tareaRolDTO.setNumeroTarea(tareaVO.getNumeroTarea());
		tareaRolDTO.setRol(tareaVO.getRol());
		return tareaRolDTO;

	}
	 /**
	  * Metodo que reliza la busqueda de tareas por estado de asignado y por Id de instancia.
	  * @param requestInstanciaDTO 
	  * 			contiene el id de instancia y los datos del usuario.
	  * @return responseTareaDTO 
	  * 			contiene la lista de tareas en estado de ejecucion.
	  */
	@Override
	public ResponseTareaDTO findTareasAsignadas(
			RequestInstanciaDTO requestInstanciaDTO) {

		List<TareaVO> listaTareaVO = null; 
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());
			usuarioVO
					.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			listaTareaVO =tareaService.findTareasAsignadas(usuarioVO, requestInstanciaDTO.getInstanciaDTO()
					.getIdInstancia());
			
			responseTareaDTO.setListaTareaDTO(mapeaTareaAsignadas(listaTareaVO));
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}
		return responseTareaDTO;
	}
	
	
	/**
	 * Metodo que realiza el mapeo de la consulta tareas en asignado (TareaVO a
	 * TareaDTO).
	 * 
	 * @param listaInstanciaVO
	 *            lista de tareas a mapeadas.
	 * @return listaInstanciaDTO lista de tareas mapeadas.
	 */
	private List<TareaDTO> mapeaTareaAsignadas(List<TareaVO> listaTareaVO) {
		List<TareaDTO> listaTareaDTO = null;
		if (listaTareaVO != null) {
			listaTareaDTO = new ArrayList<TareaDTO>();
			for (TareaVO tareaVO : listaTareaVO) {
				TareaDTO tareaDTO = new TareaDTO();
				tareaDTO.setNombreTarea(tareaVO.getNombreTarea());
				tareaDTO.setIdTarea(tareaVO.getIdTarea());
				tareaDTO.setFechaCompromiso(tareaVO.getFechaCompromiso());
				tareaDTO.setPrioridad(tareaVO.getPrioridad());
				tareaDTO.setNumeroTarea(tareaVO.getNumeroTarea());
				tareaDTO.setUrlTarea(tareaVO.getUrlTarea());
				tareaDTO.setFechaInicio(tareaVO.getFechaInicio());
				tareaDTO.setFechaInicioEjecucion(tareaVO.getFechaInicioEjecucion());
				tareaDTO.setResponsableActividad(tareaVO.getResponsableActividad());
				listaTareaDTO.add(tareaDTO);
			}
		}
		return listaTareaDTO;
	}	

	/**
	 * Metodo para obtener el valor de una variable en una actividad.
	 * @param requestConsultaArgActDTO
	 * @return ResponseConsultaArgActDTO
	 */	
	@Override
	public ResponseConsultaArgActDTO getArgumentoActividad(RequestConsultaArgActDTO requestConsultaArgActDTO) {
		
		ResponseConsultaArgActDTO responseConsultaArgActDTO = new ResponseConsultaArgActDTO();
		ConsultaArgActividadVO consultaArgActividadVO = new ConsultaArgActividadVO();
		String valorArgumento = null;
		
		try{
			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestConsultaArgActDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestConsultaArgActDTO.getUsuarioDTO().getUsuario());
			
			consultaArgActividadVO.setUsuarioVO(usuarioVO);
			consultaArgActividadVO.setIdInstanciaProceso(requestConsultaArgActDTO.getIdInstanciaProceso());
			consultaArgActividadVO.setIdInstanciaActividad(requestConsultaArgActDTO.getIdInstanciaActividad());
			consultaArgActividadVO.setNombreVble(requestConsultaArgActDTO.getNombreVble());
			
			valorArgumento = tareaService.getArgumentoActividad(consultaArgActividadVO);
			
			responseConsultaArgActDTO.setValorArgumento(valorArgumento);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseConsultaArgActDTO, e);
		}
		
		return responseConsultaArgActDTO;
	}
	
	/**
	 * Metodo que permite actualizar una tarea que no este en EJECUCION, RECHAZADA o POSPUESTA 
	 * @param requestTareaDTO 	contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene la lista de tareas.
	*/	
	@Override
	public ResponseTareaDTO updateTarea(RequestTareaDTO requestTareaDTO) {
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();

		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			TareaVO tareaVO = new TareaVO();
			InstanciaVO instanciaVO = new InstanciaVO();
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			usuarioVO.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());
						
			instanciaVO.setIdInstancia(requestTareaDTO.getInstanciaDTO().getIdInstancia());
			tareaVO.setIdTarea(requestTareaDTO.getTareaDTO().getIdTarea());
				
			tareaVO.setFechaCompromiso(requestTareaDTO.getTareaDTO().getFechaCompromiso());
			tareaVO.setPrioridad(requestTareaDTO.getTareaDTO().getPrioridad());
			tareaVO.setNumeroTarea(requestTareaDTO.getTareaDTO().getNumeroTarea());
			tareaVO.setEstado(requestTareaDTO.getTareaDTO().getEstado());
			tareaVO.setFechaFinEjecucion(requestTareaDTO.getTareaDTO().getFechaFinEjecucion());
			tareaVO.setFechaInicioEjecucion(requestTareaDTO.getTareaDTO().getFechaInicioEjecucion());
			tareaVO.setUrlTarea(requestTareaDTO.getTareaDTO().getUrlTarea());
			tareaVO.setComentario(requestTareaDTO.getTareaDTO().getComentario());
			tareaVO.setResponsableActividad(requestTareaDTO.getTareaDTO().getResponsableActividad());

			responseTareaDTO.setExitoso(tareaService.updateTarea(
					usuarioVO, tareaVO));

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}

		return responseTareaDTO;

	}

	 /**
	  * Metodo que obtiene la lista de tareas en asignacion y ejecutar la primera tarea. 
	  * @param requestInstanciaDTO contiene loa datos para inciar sesion y 
	  * 						   el identficador de instancia.
	  * @return responseTareaDTO contiene una bandera true/false de ejecucion 
	  * 						 correcta de la tarea.
	  */
	@Override
	public ResponseTareaDTO executeTarea(RequestInstanciaDTO requestInstanciaDTO) {
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();
		TareaDTO tareaDTO = new TareaDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			TareaVO tareaVO = tareaService.executeTarea(usuarioVO, requestInstanciaDTO.getInstanciaDTO()
					.getIdInstancia());
			
			if (tareaVO != null) {
				tareaDTO.setEstado(tareaVO.getEstado());
				tareaDTO.setIdTarea(tareaVO.getIdTarea());
				tareaDTO.setNombreTarea(tareaVO.getNombreTarea());
				tareaDTO.setNumeroTarea(tareaVO.getNumeroTarea());
			}
			
			responseTareaDTO.setTareaDTO(tareaDTO);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}
		return responseTareaDTO;
	}	
	
	
	
	
	/**
	 *  Metodo que busca el rol de la primera actividad de una instancia del proceso 
	 * @param requestTareaDTO 	contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene rol y tarea.
	*/	
	@Override
	public ResponseTareaRolDTO findRolDePrimeraTareaDeInstProceso(RequestTareaDTO requestTareaDTO) {
		ResponseTareaRolDTO responseTareaDTO = new ResponseTareaRolDTO();
		try{
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			usuarioVO
					.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());			
			
			InstanciaVO instanciaVO = new InstanciaVO();
			instanciaVO.setIdInstancia(requestTareaDTO.getInstanciaDTO().getIdInstancia());
			
			List<TareaRolDTO> lista = new ArrayList<TareaRolDTO>();
			lista.add(tareaVOToTareaRolDTO(tareaService.findFirstInstanceTaskForProcess(usuarioVO, instanciaVO)));						
			responseTareaDTO.setListaTareaRolDTO(lista);
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}

		return responseTareaDTO;

	}
	
	/**
	 *  Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param requestTareaDTO 	contiene el id de tarea. 
	 * @return ResponseTareaDTO contiene rol y tarea.
	*/	
	@Override
	public ResponseTareaDTO findTareasRemitentes(RequestTareaDTO requestTareaDTO) {
		ResponseTareaDTO responseTareaDTO = new ResponseTareaDTO();
		try{
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestTareaDTO.getUsuarioDTO().getUsuario());
			usuarioVO
					.setPassword(requestTareaDTO.getUsuarioDTO().getPassword());
			TareaVO tareaVO = new TareaVO();
			tareaVO.setIdTarea(requestTareaDTO.getTareaDTO().getIdTarea());
					
			responseTareaDTO.setListaTareaDTO(mapeaTareaAsignadas(tareaService.findTareasRemitentes(usuarioVO, tareaVO)));
			
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseTareaDTO, e);
		}

		return responseTareaDTO;

	}	
}

