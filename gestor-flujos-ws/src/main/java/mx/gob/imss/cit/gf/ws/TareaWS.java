package mx.gob.imss.cit.gf.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.gob.imss.cit.gf.integration.api.ITareaIntegrator;
import mx.gob.imss.cit.gf.integration.dto.RequestConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseConsultaArgActDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseTareaRolDTO;

/**
 * Clase que expone un webservice
 * 
 * @author admin
 * 
 */
@WebService
public class TareaWS {

	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private ITareaIntegrator tareaIntegrator;

	/**
	 * Metodo que realiza la consulta las tareas de una instancia.
	 * 
	 * @param requestFInstanciaDTO
	 *            criterios de busqueda.
	 * @return ResponseInstanciaDTO regresa las instancias por filtro.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaDTO")
	public ResponseTareaDTO findTareasInstancia(
			@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return tareaIntegrator.findTareasInstancia(requestInstanciaDTO);
	}
	
	/**
	 * Metodo que busca las tareas por usuario y/o estado.
	 * @param requestTareaDTO 
	 * 			contiene el id de instancia. 
	 * @return ResponseTareaDTO 
	 * 			contiene la lista de tareas.
	 */
	@WebMethod
	@WebResult(name = "responseTareaDTO")
	public ResponseTareaDTO findTareas(
			@WebParam(name = "requestTareaDTO")  RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.findTareas(requestTareaDTO);
	}

	/**
	 * Metodo que permite rechazar o posponer una actividad
	 * 
	 * @param requestTareaDTO
	 *            criterios de busqueda de la tarea
	 * @return ResponseTareaDTO informa si actualizo correctamente.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaDTO")
	public ResponseTareaDTO updateEstadoTarea(
			@WebParam(name = "requestTareaDTO")RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.updateEstadoTarea(requestTareaDTO);
	}


	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia.
	 * 
	 * @param requestTareaDTO
	 *            criterios de busqueda.
	 * @return ResponseTareaDTO regresa las tareas y roles de la instancia.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaRolDTO")
	public ResponseTareaRolDTO findTareasRolByInstancia(
			@WebParam(name = "requestTareaDTO")RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.findRolTareasInstancia(requestTareaDTO);
	}

	/**
	 * Metodo que realiza la consulta de tareas en asignacion y por Id Instancia 
	 * 
	 * @param requestInstanciaDTO
	 *            criterios de busqueda.
	 * @return ResponseTareaDTO regresa las tareas asignadas.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "requestInstanciaDTO")
	public ResponseTareaDTO findTareasAsignadas(
			@WebParam(name = "requestInstanciaDTO")RequestInstanciaDTO requestInstanciaDTO) {
		return tareaIntegrator.findTareasAsignadas(requestInstanciaDTO);
	}
	
	/**
	 * Metodo para obtener el valor de una variable en una actividad.
	 * @param requestConsultaArgActDTO
	 * @return ResponseConsultaArgActDTO
	 */	
	@WebMethod
	@WebResult(name = "responseConsultaArgActDTO")
	public ResponseConsultaArgActDTO getArgumentoActividad(
			@WebParam(name = "requestConsultaArgActDTO") RequestConsultaArgActDTO requestConsultaArgActDTO) {
		
		return tareaIntegrator.getArgumentoActividad(requestConsultaArgActDTO);
	}
		
	/**
	 * Metodo que permite actualizar la tarea de una instancia dependiendo el estado de la instancia
	 * 
	 * @param requestTareaDTO
	 *            criterios de busqueda de la tarea
	 * @return ResponseTareaDTO informa si actualizo correctamente.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaDTO")
	public ResponseTareaDTO updateTarea(
			@WebParam(name = "requestTareaDTO")RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.updateTarea(requestTareaDTO);
	}
	
	/**
	 * Metodo que obtiene la lista de tareas en asignacion y ejecutar la primera tarea. 
	 * 
	 * @param requestInstanciaDTO
	 *            criterios de busqueda.
	 * @return ResponseTareaDTO regresa si la tarea bandera de exito o no exito de la tarea.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "requestInstanciaDTO")
	public ResponseTareaDTO executeTarea(
			@WebParam(name = "requestInstanciaDTO")RequestInstanciaDTO requestInstanciaDTO) {
		return tareaIntegrator.executeTarea(requestInstanciaDTO);
	}
	
	
	/**
	 * Metodo que realiza la consulta de tareas y roles de la primer tarea de una instancia de proceso
	 * 
	 * @param requestTareaDTO
	 *            criterios de busqueda.
	 * @return ResponseTareaDTO regresa las tareas y roles de la instancia.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaRolDTO")
	public ResponseTareaRolDTO findRolDePrimerTareaDeUnaInstProceso(
			@WebParam(name = "requestTareaDTO")RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.findRolDePrimeraTareaDeInstProceso(requestTareaDTO);
	}
	
	/**
	 *  Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * 
	 * @param requestTareaDTO
	 *            criterios de busqueda de la tarea
	 * @return ResponseTareaDTO informa si actualizo correctamente.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseTareaDTO")
	public ResponseTareaDTO findTareasRemitentes(
			@WebParam(name = "requestTareaDTO")RequestTareaDTO requestTareaDTO) {
		return tareaIntegrator.findTareasRemitentes(requestTareaDTO);
	}
		
}
