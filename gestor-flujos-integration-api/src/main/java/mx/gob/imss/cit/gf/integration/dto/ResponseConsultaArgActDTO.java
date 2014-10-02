package mx.gob.imss.cit.gf.integration.dto;

public class ResponseConsultaArgActDTO extends BaseResponseDTO {
	
	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * valor del argumento
	 */
	private String valorArgumento;
	

	/**
	 * @return valorArgumento
	 */
	public String getValorArgumento() {
		return valorArgumento;
	}
	/**
	 * @param valorArgumento
	 */
	public void setValorArgumento(String valorArgumento) {
		this.valorArgumento = valorArgumento;
	}

}
