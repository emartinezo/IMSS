package mx.gob.imss.cit.gf.services.exception;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;

/**
 * Clase de excepcion para los servicios del gestor de flujos
 * 
 * @author Admin
 *
 */
public class GestorFlujosServicesException extends GestorFlujosException {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor default de la clase
	 */
	public GestorFlujosServicesException(){
		super();
	}
	
	/**
	 * Constructor de la clase que incluye mensaje de error.
	 * 
	 * @param message
	 */
	public GestorFlujosServicesException(String message) {
		super(message);
	}	
	
	/**
	 * Constructor de la clase que incluye mensaje de error y causa del error.
	 * 
	 * @param message
	 * @param cause
	 */
	public GestorFlujosServicesException(String message, Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * Constructor de la clase que incluye la causa del error.
	 * 
	 * @param cause
	 */
	public GestorFlujosServicesException(Throwable cause){
		super(cause);
	}
	
}
	
	
	
