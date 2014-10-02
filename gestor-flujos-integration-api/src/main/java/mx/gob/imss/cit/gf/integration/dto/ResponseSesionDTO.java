package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.SesionDTO;

/**
 * Clase que contiene los datos de salida del inicio
 * de sesion en el BPM
 * @author Admin
 *
 */
public class ResponseSesionDTO extends BaseResponseDTO{

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;

	private SesionDTO sesionDTO;

	/**
	 * @return the sesionDTO
	 */
	public SesionDTO getSesionDTO() {
		return sesionDTO;
	}

	/**
	 * @param sesionDTO the sesionDTO to set
	 */
	public void setSesionDTO(SesionDTO sesionDTO) {
		this.sesionDTO = sesionDTO;
	}
	
	

}
