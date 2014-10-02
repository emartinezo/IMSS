package mx.gob.imss.cit.gf.vo;

import java.io.Serializable;

/**
 * Clase VO conteniendo datos de entrada para Consultar Argumentos de Actividad
 * @author galejo
 * @version 1.0
 * @created 09-Sep-2014 17:11:44 p.m.
 */
public class ConsultaArgActividadVO implements Serializable {

	/**
	 * Serial number
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador de la instancia de proceso
	 */
	private String idInstanciaProceso;
	/**
	 * Identificador de la instancia de la actividad
	 */	
	private String idInstanciaActividad;
	/**
	 * Nombre de la variable
	 */
	private String nombreVble;
	/**
	 * Datos de sesión del usuario
	 */	
	private UsuarioVO usuarioVO;
	
	
	/**
	 * @return idInstanciaProceso
	 */
	public String getIdInstanciaProceso() {
		return idInstanciaProceso;
	}
	/**
	 * @param idInstanciaProceso
	 */
	public void setIdInstanciaProceso(String idInstanciaProceso) {
		this.idInstanciaProceso = idInstanciaProceso;
	}
	/**
	 * @return idInstanciaActividad
	 */
	public String getIdInstanciaActividad() {
		return idInstanciaActividad;
	}
	/**
	 * @param idInstanciaActividad
	 */
	public void setIdInstanciaActividad(String idInstanciaActividad) {
		this.idInstanciaActividad = idInstanciaActividad;
	}
	/**
	 * @return nombreVble
	 */
	public String getNombreVble() {
		return nombreVble;
	}
	/**
	 * @param nombreVble
	 */
	public void setNombreVble(String nombreVble) {
		this.nombreVble = nombreVble;
	}
	/**
	 * @return usuarioVO
	 */
	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}
	/**
	 * @param usuarioVO
	 */
	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}
	


}
