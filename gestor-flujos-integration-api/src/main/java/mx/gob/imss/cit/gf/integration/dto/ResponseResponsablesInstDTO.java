package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

/**
 * Clase DTO conteniendo datos de salida por participante del CU Consultar responsables
 * @author galejo
 * @version 1.0
 * @created 28-Ago-2014 19:15:45 p.m.
 */
public class ResponseResponsablesInstDTO extends BaseResponseDTO {
	
	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * responsables
	 */
	private List<String> responsables;

	/**
	 * @return the responsables
	 */
	public List<String> getResponsables() {
		return responsables;
	}

	/**
	 * @param responsables the responsables to set
	 */
	public void setResponsables(List<String> responsables) {
		this.responsables = responsables;
	}
	
}
