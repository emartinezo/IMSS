/**
 * 
 */
package mx.gob.imss.cit.gf.integration.domain;


/**
 * Clase que contiene los datos de sesion.
 * 
 * @author ahernandezd
 *
 */
public class SesionDTO  extends BaseDTO{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Regresa el token de la sesion.
	 */
	private String token;
	
	/**
	 * Regresa el usuario de la sesion.
	 */
	private String usuario;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
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
