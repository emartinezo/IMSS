/**
 * 
 */
package mx.gob.imss.cit.gf.integration.domain;

/**
 * @author ahernandezd
 *
 */
public class ActividadDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Identidicador de la actividad
	 */
	private String idActividad;
	/**
	 * Nombre de la actividad.
	 */
	private String nombre;
	/**
	 * Tipo elemento ejecutado.
	 */
	private String tipoElemento;
	/**
	 * Tipo Rol.
	 */
	private String rol;
	/**
	 * Nivel de auditoria
	 */
	private String nivelAuditoria;
	/**
	 * @return the idActividad
	 */
	public String getIdActividad() {
		return idActividad;
	}
	/**
	 * @param idActividad the idActividad to set
	 */
	public void setIdActividad(String idActividad) {
		this.idActividad = idActividad;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the tipoElemento
	 */
	public String getTipoElemento() {
		return tipoElemento;
	}
	/**
	 * @param tipoElemento the tipoElemento to set
	 */
	public void setTipoElemento(String tipoElemento) {
		this.tipoElemento = tipoElemento;
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
	 * @return the nivelAuditoria
	 */
	public String getNivelAuditoria() {
		return nivelAuditoria;
	}
	/**
	 * @param nivelAuditoria the nivelAuditoria to set
	 */
	public void setNivelAuditoria(String nivelAuditoria) {
		this.nivelAuditoria = nivelAuditoria;
	}
	

}
