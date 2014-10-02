/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.InstanciaDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * Clase que contiene los criterios de busqueda paara la instancias y sesion.
 * @author ahernandezd
 *
 */
public class RequestInstanciaDTO extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Objeto que contiene los criterios de busqueda.
	 */
	private InstanciaDTO instanciaDTO;
	/**
	 * Objeto que contiene los datos para la sesion.
	 */
	private UsuarioDTO usuarioDTO;
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
	
}
