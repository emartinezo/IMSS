/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.ModeloDTO;

/**
 * @author ahernandezd
 *
 */
public class ResponseModeloDTO extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Modelo del proceso.
	 */
	private ModeloDTO modeloDTO;
	
	/**
	 * Lista de modelos de procesos.
	 */
	private List<ModeloDTO> listaModeloDTO;

	/**
	 * @return the modeloDTO
	 */
	public ModeloDTO getModeloDTO() {
		return modeloDTO;
	}

	/**
	 * @param modeloDTO the modeloDTO to set
	 */
	public void setModeloDTO(ModeloDTO modeloDTO) {
		this.modeloDTO = modeloDTO;
	}

	/**
	 * @return the listaModeloDTO
	 */
	public List<ModeloDTO> getListaModeloDTO() {
		return listaModeloDTO;
	}

	/**
	 * @param listaModeloDTO the listaModeloDTO to set
	 */
	public void setListaModeloDTO(List<ModeloDTO> listaModeloDTO) {
		this.listaModeloDTO = listaModeloDTO;
	}
}
