/**
 * 
 */
package mx.gob.imss.cit.gf.integration.api;

import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestResponsablesInstDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaRolesDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseResponsablesInstDTO;


/**
 * Interface de la capa de intergracion
 * para el servicio de instancia
 * @author ahernandezd
 *
 */
public interface IInstanciaIntegrator {

	/**
	 * Metodo que realiza la consulta el detalle de las instancias.
	 * @param requestFInstanciaDTO  criterios de busqueda.
	 * @return ResponseInstanciaDTO regresa las instancias por filtro.
	 * @throws GestorFlujosServicesException
	 */
	 ResponseInstanciaDTO findInstanciaDetalle(RequestInstanciaDTO requestFInstanciaDTO); 
			
	/**
	 * Metodo que genera la instancia de un proceso.
	 * @param requestAltaInstanciaDTO
	 * @return ResponseAltaInstanciaDTO contiene el identificador de la instancia creada.
	 * @throws GestorFlujosServicesException
	 */
	 ResponseInstanciaDTO executeCrearInstanciaProceso(RequestInstanciaDTO requestAltaInstanciaDTO);
	 
	 /**
	  * Metodo que cancela una instancia de proceso en base al id de instancia
	  * @param usuarioVO objeto que contiene los datos de sesion.
	  * @param procesoId Identificador de la instacia del proceso.
	  * @throws GestorFlujosServicesException
	  */
	 ResponseInstanciaDTO executeCancelarInstanciaProceso(RequestInstanciaDTO requestCancelaInstanciaDTO);
		
	 /**
	  * Metodo que realiza la consulta el detalle de las instancias y que obtiene las tareas.
	  * @param requestInstanciaDTO los parametros de busqueda.
	  * @return responseInstanciaDTO la lista de instancias con las tareas.
	  */
	 ResponseInstanciaDTO findInstancias(RequestInstanciaDTO requestInstanciaDTO);
	 
	 /**
	  * Metodo para obtener los responsables de las instancias de proceso.
	  * @param requestResponsablesInstDTO
	  * @return ResponseResponsablesInstDTO
	  */
	 ResponseResponsablesInstDTO findResponsablesInstancias(RequestResponsablesInstDTO requestResponsablesInstDTO);

	 
	/**
	* Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	* @param requestResponsablesInstDTO	Objeto que contiene los datos de sesion y las propiedades del proceso.
	* @return ResponseInstanciaRolesDTO lista de Roles
	*/	
	 ResponseInstanciaDTO updateInstanciaDeProceso(RequestInstanciaDTO requestResponsablesInstDTO);
	 
	 
	 /**
	* Metodo que permite consultar roles de una instancia de proceso
	* @param requestResponsablesInstDTO	Objeto que contiene los datos de sesion y las propiedades del proceso.
	* @return ResponseInstanciaRolesDTO lista de Roles
	*/	
	 ResponseInstanciaRolesDTO findRolesByInstaceProcess(RequestInstanciaDTO requestResponsablesInstDTO);
	 
	/**
	 * Metodo que genera la instancia de un proceso.
	 * @param requestAltaInstanciaDTO
	 * @return ResponseAltaInstanciaDTO contiene el identificador de la instancia creada.
	 * @throws GestorFlujosServicesException
	 */
	 ResponseInstanciaDTO executeInitInstanciaProceso(RequestInstanciaDTO requestAltaInstanciaDTO);
	 
}
