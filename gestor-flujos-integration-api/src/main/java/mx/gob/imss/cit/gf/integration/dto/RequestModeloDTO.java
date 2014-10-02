/**
 * 
 */
package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * @author ahernandezd
 *
 */
public class RequestModeloDTO extends BaseRequestDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Contiene los datos de usuario.
	 */
	private UsuarioDTO usuarioDTO;

	/**
	 * Identificador de instancia.
	 */
	private String idInstancia;

	/**
	 * Identificador de proceso.
	 */
	private String procesoDN;
	/**
	 * tipo de proceso.
	 */
	private String tipo;
	
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
	 * @return the idInstancia
	 */
	public String getIdInstancia() {
		return idInstancia;
	}

	/**
	 * @param idInstancia the idInstancia to set
	 */
	public void setIdInstancia(String idInstancia) {
		this.idInstancia = idInstancia;
	}

	/**
	 * @return the procesoDN
	 */
	public String getProcesoDN() {
		return procesoDN;
	}

	/**
	 * @param procesoDN the procesoDN to set
	 */
	public void setProcesoDN(String procesoDN) {
		this.procesoDN = procesoDN;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
