/**
 * 
 */
package mx.gob.imss.cit.gf.integration.api;

import mx.gob.imss.cit.gf.integration.dto.RequestModeloDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseModeloDTO;

/**
 * @author ahernandezd
 *
 */
public interface IModeloIntegrator {

	/**
	 * Metodo que extrae el modelo del proceso por identificador de la instancia.
	 * @param requestModeloDTO contiene los datos de usuario y el identificador de instancia del proceso.
	 * @return ResponseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	ResponseModeloDTO findModeloProcesoInstancia(RequestModeloDTO requestModeloDTO);
	
	/**
	 * Metodo que obtiene todas los modelos de la instancias asociadas al proceso expuesto.
	 * @param requestModeloDTO contiene los datos de usuario y procesoDN.
	 * @return ResponseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	ResponseModeloDTO findModeloProcesoIdentificador(RequestModeloDTO requestModeloDTO);
	
	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param requestModeloDTO contiene los datos de usuario y tipo.
	 * @return ResponseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	ResponseModeloDTO findModeloProcesoTipo(RequestModeloDTO requestModeloDTO);
	
}
