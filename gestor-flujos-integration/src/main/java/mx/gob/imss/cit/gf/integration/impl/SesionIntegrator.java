package mx.gob.imss.cit.gf.integration.impl;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.integration.api.ISesionIntegrator;
import mx.gob.imss.cit.gf.integration.domain.SesionDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestSesionDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseSesionDTO;
import mx.gob.imss.cit.gf.integration.util.IntegrationResponseExceptionUtil;
import mx.gob.imss.cit.gf.services.ISesionService;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que integra los servicios referentes a la sesion
 * @author ajfuentes
 *
 */
@Remote(ISesionIntegrator.class)
@Stateless
public class SesionIntegrator implements ISesionIntegrator {

	/**
	 * EJB de sesion
	 */
	@EJB
	private ISesionService sesionService;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory	.getLogger(SesionIntegrator.class);

	
	/**
	 * Metodo que obtiene la sesion del BPM.
	 */
	public ResponseSesionDTO getSesion(RequestSesionDTO requestSesionDTO){


		ResponseSesionDTO responseSesioDTO = new ResponseSesionDTO();
		try{
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setPassword(requestSesionDTO.getUsuarioDTO().getPassword());
			usuarioVO.setUsuario(requestSesionDTO.getUsuarioDTO().getUsuario());

			
			SesionDTO sesionDTO=new SesionDTO();
			responseSesioDTO.setSesionDTO(sesionDTO);
			
			SesionVO sesionVO =sesionService.getSesion(usuarioVO);
			responseSesioDTO.getSesionDTO().setToken(sesionVO.getToken());
			responseSesioDTO.getSesionDTO().setUsuario(sesionVO.getUsuario());
			
		} catch (Exception e) {
			LOG.error("ERROR - getSesion: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseSesioDTO, e);
		}
		return responseSesioDTO;
	}


	/**
	 * Metodo que obtiene la sesion del BPM.
	 */
	@Override
	public ResponseSesionDTO deleteSesion(RequestSesionDTO requestSesionDTO) {
		

		ResponseSesionDTO responseSesioDTO = new ResponseSesionDTO();
		try{
			UsuarioVO usuarioVO = new UsuarioVO();
			usuarioVO.setUsuario(requestSesionDTO.getUsuarioDTO().getUsuario());
			
			sesionService.deleteSesion(usuarioVO);
			responseSesioDTO.setExitoso(Boolean.TRUE);

		}catch (Exception e) {
			LOG.error("ERROR - deleteSesion: "+e.getMessage(), e);
			IntegrationResponseExceptionUtil.setResponseError(responseSesioDTO, e);
		}
		
		return responseSesioDTO;
	}

}
