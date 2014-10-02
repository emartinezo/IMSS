package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.InstanciaDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * Clase DTO conteniendo datos de entrada del CU Consultar responsables
 * @author galejo
 * @version 1.0
 * @created 28-Ago-2014 19:15:45 p.m.
 */
public class RequestResponsablesInstDTO extends BaseRequestDTO {
	
	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * idInstanciasProcesos
	 */
	private List<String> idInstanciasProcesos;
	/**
	 * usuarioDTO
	 */
	private UsuarioDTO usuarioDTO;
	
	/**
	 * instanciaDTO
	 */	
	private InstanciaDTO instanciaDTO;
	
	/**
	 * @return the idInstanciasProcesos
	 */
	public List<String> getIdInstanciasProcesos() {
		return idInstanciasProcesos;
	}
	/**
	 * @param idInstanciasProcesos the idInstanciasProcesos to set
	 */
	public void setIdInstanciasProcesos(List<String> idInstanciasProcesos) {
		this.idInstanciasProcesos = idInstanciasProcesos;
	}
	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}
	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	/**
	 * @return the instanciaDTO
	 */
	public InstanciaDTO getInstanciaDTO() {
		return instanciaDTO;
	}
	/**
	 * @param instanciaDTO the instanciaDTO to set
	 */	
	public void setInstanciaDTO(InstanciaDTO instanciaDTO) {
		this.instanciaDTO = instanciaDTO;
	}
	
	
}
