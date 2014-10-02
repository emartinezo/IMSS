/**
 * 
 */
package mx.gob.imss.cit.gf.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author ahernandezd
 *
 */
public class ModeloProcesoVO implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nombre del proceso.
	 */
	
	private String nombreProceso;
	/**
	 * DN del proceso.
	 */
	private String procesoDN;
	
	/**
	 * listas de Indentificadores de la instancias.
	 */
	private List<String> instancias;
	
	/**
	 * Indentificador de la instancia.
	 */
	private String idInstancia;
	/**
	 * Lista de actividas de ejecutadas. 
	 */
	private List<ActividadVO> listaActividadIniciada;
	/**
	 * Lista de actividades proximas a ejecutarse.
	 */
	private List<ActividadVO> listaActividadProxima;
	/**
	 * @return the listaActividadIniciada
	 */
	public List<ActividadVO> getListaActividadIniciada() {
		return listaActividadIniciada;
	}
	/**
	 * @param listaActividadIniciada the listaActividadIniciada to set
	 */
	public void setListaActividadIniciada(List<ActividadVO> listaActividadIniciada) {
		this.listaActividadIniciada = listaActividadIniciada;
	}
	/**
	 * @return the listaActividadProxima
	 */
	public List<ActividadVO> getListaActividadProxima() {
		return listaActividadProxima;
	}
	/**
	 * @param listaActividadProxima the listaActividadProxima to set
	 */
	public void setListaActividadProxima(List<ActividadVO> listaActividadProxima) {
		this.listaActividadProxima = listaActividadProxima;
	}
	/**
	 * @return the nombreProceso
	 */
	public String getNombreProceso() {
		return nombreProceso;
	}
	/**
	 * @param nombreProceso the nombreProceso to set
	 */
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
	/**
	 * @return the procesoDN
	 */
	public String getProcesoDN() {
		return procesoDN;
	}
	/**
	 * @param procesoDN the procesoDN to set
	 */
	public void setProcesoDN(String procesoDN) {
		this.procesoDN = procesoDN;
	}
	/**
	 * @return the idInstancia
	 */
	public String getIdInstancia() {
		return idInstancia;
	}
	/**
	 * @param idInstancia the idInstancia to set
	 */
	public void setIdInstancia(String idInstancia) {
		this.idInstancia = idInstancia;
	}
	/**
	 * @return the instancias
	 */
	public List<String> getInstancias() {
		return instancias;
	}
	/**
	 * @param instancias the instancias to set
	 */
	public void setInstancias(List<String> instancias) {
		this.instancias = instancias;
	}

	
	
}
