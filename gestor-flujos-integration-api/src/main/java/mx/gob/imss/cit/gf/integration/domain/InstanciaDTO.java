/**
 * 
 */
package mx.gob.imss.cit.gf.integration.domain;

import java.util.List;


/**
 * Clase que contiene las propiedades de una instancia.
 * @author ahernandezd
 *
 */
public class InstanciaDTO extends BaseDTO{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador de la instancia del proceso.
	 */
	private String idInstancia;
	/**
	 * Identificador del proceso.
	 */
	private Long idProceso;
	/**
	 * Estado de la instancia del proceso.
	 */
	private String estado;
	/**
	 * Nombre de la instancia del proceso
	 */
	private String nombreProceso;
	/**
	 * Composite DN
	 */
	private String procesoDN;
	/**
	 * Descripcion de rol asignado al proceso.
	 */
	private String rol;
	/**
	 * Usuario o creador que ejecuta el proceso.
	 */
	private String usuarioCreador;
	/**
	 * Fecha de la creacion del proceso.
	 */
	private String fechaCreacion;
	
	/**
	 * Lista de tareas.
	 */
	private List<TareaDTO> listaTareas;
	
	
	/**
	 * @return the idInstancia
	 */
	public String getIdInstancia() {
		return idInstancia;
	}
	/**
	 * @param idInstancia the idInstancia to set
	 */
	public void setIdInstancia(String idInstancia) {
		this.idInstancia = idInstancia;
	}
	/**
	 * @return the idProceso
	 */
	public Long getIdProceso() {
		return idProceso;
	}
	/**
	 * @param idProceso the idProceso to set
	 */
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
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
	 * @return the nombreProceso
	 */
	public String getNombreProceso() {
		return nombreProceso;
	}
	/**
	 * @param nombreProceso the nombreProceso to set
	 */
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	/**
	 * @return the procesoDN
	 */
	public String getProcesoDN() {
		return procesoDN;
	}
	/**
	 * @param procesoDN the procesoDN to set
	 */
	public void setProcesoDN(String procesoDN) {
		this.procesoDN = procesoDN;
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
	 * @return the fechaCreacion
	 */
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the listaTareas
	 */
	public List<TareaDTO> getListaTareas() {
		return listaTareas;
	}
	/**
	 * @param listaTareas the listaTareas to set
	 */
	public void setListaTareas(List<TareaDTO> listaTareas) {
		this.listaTareas = listaTareas;
	}

}
