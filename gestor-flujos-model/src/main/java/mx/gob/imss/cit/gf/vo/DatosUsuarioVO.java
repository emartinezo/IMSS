package mx.gob.imss.cit.gf.vo;

/**
 * Clase con los datos necesarios para el usuario
 *
 * @author rcelma
 * @created 22/08/2014 16:40:02
 */
public class DatosUsuarioVO {

	/**
	 * Id de los datos del usuario
	 */
	private String id;
	/**
	 * Nombre de los datos del usuario
	 */
	private String name;
	/**
	 * Tipo de datos de la aplicación
	 */
	private String applicationDataType;
	/**
	 * datos
	 */
	private String data;
	/**
	 * Contexto de Identidad
	 */
	private String identityContext;
	/**
	 * Dueño
	 */
	private String owner;

	/**
	 * @return applicationDataType
	 */
	public String getApplicationDataType() {

		return applicationDataType;
	}

	/**
	 * @return data
	 */
	public String getData() {

		return data;
	}

	/**
	 * @return identityContext
	 */
	public String getIdentityContext() {

		return identityContext;
	}

	/**
	 * @return name
	 */
	public String getName() {

		return name;
	}

	/**
	 * @return owner
	 */
	public String getOwner() {

		return owner;
	}

	/**
	 * @param applicationDataType applicationDataType
	 */
	public void setApplicationDataType(String applicationDataType) {

		this.applicationDataType = applicationDataType;
	}

	/**
	 * @param data data
	 */
	public void setData(String data) {

		this.data = data;
	}

	/**
	 * @param identityContext identityContext
	 */
	public void setIdentityContext(String identityContext) {

		this.identityContext = identityContext;
	}

	/**
	 * @param name name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * @param owner owner
	 */
	public void setOwner(String owner) {

		this.owner = owner;
	}

	/**
	 * @return id
	 */
	public String getId() {

		return id;
	}

	/**
	 * @param id id
	 */
	public void setId(String id) {

		this.id = id;
	}
}