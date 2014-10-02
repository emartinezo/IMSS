package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;


/**
 * Clase que contiene los datos de entrada para el servicio
 * de inicio de sesion en el BPM
 * @author Admin
 *
 */
public class RequestSesionDTO extends BaseRequestDTO {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = -1L;
	
	/**
	 * Datos de usuario.
	 */
	private UsuarioDTO usuarioDTO;

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

}
