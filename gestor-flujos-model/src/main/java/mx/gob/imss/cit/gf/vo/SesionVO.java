package mx.gob.imss.cit.gf.vo;

import java.io.Serializable;

public class SesionVO implements Serializable {
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	private String token;

	/**
	 * usuario
	 */
	private String usuario;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
