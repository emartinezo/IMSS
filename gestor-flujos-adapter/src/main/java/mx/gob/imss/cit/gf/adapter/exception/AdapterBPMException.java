package mx.gob.imss.cit.gf.adapter.exception;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;


/**
 * Clase de excepcion para el AdapterBPM
 * @author Admin
 *
 */

public class AdapterBPMException extends GestorFlujosException {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor default de la clase
	 */
	public AdapterBPMException(){
		super();
	}

	/**
	 * Constructor de la clase que incluye mensaje de error.
	 * 
	 * @param message
	 */
	public AdapterBPMException(String message) {
		super(message);
	}

	/**
	 * Constructor de la clase que incluye mensaje de error y causa del error.
	 * 
	 * @param message
	 * @param cause
	 */
	public AdapterBPMException(String message, Throwable cause) {
		super(message,cause);
	}

	/**
	 * Constructor de la clase que incluye la causa del error.
	 * 
	 * @param cause
	 */
	public AdapterBPMException(Throwable cause){
		super(cause);
	}

}

