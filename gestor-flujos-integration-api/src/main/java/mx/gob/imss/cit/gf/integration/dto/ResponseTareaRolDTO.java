package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.TareaRolDTO;

public class ResponseTareaRolDTO extends BaseResponseDTO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Lista de tareas asociadas a una instancia.
	 */
	private List<TareaRolDTO> listaTareaRolDTO;

	/**
	 * @return the listaTareaRolDTO
	 */
	public List<TareaRolDTO> getListaTareaRolDTO() {
		return listaTareaRolDTO;
	}

	/**
	 * @param listaTareaRolDTO the listaTareaVO to set
	 */
	public void setListaTareaRolDTO(List<TareaRolDTO> listaTareaRolDTO) {
		this.listaTareaRolDTO = listaTareaRolDTO;
	}
	
}
