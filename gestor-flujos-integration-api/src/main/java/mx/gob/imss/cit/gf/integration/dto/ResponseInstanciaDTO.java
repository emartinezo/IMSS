/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.InstanciaDTO;

/**
 * Clase que contiena la lista de las instancias.
 * @author ahernandezd
 *
 */
public class ResponseInstanciaDTO extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de instancias.
	 */
	private List <InstanciaDTO> listaInstanciaDTO;
	/**
	 * Instancias del proceso.
	 */
	private InstanciaDTO instanciaDTO;

	/**
	 * @return the listaInstanciaDTO
	 */
	public List <InstanciaDTO> getListaInstanciaDTO() {
		return listaInstanciaDTO;
	}

	/**
	 * @param listaInstanciaDTO the listaInstanciaDTO to set
	 */
	public void setListaInstanciaDTO(List <InstanciaDTO> listaInstanciaDTO) {
		this.listaInstanciaDTO = listaInstanciaDTO;
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
