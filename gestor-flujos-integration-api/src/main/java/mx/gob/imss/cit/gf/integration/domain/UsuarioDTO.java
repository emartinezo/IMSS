/**
 * 
 */
package mx.gob.imss.cit.gf.integration.domain;

/**
 * Clase que contiene los datos de usuario.
 * @author ahernandezd
 *
 */
public class UsuarioDTO  extends BaseDTO{

	/**
	 * UID
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
}
