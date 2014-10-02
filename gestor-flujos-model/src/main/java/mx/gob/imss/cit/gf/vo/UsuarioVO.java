/**
 * 
 */
package mx.gob.imss.cit.gf.vo;

import java.io.Serializable;

/**
 * @author ahernandezd
 *
 */
public class UsuarioVO implements Serializable {
	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Nombre del usuario que se loguea e inicia sesion
	 */
	private String usuario;
	/**
	 * Password del usuario para iniciar sesion
	 */
	private String password;
	
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
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "UsuarioVO [usuario=" + usuario + ", password=" + password + "]";
	}
	
}
