package mx.gob.imss.cit.gf.exception;

import java.util.Date;

/**
 * Clase de excepcion para  del gestor de flujos
 * 
 * @author Admin
 *
 */
public class GestorFlujosException extends Exception {
	
	/**
	 * Serial number 
	 */
	private static final long serialVersionUID = 1L;
	/** ID de error */
	protected long id;

	/** C&oacute;digo de error */
	protected int code;

	/** Descripci&oacute;n de error */
	protected String description;

	/** Fecha de error */
	protected Date date;

	/** Argumentos */
	protected Object[] args;	
	
	/**
	 * Constructor default de la clase
	 */
	public GestorFlujosException(){
		super();
	}
	
	/**
	 * Constructor de la clase que incluye mensaje de error.
	 * 
	 * @param message
	 */
	public GestorFlujosException(String message) {
		super(message);
	}	
	
	/**
	 * Constructor de la clase que incluye mensaje de error y causa del error.
	 * 
	 * @param message
	 * @param cause
	 */
	public GestorFlujosException(String message, Throwable cause) {
		super(message,cause);
	}
	
	/**
	 * Constructor de la clase que incluye mensaje de error y causa del error.
	 * 
	 * @param message
	 * @param cause
	 */
	public GestorFlujosException(int code,String message) {
		super(message);
		this.code=code;
		this.description=message;
		
	}
	
	/**
	 * Constructor de la clase que incluye la causa del error.
	 * 
	 * @param cause
	 */
	public GestorFlujosException(Throwable cause){
		super(cause);
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date != null ? (Date) date.clone() : null;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date != null ? (Date) date.clone() : null;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the args
	 */
	public Object[] getArgs() {
		return args != null ? args.clone() : new Object[0];
	}

	/**
	 * @param args
	 *            the args to set
	 */
	public void setArgs(Object[] args) {
		this.args = args != null ? args.clone() : null;
	}
}
