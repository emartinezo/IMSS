package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.TareaDTO;

public class ResponseTareaDTO extends BaseResponseDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * tarea asociada a una instancia.
	 */
	private TareaDTO tareaDTO;
	
	/**
	 * Lista de tareas asociadas a una instancia.
	 */
	private List<TareaDTO> listaTareaDTO;

	/**
	 * @return the listaTareaDTO
	 */
	public List<TareaDTO> getListaTareaDTO() {
		return listaTareaDTO;
	}

	/**
	 * @param listaTareaDTO the listaTareaVO to set
	 */
	public void setListaTareaDTO(List<TareaDTO> listaTareaDTO) {
		this.listaTareaDTO = listaTareaDTO;
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
	
}
