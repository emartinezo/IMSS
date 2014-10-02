package mx.gob.imss.cit.gf.vo;

/**
 * Clase VO conteniendo datos de salida por participante del CU Consultar participantes
 * @author galejo
 * @version 1.0
 * @created 25-Ago-2014 13:34:45 p.m.
 */
public class ParticipanteVO {
	
	/**
	 * usuario o login 
	 */
	private String usuario;
	/**
	 * Nombre
	 */
	private String nombre;
	/**
	 * Apellido Paterno
	 */
	private String apellidoPaterno;
	/**
	 * Correo Electronico
	 */
	private String correoElectronico;
	/**
	 * Rol
	 */
	private String rol;
	
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
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
	 * @return the apellidoPaterno
	 */
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	/**
	 * @param apellidoPaterno the apellidoPaterno to set
	 */
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	/**
	 * @return the correoElectronico
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	/**
	 * @param correoElectronico the correoElectronico to set
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
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
