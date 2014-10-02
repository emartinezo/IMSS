package mx.gob.imss.cit.gf.vo;

/**
 * Clase VO conteniendo datos de entrada para Consultar participantes
 * @author galejo
 * @version 1.0
 * @created 25-Ago-2014 13:19:35 p.m.
 */
public class ConsultaParticipantesVO {
	
	/**
	 * Identificador de la instancia de proceso
	 */
	private Long idInstanciaProceso;
	/**
	 * Identificador de la instancia de la actividad
	 */	
	private Long idInstanciaActividad;
	/**
	 * Datos de sesión del usuario
	 */	
	private UsuarioVO usuario;
	
	
	/**
	 * @return idInstanciaProceso
	 */
	public Long getIdInstanciaProceso() {
		return idInstanciaProceso;
	}
	/**
	 * @param idInstanciaProceso
	 */
	public void setIdInstanciaProceso(Long idInstanciaProceso) {
		this.idInstanciaProceso = idInstanciaProceso;
	}
	/**
	 * @return idInstanciaActividad
	 */
	public Long getIdInstanciaActividad() {
		return idInstanciaActividad;
	}
	/**
	 * @param idInstanciaActividad
	 */
	public void setIdInstanciaActividad(Long idInstanciaActividad) {
		this.idInstanciaActividad = idInstanciaActividad;
	}
	/**
	 * @return usuario
	 */
	public UsuarioVO getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario
	 */
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	

}
