package mx.gob.imss.cit.gf.integration.domain;

/**
 * @author dlopezf
 */
public class TareaRolDTO extends BaseDTO{

	/**
	 * Identificador de la tarea.
	 */
	private String idTarea;
	/**
	 * Numero de la tarea.
	 */
	private Integer numeroTarea;

	/**
	 * Nombre de la tarea.
	 */
	private String nombreTarea;
	
	/**
	 * Descripcion de rol asignado a la  tarea.
	 */
	private String rol;
	
	/**
	 * @return the idTarea
	 */
	public String getIdTarea() {
		return idTarea;
	}
	/**
	 * @param idTarea the idTarea to set
	 */
	public void setIdTarea(String idTarea) {
		this.idTarea = idTarea;
	}
	/**
	 * @return the numeroTarea
	 */
	public Integer getNumeroTarea() {
		return numeroTarea;
	}
	/**
	 * @param numeroTarea the numeroTarea to set
	 */
	public void setNumeroTarea(Integer numeroTarea) {
		this.numeroTarea = numeroTarea;
	}

	/**
	 * @return the nombreTarea
	 */
	public String getNombreTarea() {
		return nombreTarea;
	}
	/**
	 * @param nombreTarea the nombreTarea to set
	 */
	public void setNombreTarea(String nombreTarea) {
		this.nombreTarea = nombreTarea;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}
	
	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

}
