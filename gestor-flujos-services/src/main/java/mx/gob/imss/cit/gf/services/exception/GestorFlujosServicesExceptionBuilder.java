package mx.gob.imss.cit.gf.services.exception;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * Clase que crea las excepciones de tipo GestorFlujosServicesException
 * 
 * @author Admin
 *
 */

public final class GestorFlujosServicesExceptionBuilder {
	
	private static final int ERROR_DESCONOCIDO = 0;
	/**
	 * Objeto para recuperar el archivo properties de los mensajes de error
	 */
	private static final ResourceBundle MSG_ERROR =ResourceBundle.getBundle("service-exception-config");
	/**
	 * Prefijo de la cadena de errores
	 */
	private static final String PREFIJO_ERROR = "Error.";
	
	private static final Logger LOG = LoggerFactory.getLogger(GestorFlujosServicesExceptionBuilder.class);

/**
 * Constructor por default
 */
private GestorFlujosServicesExceptionBuilder(){
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado
 * 
 * @param GestorFlujosServicesCodeExceptionEnum code
 * @return GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( GestorFlujosServicesCodeExceptionEnum code )
{
  return build( code.getId(), null, null, null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado
 * 
 * @param code C&oacute;digo de Error
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( int code )
{
  return build( code, null, null, null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
 * 
 * @param code C&oacute;digo de Error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( int code, Object[] args )
{
  return build( code, null, args, null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, anexa los par&aacute;metros
 * 
 * @param code C&oacute;digo de Error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( GestorFlujosServicesCodeExceptionEnum code, Object[] args )
{
  return build( code.getId(), null, args, null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, agrega la causa de error
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( int code, Throwable cause )
{
  return build( code, cause, null, null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, agrega la causa de error
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( GestorFlujosServicesCodeExceptionEnum code, Throwable cause )
{
  return build( code.getId(), cause, null, null );
}


public static GestorFlujosServicesException build(int code, Throwable cause ,String descriptionError){
	
	LOG.info("GestorFlujosServicesExceptionBuilder - build - code: " + code + " - descriptionError: " + descriptionError);
	
	return build(code, cause, null, descriptionError);
}


/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
 * par&aacute;metros de
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( GestorFlujosServicesCodeExceptionEnum code, Throwable cause, Object[] args )
{
  return build( code.getId(), cause, args , null );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con el c&oacute;digo de error indicado, agrega la causa de error, agrega los
 * par&aacute;metros de
 * 
 * @param code C&oacute;digo de Error
 * @param cause Causa de error
 * @param args Par&aacute;metros
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( int code, Throwable cause, Object[] args, String descriptionError  )
{
	GestorFlujosServicesException gestorFlujosServicesException = null;
  if( cause != null ){
	  gestorFlujosServicesException = new GestorFlujosServicesException( cause.getMessage(), cause );
  }else{
	  gestorFlujosServicesException = new GestorFlujosServicesException();
  }

  gestorFlujosServicesException.setCode( code );
  
  if(descriptionError == null){
	  gestorFlujosServicesException.setDescription(MSG_ERROR.getString(PREFIJO_ERROR + code));
  }else{
	  gestorFlujosServicesException.setDescription(descriptionError);
  }
  
  LOG.info("GestorFlujosServicesExceptionBuilder - error descripcion: " +gestorFlujosServicesException.getDescription() + " - causa:" + cause );
  
  return gestorFlujosServicesException;
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con un c&oacute;digo de error 0 (desconocido)
 * 
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build()
{
  return build( ERROR_DESCONOCIDO );
}

/**
 * Genera una excepci&oacute;n GestorFlujosServicesException con un c&oacute;digo de error 0 (desconocido) y agrega la causa de error
 * 
 * @param cause Causa de error
 * @return Excepci&oacute;n GestorFlujosServicesException
 */
public static GestorFlujosServicesException build( Throwable cause )
{
  return build( ERROR_DESCONOCIDO, cause );
}	

}
