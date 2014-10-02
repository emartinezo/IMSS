package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * Clase DTO conteniendo datos de salida del CU Consultar argumento de actividad
 * @author galejo
 * @version 1.0
 * @created 05-Sep-2014 10:44:44 a.m.
 */
public class RequestConsultaArgActDTO extends BaseRequestDTO {
	
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador de la instancia de proceso
	 */
	private String idInstanciaProceso;
	/**
	 * Identificador de la instancia de la actividad
	 */	
	private String idInstanciaActividad;
	/**
	 * Nombre de la variable
	 */
	private String nombreVble;
	/**
	 * Datos de sesión del usuario
	 */	
	private UsuarioDTO usuarioDTO;
	
	
	/**
	 * @return idInstanciaProceso
	 */
	public String getIdInstanciaProceso() {
		return idInstanciaProceso;
	}
	/**
	 * @param idInstanciaProceso
	 */
	public void setIdInstanciaProceso(String idInstanciaProceso) {
		this.idInstanciaProceso = idInstanciaProceso;
	}
	/**
	 * @return idInstanciaActividad
	 */
	public String getIdInstanciaActividad() {
		return idInstanciaActividad;
	}
	/**
	 * @param idInstanciaActividad
	 */
	public void setIdInstanciaActividad(String idInstanciaActividad) {
		this.idInstanciaActividad = idInstanciaActividad;
	}
	/**
	 * @return nombreVble
	 */
	public String getNombreVble() {
		return nombreVble;
	}
	/**
	 * @param nombreVble
	 */
	public void setNombreVble(String nombreVble) {
		this.nombreVble = nombreVble;
	}
	/**
	 * @return usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	/**
	 * @param usuarioDTO
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	

}
