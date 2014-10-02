package mx.gob.imss.cit.gf.integration.util;

import mx.gob.imss.cit.gf.integration.dto.BaseResponseDTO;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase encargada de setear mensajes de salida en los response de los metodos
 * en la capa de Integrator
 * @author Admin
 *
 */
public final class IntegrationResponseExceptionUtil {

	/**
	 * Constructor privado
	 */
	private IntegrationResponseExceptionUtil(){}
	/**
	 * Constante que se usa cuando el mensaje del Exception es nulo
	 */
	private static final String ERROR_DESCONOCIDO = "ERROR DESCONOCIDO";
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(IntegrationResponseExceptionUtil.class);	

	/**
	 * Metodo que setea los atributos:
	 *  - ExceptionMensaje
	 *  - ExceptionCodigo
	 *  - ExceptionCausa
	 * del response para los metodos de la capa Integrator   
	 *  
	 * @param response
	 * @param e
	 */
	public static void setResponseError( Object response, Exception e){		
		if(response != null){
			BaseResponseDTO rspDTO = (BaseResponseDTO) response;		
			if(e instanceof GestorFlujosServicesException){
				GestorFlujosServicesException gfse = (GestorFlujosServicesException)e;
				rspDTO.setExcepcionMensaje(gfse.getDescription());
				rspDTO.setExcepcionCodigo(gfse.getCode());
				rspDTO.setExcepcionCausa(gfse.getMessage());			
			}else if(e.getMessage() != null){
				rspDTO.setExcepcionMensaje(ERROR_DESCONOCIDO);
			}
			rspDTO.setExitoso(false);			
			LOG.info("setResponseError - excepcionMensaje: " + rspDTO.getExcepcionMensaje());
			LOG.info("setResponseError - excepcionCodigo: " + rspDTO.getExcepcionCodigo());
			LOG.info("setResponseError - excepcionCausa: " + rspDTO.getExcepcionCausa());			
		}
	}
	
	
}
