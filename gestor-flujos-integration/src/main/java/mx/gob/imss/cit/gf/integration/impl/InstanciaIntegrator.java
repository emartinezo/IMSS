/**
 * 
 */
package mx.gob.imss.cit.gf.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.integration.api.IInstanciaIntegrator;
import mx.gob.imss.cit.gf.integration.domain.InstanciaDTO;
import mx.gob.imss.cit.gf.integration.domain.RolDTO;
import mx.gob.imss.cit.gf.integration.domain.TareaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestResponsablesInstDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaRolesDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseResponsablesInstDTO;
import mx.gob.imss.cit.gf.integration.util.IntegrationResponseExceptionUtil;
import mx.gob.imss.cit.gf.services.IInstanciaService;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Clase que integra los servicios referentes a la instancia
 * @author ahernandezd
 *
 */
@Remote(IInstanciaIntegrator.class)
@Stateless
public class InstanciaIntegrator implements IInstanciaIntegrator {

	/**
	 * aributo de servicio
	 */
	@EJB
	private IInstanciaService instanciaService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(InstanciaIntegrator.class);

	/**
	 * Metodo que realiza la consulta detalle de instancias.
	 */
	@Override
	public ResponseInstanciaDTO findInstanciaDetalle(RequestInstanciaDTO requestInstanciaDTO){

		List <InstanciaVO> listaInstanciaVO = null; 
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());
			InstanciaVO instanciaVO = new InstanciaVO();
			instanciaVO.setIdInstancia(requestInstanciaDTO.getInstanciaDTO().getIdInstancia());
			instanciaVO.setEstado(requestInstanciaDTO.getInstanciaDTO().getEstado());
			instanciaVO.setFechaCreacion(requestInstanciaDTO.getInstanciaDTO().getFechaCreacion());
			instanciaVO.setUsuarioCreador(requestInstanciaDTO.getInstanciaDTO().getUsuarioCreador());

			listaInstanciaVO = instanciaService.findInstanciaDetalle(instanciaVO, usuarioVO);
			responseInstanciaDTO.setListaInstanciaDTO(executeMapearInstanciaDetalle(listaInstanciaVO));

		}catch (Exception e) {
			LOG.error("ERROR - findInstanciaDetalle: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		
		return responseInstanciaDTO;
	}
	
	
	
	/**
	 *  Metodo que genera la instancia de un proceso.
	 */

	@Override
	public ResponseInstanciaDTO executeCrearInstanciaProceso(RequestInstanciaDTO requestInstanciaDTO){
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		String idInstancia = null;
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());
			idInstancia = instanciaService.executeCrearInstanciaProceso(usuarioVO, 
					requestInstanciaDTO.getInstanciaDTO().getProcesoDN());
			instanciaDTO.setIdInstancia(idInstancia);
			
			LOG.info("InstanciaIntegrator - idInstancia:" + instanciaDTO.getIdInstancia());
			
			responseInstanciaDTO.setInstanciaDTO(instanciaDTO);
			
		}catch (Exception e) {
			LOG.error("ERROR - executeCrearInstanciaProceso: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		return responseInstanciaDTO;
	}
	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (InstanciaVO a InstanciaDTO).
	 * @param listaInstanciaVO lista de instancias a mapeadas.
	 * @return listaInstanciaDTO lista de instancias mapeadas.
	 */
	private  List<InstanciaDTO> executeMapearInstanciaDetalle(List <InstanciaVO> listaInstanciaVO){
		List <InstanciaDTO> listaInstancia = null;
		
		if(listaInstanciaVO!= null){
			listaInstancia = new ArrayList<InstanciaDTO>();
			
			for (InstanciaVO instanciavo : listaInstanciaVO) {
				InstanciaDTO instanciaDTO = executeMapearInstanciaVoToDTO(instanciavo);
			
				listaInstancia.add(instanciaDTO);
			}
		}
		return listaInstancia;
	}
	
	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (InstanciaVO a InstanciaDTO).
	 * @param instanciavo
	 * @return
	 */
	private InstanciaDTO executeMapearInstanciaVoToDTO(InstanciaVO instanciavo) {
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		instanciaDTO.setEstado(instanciavo.getEstado());
		instanciaDTO.setIdProceso(instanciavo.getIdProceso());
		instanciaDTO.setFechaCreacion(instanciavo.getFechaCreacion());
		instanciaDTO.setIdInstancia(instanciavo.getIdInstancia());						
		instanciaDTO.setNombreProceso(instanciavo.getNombreProceso());
		instanciaDTO.setProcesoDN(instanciavo.getProcesoDN());
		instanciaDTO.setUsuarioCreador(instanciavo.getUsuarioCreador());
		instanciaDTO.setRol(instanciavo.getRol());
		return instanciaDTO;
	}
	 /**
	  * Metodo que realiza la consulta el detalle de las instancias y que obtiene las tareas.
	  * 
	  */
	@Override
	public ResponseInstanciaDTO findInstancias(
			RequestInstanciaDTO requestInstanciaDTO) {
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		List <InstanciaVO> listaInstanciaVO = null;
		List <InstanciaDTO> listaInstanciaDTO = null;
		try {
			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());
			InstanciaVO instanciaVO = new InstanciaVO();
			instanciaVO.setIdInstancia(requestInstanciaDTO.getInstanciaDTO().getIdInstancia());
			instanciaVO.setEstado(requestInstanciaDTO.getInstanciaDTO().getEstado());
			instanciaVO.setFechaCreacion(requestInstanciaDTO.getInstanciaDTO().getFechaCreacion());
			instanciaVO.setUsuarioCreador(requestInstanciaDTO.getInstanciaDTO().getUsuarioCreador());
			listaInstanciaVO = instanciaService.findInstancias(instanciaVO, usuarioVO);
			listaInstanciaDTO = executeMapearInstancias(listaInstanciaVO);
			responseInstanciaDTO.setListaInstanciaDTO(listaInstanciaDTO);
			
		}catch (Exception e) {
			LOG.error("ERROR - findInstancias: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		return responseInstanciaDTO;
	}
	
	/**
	 * Metodo que realiza el mapeo de la consulta Instancias (InstanciaVO a InstanciaDTO).
	 * @param listaInstanciaVO lista de instancias a mapeadas.
	 * @return listaInstanciaDTO lista de instancias mapeadas.
	 */
	private  List<InstanciaDTO> executeMapearInstancias(List <InstanciaVO> listaInstancias){
		List <InstanciaDTO> listaInstanciaDTO = null;
		if(listaInstancias!=null){
			listaInstanciaDTO = new ArrayList<InstanciaDTO>();
			
			for (InstanciaVO instancia : listaInstancias) {
				InstanciaDTO instanciaDTO = executeMapearInstanciaVoToDTO(instancia);
				instanciaDTO.setListaTareas(executeMapearTarea(instancia.getListaTareas()));
				listaInstanciaDTO.add(instanciaDTO);
				
			}
		}
		return listaInstanciaDTO;
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
		List<TareaDTO> listaTareaDTO = null;
		if (listaTareaVO != null) {
			listaTareaDTO = new ArrayList<TareaDTO>();
			for (TareaVO tareaVO : listaTareaVO) {
				TareaDTO tareaDTO = new TareaDTO();
				tareaDTO.setEstado(tareaVO.getEstado());
				tareaDTO.setFechaFinEjecucion(tareaVO.getFechaFinEjecucion());
				tareaDTO.setFechaInicio(tareaVO.getFechaInicio());
				tareaDTO.setFechaCompromiso(tareaVO.getFechaCompromiso());
				tareaDTO.setFechaInicioEjecucion(tareaVO.getFechaInicioEjecucion());
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
	 * Metodo que cancela una instancia en base al id de instancia del proceso
	 */
	@Override
	public ResponseInstanciaDTO executeCancelarInstanciaProceso(
			RequestInstanciaDTO requestCancelaInstanciaDTO) {
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		try{
			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestCancelaInstanciaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestCancelaInstanciaDTO.getUsuarioDTO().getUsuario());
			instanciaService.executeCancelarInstanciaProceso(usuarioVO, requestCancelaInstanciaDTO.getInstanciaDTO().getIdProceso().toString());						
			
		}catch (Exception e) {
			LOG.error("ERROR - executeCancelarInstanciaProceso: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		return responseInstanciaDTO;
	}
	
	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * @param requestResponsablesInstDTO
	 * @return ResponseResponsablesInstDTO 
	 * 
	 */
	@Override
	public ResponseResponsablesInstDTO findResponsablesInstancias(RequestResponsablesInstDTO requestResponsablesInstDTO) {
		ResponseResponsablesInstDTO responseResponsablesInstDTO = new ResponseResponsablesInstDTO();
		try{
			
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestResponsablesInstDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestResponsablesInstDTO.getUsuarioDTO().getUsuario());
			List<String> responsables = instanciaService.findResponsablesInstancias(requestResponsablesInstDTO.getIdInstanciasProcesos(), usuarioVO);
			responseResponsablesInstDTO.setResponsables(responsables);
			
		} catch (Exception e) {
			LOG.error("ERROR - findResponsablesInstancias: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseResponsablesInstDTO, e);
		}
		
		return responseResponsablesInstDTO;
	}

	
	/**
	* Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	* @param requestResponsablesInstDTO	Objeto que contiene los datos de sesion y las propiedades del proceso.
	* @return ResponseInstanciaRolesDTO lista de Roles
	*/		
	@Override
	public ResponseInstanciaDTO updateInstanciaDeProceso(RequestInstanciaDTO requestResponsablesDTO) {
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		try{
				
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestResponsablesDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestResponsablesDTO.getUsuarioDTO().getUsuario());
			InstanciaVO instanciaVO= new InstanciaVO();
			instanciaVO.setEstado(requestResponsablesDTO.getInstanciaDTO().getEstado());
			instanciaVO.setIdInstancia(requestResponsablesDTO.getInstanciaDTO().getIdInstancia());

			responseInstanciaDTO.setExitoso(instanciaService.updateInstancia(usuarioVO, instanciaVO));
			
		} catch (Exception e) {
			LOG.error("ERROR - updateInstanciaDeProceso: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		return responseInstanciaDTO;
	}
	
	
	 /**
	* Metodo que permite consultar roles de una instancia de proceso
	* @param requestResponsablesInstDTO	Objeto que contiene los datos de sesion y las propiedades del proceso.
	* @return ResponseInstanciaRolesDTO lista de Roles
	*/	
	@Override
	public ResponseInstanciaRolesDTO findRolesByInstaceProcess(RequestInstanciaDTO requestResponsablesDTO){
		ResponseInstanciaRolesDTO responseInstanciaRolesDTO = new ResponseInstanciaRolesDTO();
		List<RolDTO> rolesDTO=new ArrayList<RolDTO>(); 
		try{
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestResponsablesDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestResponsablesDTO.getUsuarioDTO().getUsuario());
			InstanciaVO instanciaVO= new InstanciaVO();
			instanciaVO.setIdInstancia(requestResponsablesDTO.getInstanciaDTO().getIdInstancia());
			
			List<RolVO> rolesVO=instanciaService.findRolesByInstaceProcess(usuarioVO, instanciaVO);
			
			for (RolVO rol:rolesVO){
				RolDTO rolDTO= new RolDTO();
				rolDTO.setRolName(rol.getRolName());
				rolesDTO.add(rolDTO);
			}
			
			responseInstanciaRolesDTO.setRolesDTO(rolesDTO);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaRolesDTO, e);
		}	
		
		return responseInstanciaRolesDTO;
	}
	
	/**
	 *  Metodo que genera la instancia de un proceso.
	 */
	@Override
	public ResponseInstanciaDTO executeInitInstanciaProceso(RequestInstanciaDTO requestInstanciaDTO) {
		InstanciaVO instanciaVO = null; 
		ResponseInstanciaDTO responseInstanciaDTO = new ResponseInstanciaDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestInstanciaDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestInstanciaDTO.getUsuarioDTO().getUsuario());			

			instanciaVO = instanciaService.executeInitInstanciaProceso(usuarioVO, requestInstanciaDTO.getInstanciaDTO().getProcesoDN());
			responseInstanciaDTO.setInstanciaDTO(executeMapearInstanciaVoToDTO(instanciaVO));

		}catch (Exception e) {
			LOG.error("ERROR - findInstanciaDetalle: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseInstanciaDTO, e);
		}
		
		return responseInstanciaDTO;
	}
}
