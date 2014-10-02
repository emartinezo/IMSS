/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.RolDTO;

/**
 * Clase que contiena la lista de las instancias.
 * @author ahernandezd
 *
 */
public class ResponseInstanciaRolesDTO extends BaseResponseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de instancias.
	 */
	private List <RolDTO> roles;

	/**
	 * @return the listaInstanciaDTO
	 */
	public List <RolDTO> getRolesDTO() {
		return roles;
	}

	/**
	 * @param listaInstanciaDTO the listaInstanciaDTO to set
	 */
	public void setRolesDTO(List <RolDTO> roles) {
		this.roles = roles;
	}


}
