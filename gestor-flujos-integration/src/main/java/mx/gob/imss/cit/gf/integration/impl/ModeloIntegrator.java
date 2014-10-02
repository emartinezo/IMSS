/**
 * 
 */
package mx.gob.imss.cit.gf.integration.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.integration.api.IModeloIntegrator;
import mx.gob.imss.cit.gf.integration.domain.ActividadDTO;
import mx.gob.imss.cit.gf.integration.domain.ModeloDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestModeloDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseModeloDTO;
import mx.gob.imss.cit.gf.integration.util.IntegrationResponseExceptionUtil;
import mx.gob.imss.cit.gf.services.IModeloService;
import mx.gob.imss.cit.gf.vo.ActividadVO;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra los servicios referentes al Modelo de Proceso
 * 
 * @author ahernandezd
 *
 */
@Remote(IModeloIntegrator.class)
@Stateless
public class ModeloIntegrator implements IModeloIntegrator {
	/**
	 * aributo de servicio
	 */
	@EJB
	private IModeloService modeloService;
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ModeloIntegrator.class);


	/**
	 * Metodo que extrae el modelo del proceso por identificador de la instancia.
	 * @param requestFInstanciaDTO contiene los datos 
	 * @return ResponseModeloDTO
	 */
	@Override
	public ResponseModeloDTO findModeloProcesoInstancia(
			RequestModeloDTO requestModeloDTO) {
		ResponseModeloDTO responseModeloDTO = new ResponseModeloDTO();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestModeloDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestModeloDTO.getUsuarioDTO().getUsuario());
			ModeloProcesoVO modeloProcesoVO = modeloService.findModeloProcesoInstancia(usuarioVO, requestModeloDTO.getIdInstancia());
			responseModeloDTO.setModeloDTO(executeModeloProceso(modeloProcesoVO));

		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseModeloDTO, e);
		}
		
		return responseModeloDTO;
	}

	/**
	 * Mapea los atributos del modelo del VO a DTO.
	 * @param modeloProcesoVO objeto a mapear.
	 * @return ModeloDTO objeto meapeado.
	 */
	private ModeloDTO executeModeloProceso(ModeloProcesoVO modeloProcesoVO){
		ModeloDTO modeloDTO = new ModeloDTO();
		List<ActividadDTO> listaActividadIniciada = new ArrayList<ActividadDTO>();
		List<ActividadDTO> listaActividadProxima = new ArrayList<ActividadDTO>();
		modeloDTO.setNombreProceso(modeloProcesoVO.getNombreProceso());
		modeloDTO.setProcesoDN(modeloProcesoVO.getProcesoDN());
		
		for (ActividadVO actividadVO : modeloProcesoVO.getListaActividadIniciada()) {
			ActividadDTO actividadDTO = new ActividadDTO();
			actividadDTO.setIdActividad(actividadVO.getIdActividad());
			actividadDTO.setNivelAuditoria(actividadVO.getNivelAuditoria());
			actividadDTO.setNombre(actividadVO.getNombre());
			actividadDTO.setRol(actividadVO.getRol());
			actividadDTO.setTipoElemento(actividadVO.getTipoElemento());
			listaActividadIniciada.add(actividadDTO);
		}
		for (ActividadVO actividadVO : modeloProcesoVO.getListaActividadProxima()) {
			ActividadDTO actividadDTO = new ActividadDTO();
			actividadDTO.setIdActividad(actividadVO.getIdActividad());
			actividadDTO.setNombre(actividadVO.getNombre());
			actividadDTO.setTipoElemento(actividadVO.getTipoElemento());
			listaActividadProxima.add(actividadDTO);
		}
		
		modeloDTO.setListaActividadIniciada(listaActividadIniciada);
		modeloDTO.setListaActividadProxima(listaActividadProxima);
		return modeloDTO;
	}
	/**
	 * Metodo que obtiene todas los modelos de la instancias asociadas al proceso expuesto.
	 * @param requestModeloDTO contiene los datos de usuario y procesoDN.
	 * @return ResponseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	@Override
	public ResponseModeloDTO findModeloProcesoIdentificador(
			RequestModeloDTO requestModeloDTO) {
		ResponseModeloDTO responseModeloDTO = new ResponseModeloDTO();
		List<ModeloDTO> listaModeloDTO = new ArrayList<ModeloDTO>();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestModeloDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestModeloDTO.getUsuarioDTO().getUsuario());
			List<ModeloProcesoVO> listaModeloProcesoVO = modeloService.findModeloProcesoIdentificador(usuarioVO, 
					requestModeloDTO.getProcesoDN());
			
			if (listaModeloProcesoVO!=null) {
				for (ModeloProcesoVO modeloProcesoVO : listaModeloProcesoVO) {
					ModeloDTO modeloDTO = executeModeloProceso(modeloProcesoVO);
					modeloDTO.setIdInstancia(modeloProcesoVO.getIdInstancia());
					listaModeloDTO.add(modeloDTO);
				}
			}
			responseModeloDTO.setListaModeloDTO(listaModeloDTO);
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseModeloDTO, e);
		}
		
		return responseModeloDTO;
	}

	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param requestModeloDTO contiene los datos de usuario y tipo.
	 * @return ResponseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	@Override
	public ResponseModeloDTO findModeloProcesoTipo(
			RequestModeloDTO requestModeloDTO) {
		ResponseModeloDTO responseModeloDTO = new ResponseModeloDTO();
		List<ModeloDTO> listaModeloDTO = new ArrayList<ModeloDTO>();
		try {
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestModeloDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestModeloDTO.getUsuarioDTO().getUsuario());
			List<ModeloProcesoVO> listaModeloProcesoVO = modeloService.findModeloProcesoTipo(usuarioVO, 
					requestModeloDTO.getTipo());
			
			if (listaModeloProcesoVO!=null) {
				for (ModeloProcesoVO modeloProcesoVO : listaModeloProcesoVO) {
					ModeloDTO modeloDTO = new ModeloDTO();
					modeloDTO.setInstancias(modeloProcesoVO.getInstancias());
					modeloDTO.setNombreProceso(modeloProcesoVO.getNombreProceso());
					modeloDTO.setProcesoDN(modeloProcesoVO.getProcesoDN());
					listaModeloDTO.add(modeloDTO);
				}
			}
			responseModeloDTO.setListaModeloDTO(listaModeloDTO);
			
		}catch (Exception e) {
			LOG.error(e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseModeloDTO, e);
		}
		
		return responseModeloDTO;
	}
}
