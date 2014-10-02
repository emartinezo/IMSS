package mx.gob.imss.cit.gf.integration.dto;

import java.io.Serializable;

/**
 * Clase base de los dto de entrada
 * @author ajfuentes
 *
 */
public class BaseResponseDTO implements Serializable{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * codigo de la excepcion
	 */
	protected Integer excepcionCodigo;
	
	/**
	 * mensaje de la excepcion
	 */
	protected String excepcionMensaje;
	
	/**
	 * causa original de la excepcion en el adapter
	 */
	protected String excepcionCausa;
	
	/**
	 * si fue exitoso
	 */
	protected boolean exitoso=true;

	/**
	 * @return the excepcionCodigo
	 */
	public Integer getExcepcionCodigo() {
		return excepcionCodigo;
	}

	/**
	 * @param excepcionCodigo the excepcionCodigo to set
	 */
	public void setExcepcionCodigo(Integer excepcionCodigo) {
		this.excepcionCodigo = excepcionCodigo;
	}

	/**
	 * @return the excepcionMensaje
	 */
	public String getExcepcionMensaje() {
		return excepcionMensaje;
	}

	/**
	 * @param excepcionMensaje the excepcionMensaje to set
	 */
	public void setExcepcionMensaje(String excepcionMensaje) {
		this.excepcionMensaje = excepcionMensaje;
	}

	/**
	 * @return the exitoso
	 */
	public boolean isExitoso() {
		return exitoso;
	}

	/**
	 * @param exitoso the exitoso to set
	 */
	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	/**
	 * @return the excepcionCausa
	 */
	public String getExcepcionCausa() {
		return excepcionCausa;
	}

	/**
	 * @param excepcionCausa the excepcionCausa to set
	 */
	public void setExcepcionCausa(String excepcionCausa) {
		this.excepcionCausa = excepcionCausa;
	}

}
