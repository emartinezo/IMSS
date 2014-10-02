package mx.gob.imss.cit.gf.adapter.util;

import java.util.ResourceBundle;

import mx.gob.imss.cit.gf.adapter.constant.AdapterBPMConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropertiesAdapterUtil {
	

	/**
	 * Constructor privado
	 */
	private PropertiesAdapterUtil(){
		
	}
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesAdapterUtil.class);
	/**
	 * Utileria para cargar el properties
	 */
	private static final ResourceBundle MSG =ResourceBundle.getBundle(AdapterBPMConstants.FILE_ADAPTER_CONFIG);
	
	
	/**
	 * Metodo que obtiene el valor del properties
	 * @param key
	 * @return
	 */

	public static String getMessage(String key){	
		String mensaje="";
		try{
			mensaje=MSG.getString(key);
		}catch(Exception e){
			LOG.info("No se encontro la propiedad con el key "+key);
			mensaje="";
		}
		return mensaje;
	}
}
