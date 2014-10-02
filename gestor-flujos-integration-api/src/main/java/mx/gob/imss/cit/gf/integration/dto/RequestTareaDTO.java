/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.InstanciaDTO;
import mx.gob.imss.cit.gf.integration.domain.TareaDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * @author ahernandezd
 *
 */
public class RequestTareaDTO  extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private InstanciaDTO instanciaDTO;
	
	private TareaDTO tareaDTO;
	/**
	 * Objeto que contiene los datos para la sesion.
	 */
	private UsuarioDTO usuarioDTO;

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
	 * @return the tareaDTO
	 */
	public TareaDTO getTareaDTO() {
		return tareaDTO;
	}
	/**
	 * @param tareaDTO the tareaDTO to set
	 */
	public void setTareaDTO(TareaDTO tareaDTO) {
		this.tareaDTO = tareaDTO;
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
