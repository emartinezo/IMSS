package mx.gob.imss.cit.gf.adapter.exception;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Clase que crea las excepciones de tipo AdapterBPMException
 * 
 * @author Admin
 *
 */
public final class AdapterBPMExceptionBuilder {
	
	private static final int ERROR_DESCONOCIDO = 0;
	/**
	 * Objeto para recuperar el archivo properties de los mensajes de error
	 */
	private static final ResourceBundle MSG_ERROR =ResourceBundle.getBundle("adapter-exception-config");
	/**
	 * Prefijo de la cadena de errores
	 */
	private static final String PREFIJO_ERROR = "Error.";	

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AdapterBPMExceptionBuilder.class);
	
	  /**
	   * Constructor por default
	   */
	  private AdapterBPMExceptionBuilder(){
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado
	   * 
	   * @param AdapterBPMCodeExceptionEnum code
	   * @return AdapterBPMException
	   */
	  public static AdapterBPMException build( AdapterBPMCodeExceptionEnum code )
	  {
	    return build( code.getId(), null, null );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado
	   * 
	   * @param code C&oacute;digo de Error
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( int code )
	  {
	    return build( code, null, null );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
	   * 
	   * @param code C&oacute;digo de Error
	   * @param args Par&aacute;metros
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( int code, Object[] args )
	  {
	    return build( code, null, args );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
	   * 
	   * @param code C&oacute;digo de Error
	   * @param args Par&aacute;metros
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( AdapterBPMCodeExceptionEnum code, Object[] args )
	  {
	    return build( code.getId(), null, args );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, agrega la causa de error
	   * 
	   * @param code C&oacute;digo de Error
	   * @param cause Causa de error
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( int code, Throwable cause )
	  {
	    return build( code, cause, null );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, agrega la causa de error
	   * 
	   * @param code C&oacute;digo de Error
	   * @param cause Causa de error
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( AdapterBPMCodeExceptionEnum code, Throwable cause )
	  {
		  
		LOG.info("AdapterBPMExceptionBuilder - build - code: " + code);
	    return build( code.getId(), cause, null );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
	   * par&aacute;metros de
	   * 
	   * @param code C&oacute;digo de Error
	   * @param cause Causa de error
	   * @param args Par&aacute;metros
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( AdapterBPMCodeExceptionEnum code, Throwable cause, Object[] args )
	  {
	    return build( code.getId(), cause, args );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
	   * par&aacute;metros de
	   * 
	   * @param code C&oacute;digo de Error
	   * @param cause Causa de error
	   * @param args Par&aacute;metros
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( int code, Throwable cause, Object[] args )
	  {
		  AdapterBPMException adapterBPMException = null;
	    if( cause != null ){
	    	adapterBPMException = new AdapterBPMException( cause.getMessage(), cause );
	    }else{
	    	adapterBPMException = new AdapterBPMException();
	    }

	    adapterBPMException.setCode( code );
	    adapterBPMException.setDescription(MSG_ERROR.getString(PREFIJO_ERROR + code));

	    LOG.info("AdapterBPMExceptionBuilder - error descripcion: " +adapterBPMException.getDescription() + " - causa:" + cause );
	    
	    return adapterBPMException;
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con un c&oacute;digo de error 0 (desconocido)
	   * 
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build()
	  {
	    return build( ERROR_DESCONOCIDO );
	  }

	  /**
	   * Genera una excepci&oacute;n AdapterBPMException con un c&oacute;digo de error 0 (desconocido) y agrega la causa de error
	   * 
	   * @param cause Causa de error
	   * @return Excepci&oacute;n AdapterBPMException
	   */
	  public static AdapterBPMException build( Throwable cause )
	  {
	    return build( ERROR_DESCONOCIDO, cause );
	  }	

}
