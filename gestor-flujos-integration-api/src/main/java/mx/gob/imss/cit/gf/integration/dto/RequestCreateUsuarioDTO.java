package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioBPMDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 16:40:02
 */
public class RequestCreateUsuarioDTO extends BaseRequestDTO {

	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Datos del usuario
	 */
	private UsuarioBPMDTO usuarioBPM;
	/**
	 * Datos de la sesión
	 */
	private UsuarioDTO usuarioSesion;

	/**
	 * @return createUsuario
	 */
	public UsuarioBPMDTO getCreateUsuario() {

		return usuarioBPM;
	}

	/**
	 * @return usuario
	 */
	public UsuarioDTO getUsuario() {

		return usuarioSesion;
	}

	/**
	 * @param createUsuario createUsuario
	 */
	public void setCreateUsuario(UsuarioBPMDTO createUsuario) {

		this.usuarioBPM = createUsuario;
	}

	/**
	 * @param usuario usuario
	 */
	public void setUsuario(UsuarioDTO usuario) {

		this.usuarioSesion = usuario;
	}
}