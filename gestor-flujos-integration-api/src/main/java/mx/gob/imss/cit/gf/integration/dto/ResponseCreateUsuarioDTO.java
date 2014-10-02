package mx.gob.imss.cit.gf.integration.dto;

import java.io.Serializable;

import mx.gob.imss.cit.gf.integration.domain.UsuarioBPMDTO;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 16:40:02
 */
public class ResponseCreateUsuarioDTO extends BaseResponseDTO implements Serializable{

	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Datos del usuario a crear
	 */
	private UsuarioBPMDTO createUsuario;

	/**
	 * @return createUsuario
	 */
	public UsuarioBPMDTO getCreateUsuario() {

		return createUsuario;
	}

	/**
	 * @param createUsuario createUsuario
	 */
	public void setCreateUsuario(UsuarioBPMDTO createUsuario) {

		this.createUsuario = createUsuario;
	}
}