package mx.gob.imss.cit.gf.integration.dto;

import mx.gob.imss.cit.gf.integration.domain.UsuarioDTO;

/**
 * 
 *
 * @author rcelma
 * @created 22/08/2014 16:40:02
 */
public class RequestDeleteUsuarioDTO extends BaseRequestDTO {

	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Datos de usuario.
	 */
	private UsuarioDTO usuarioDTO;
	/**
	 * Id del usuario
	 */
	private String idUsuario;
	/**
	 * Id del usuario
	 */
	private String datosAplicacion;

	/**
	 * @return the usuarioDTO
	 */
	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	/**
	 * @param usuarioDTO the usuarioDTO to set
	 */
	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}

	/**
	 * @return idUsuario
	 */
	public String getIdUsuario() {

		return idUsuario;
	}

	/**
	 * @param idUsuario idUsuario
	 */
	public void setIdUsuario(String idUsuario) {

		this.idUsuario = idUsuario;
	}

	/**
	 * @return datosAplicacion
	 */
	public String getDatosAplicacion() {

		return datosAplicacion;
	}

	/**
	 * @param datosAplicacion datosAplicacion
	 */
	public void setDatosAplicacion(String datosAplicacion) {

		this.datosAplicacion = datosAplicacion;
	}
}