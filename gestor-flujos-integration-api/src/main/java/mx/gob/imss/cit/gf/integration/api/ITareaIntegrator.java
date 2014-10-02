/**
 * 
 */
package mx.gob.imss.cit.gf.integration.api;

import mx.gob.imss.cit.gf.integration.dto.RequestConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaRolDTO;

/**
 * @author ahernandezd
 *
 */
public interface ITareaIntegrator {
	
	/**
	 * Metodo que busca las tareas asociadas a una instancia de proceso(idInstancia).
	 * @param requestInstanciaDTO contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene la lista de tareas.
	 */
	ResponseTareaDTO findTareasInstancia(RequestInstanciaDTO requestInstanciaDTO);

	/**
	 * Metodo que busca las tareas por usuario y/o estado.
	 * @param requestTareaDTO contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene la lista de tareas.
	 */
	ResponseTareaDTO findTareas(RequestTareaDTO requestTareaDTO);
	
	/**
	 * Metodo que permite rechazar o posponer una actividad 
	 * @param requestTareaDTO 	contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene la lista de tareas.
	 */
	  ResponseTareaDTO updateEstadoTarea(RequestTareaDTO requestTareaDTO);

	  
	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia.
	 * @param requestTareaDTO 	contiene el id de instancia y los datos del usuario
	 * @return ResponseTareaDTO contiene la lista de tareas con sus respectivos roles
	 */
	 ResponseTareaRolDTO findRolTareasInstancia(RequestTareaDTO requestTareaDTO);
	 
	 /**
	  * Metodo que reliza la busqueda de tareas por estado asignado y por Id de instancia.
	  * @param requestInstanciaDTO contiene el id de instancia y los datos del usuario.
	  * @return contiene la lista de tareas en estado de asignado.
	  */
	 
	 ResponseTareaDTO findTareasAsignadas(RequestInstanciaDTO requestInstanciaDTO);	 
	 /**
	  * Metodo para obtener el valor de una variable en una actividad.
	  * @param requestConsultaArgActDTO
	  * @return ResponseConsultaArgActDTO
	  */
	 ResponseConsultaArgActDTO getArgumentoActividad(RequestConsultaArgActDTO requestConsultaArgActDTO);	 
	 
	/**
	 * Metodo que permite actualizar una tarea que no este en EJECUCION, RECHAZADA o POSPUESTA 
	 * @param requestTareaDTO 	contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene la lista de tareas.
	*/	 
	 ResponseTareaDTO updateTarea(RequestTareaDTO requestTareaDTO);

	 /**
	  * Metodo que obtiene la lista de tareas en asignacion y ejecutar la primera tarea. 
	  * @param requestInstanciaDTO contiene loa datos para inciar sesion y 
	  * 						   el identficador de instancia.
	  * @return responseTareaDTO contiene una bandera true/false de ejecucion 
	  * 						 correcta de la tarea.
	  */
	 ResponseTareaDTO executeTarea(RequestInstanciaDTO requestInstanciaDTO);
	
	 
	/**
	 *  Metodo que busca el rol de la primera actividad de una instancia del proceso 
	 * @param requestTareaDTO 	contiene el id de instancia. 
	 * @return ResponseTareaDTO contiene rol y tarea.
	*/	
	 ResponseTareaRolDTO findRolDePrimeraTareaDeInstProceso(RequestTareaDTO requestTareaDTO) ;

	/**
	 *  Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param requestTareaDTO 	contiene el id de tarea. 
	 * @return ResponseTareaDTO contiene rol y tarea.
	*/		 
	 ResponseTareaDTO findTareasRemitentes(RequestTareaDTO requestTareaDTO);
}
