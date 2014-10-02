/**
 * 
 */
package mx.gob.imss.cit.gf.integration.domain;



/**
 * Clase que contiene las propiedades del Rol.
 * @author dlopezf
 *
 */
public class RolDTO extends BaseDTO{

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  Nombre del Rol
	 */
	private String rolName;
	
	/**
	 * @return the RolName
	 */
	public String getRolName() {
		return rolName;
	}
	/**
	 * @param RolName the RolName to set
	 */
	public void setRolName(String rolName) {
		this.rolName = rolName;
	}

}
