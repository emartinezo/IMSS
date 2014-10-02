package mx.gob.imss.cit.gf.integration.domain;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 16:40:02
 */
public class UsuarioBPMDTO extends BaseDTO {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nombre del Usuario a Crear
	 */
	private String name;
	/**
	 * Tipo de Aplicacion
	 */
	private String applicationDataType;
	/**
	 * Datos del Usuario
	 */
	private String data;
	/**
	 * Contexto de identidad
	 */
	private String identityContext;
	/**
	 * Dueño del usuario
	 */
	private String owner;
	/**
	 * ID de los datos
	 */
	private String id;

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