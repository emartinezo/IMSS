package mx.gob.imss.cit.gf.integration.dto;

import java.io.Serializable;
import java.util.List;

import mx.gob.imss.cit.gf.integration.domain.UsuarioBPMDTO;

/**
 * Respuesta de los datos
 *
 * @author rcelma
 * @created 10/09/2014 19:13:32
 */
public class ResponseFindUsuarioDTO extends BaseResponseDTO implements Serializable {

	/**
	 * Serialización
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Datos del usuario a buscar sus datos
	 */
	private List<UsuarioBPMDTO> resultadosUsuario;

	/**
	 * @return resultadosUsuario
	 */
	public List<UsuarioBPMDTO> getResultadosUsuario() {

		return resultadosUsuario;
	}

	/**
	 * @param resultadosUsuario resultadosUsuario
	 */
	public void setResultadosUsuario(List<UsuarioBPMDTO> resultadosUsuario) {

		this.resultadosUsuario = resultadosUsuario;
	}
}