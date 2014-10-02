package mx.gob.imss.cit.gf.integration.dto;

import java.io.Serializable;

import mx.gob.imss.cit.gf.integration.domain.UsuarioBPMDTO;
import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * Request para encontrar los datos del usaurio
 *
 * @author rcelma
 * @created 10/09/2014 19:13:02
 */
public class RequestFindUsuarioDTO extends BaseRequestDTO implements Serializable {

	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Datos del Usuario
	 */
	private UsuarioBPMDTO datosUsuario;
	/**
	 * Sesión del usuario
	 */
	private UsuarioDTO usuarioSesion;

	/**
	 * @return datosUsuario
	 */
	public UsuarioBPMDTO getDatosUsuario() {

		return datosUsuario;
	}

	/**
	 * @return usuarioSesion
	 */
	public UsuarioDTO getUsuarioSesion() {

		return usuarioSesion;
	}

	/**
	 * @param datosUsuario datosUsuario
	 */
	public void setDatosUsuario(UsuarioBPMDTO datosUsuario) {

		this.datosUsuario = datosUsuario;
	}

	/**
	 * @param usuarioSesion usuarioSesion
	 */
	public void setUsuarioSesion(UsuarioDTO usuarioSesion) {

		this.usuarioSesion = usuarioSesion;
	}
}