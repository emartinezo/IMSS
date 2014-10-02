package mx.gob.imss.cit.gf.integration.dto;

import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.ParticipanteDTO;

/**
 * Clase DTO conteniendo datos de salida del CU Consultar participantes
 * @author galejo
 * @version 1.0
 * @created 28-Ago-2014 19:15:45 p.m.
 */
public class ResponseParticipantesAsigDTO extends BaseResponseDTO {
	
	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Lista de participantes
	 */
	private List<ParticipanteDTO> participantes;

	
	/**
	 * @return participantes
	 */
	public List<ParticipanteDTO> getParticipantes() {
		return participantes;
	}

	/**
	 * Establece valor para participantes 
	 * @param participantes
	 */
	public void setParticipantes(List<ParticipanteDTO> participantes) {
		this.participantes = participantes;
	}
	
	
	

}
