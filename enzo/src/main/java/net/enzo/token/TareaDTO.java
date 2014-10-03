/**
 * 
 */
package net.enzo.token;

import java.util.List;



/**
 * @author ahernandezd
 *
 */
public class TareaDTO  extends BaseDTO{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identificador de la tarea.
	 */
	private String idTarea;
	/**
	 * Numero de la tarea.
	 */
	private Integer numeroTarea;
	/**
	 * Estado de la tarea.
	 */
	private String estado;
	/**
	 * Nombre de la tarea.
	 */
	private String nombreTarea;
	/**
	 * Usuario aosiciado a la tarea.
	 */
	private String usuarioCreador;
	/**
	 * Prioridad de la tarea.
	 */
	private Integer prioridad;
	/**
	 * Url de la tarea.
	 */
	private String urlTarea;
	/**
	 * Fecha de inicio.
	 */
	private String fechaInicio;
	/**
	 * Fecha fechaFinEjecucion.
	 */
	private String fechaFinEjecucion;
	/**
	 * Fecha fechaInicioEjecucion.
	 */
	private String fechaInicioEjecucion;
	/**
	 * Fecha fechaCompromiso.
	 */
	private String fechaCompromiso;
	/**
	 * Descripcion de rol asignado al proceso.
	 */
	private String rol;

	/**
	 * Descripcion de rol asignado al proceso.
	 */
	private String comentario;
	/**
	 * Lista de  responsables de la actividad.
	 */
	private List<String> responsableActividad;
				
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
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
	 * @return the usuarioCreador
	 */
	public String getUsuarioCreador() {
		return usuarioCreador;
	}
	/**
	 * @param usuarioCreador the usuarioCreador to set
	 */
	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}
	/**
	 * @return the prioridad
	 */
	public Integer getPrioridad() {
		return prioridad;
	}
	/**
	 * @param prioridad the prioridad to set
	 */
	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}
	/**
	 * @return the urlTarea
	 */
	public String getUrlTarea() {
		return urlTarea;
	}
	/**
	 * @param urlTarea the urlTarea to set
	 */
	public void setUrlTarea(String urlTarea) {
		this.urlTarea = urlTarea;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio=fechaInicio ;
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
	/**
	 * @return the fechaCompromiso
	 */	
	public String getFechaCompromiso() {
		return fechaCompromiso;
	}
	/**
	 * @param fechaCompromiso the fechaCompromiso to set
	 */	
	public void setFechaCompromiso(String fechaCompromiso) {
		this.fechaCompromiso=fechaCompromiso;
	}
	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}
	/**
	 * @param comentario the comentario to set
	 */	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	/**
	 * @return the fechaFinEjecucion
	 */
	public String getFechaFinEjecucion() {
		return fechaFinEjecucion;
	}
	/**
	 * @param fechaFinEjecucion the fechaFinEjecucion to set
	 */
	public void setFechaFinEjecucion(String fechaFinEjecucion) {
		this.fechaFinEjecucion=fechaFinEjecucion;
	}
	/**
	 * @return the fechaInicioEjecucion
	 */
	public String getFechaInicioEjecucion() {
		return fechaInicioEjecucion;
	}
	/**
	 * @param fechaInicioEjecucion the fechaInicioEjecucion to set
	 */
	public void setFechaInicioEjecucion(String fechaInicioEjecucion) {
		this.fechaInicioEjecucion = fechaInicioEjecucion;
	}
	/**
	 * @return the responsableActividad
	 */
	public List<String> getResponsableActividad() {
		return responsableActividad;
	}
	/**
	 * @param responsableActividad the responsableActividad to set
	 */
	public void setResponsableActividad(List<String> responsableActividad) {
		this.responsableActividad = responsableActividad;
	}


}
