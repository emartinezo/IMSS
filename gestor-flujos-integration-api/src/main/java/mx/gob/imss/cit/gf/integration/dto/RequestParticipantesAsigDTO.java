package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

public class RequestParticipantesAsigDTO extends BaseRequestDTO {
	
	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	
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
	private UsuarioDTO usuario;
	
	
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario
	 */
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}
