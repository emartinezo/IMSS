package mx.gob.imss.cit.gf.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.gob.imss.cit.gf.integration.api.IInstanciaIntegrator;
import mx.gob.imss.cit.gf.integration.dto.RequestInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.RequestResponsablesInstDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseInstanciaRolesDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseResponsablesInstDTO;

/**
 * Clase que expone un webservice
 */
@WebService
public class InstanciaWS {
	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private IInstanciaIntegrator instanciaIntegrator;

	/**
	 * Metodo que realiza la consulta de instancias.
	 * 
	 * @param requestFInstanciaDTO
	 *            criterios de busqueda.
	 * @return ResponseInstanciaDTO regresa las instancias por filtro.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO findInstanciaDetalle(
			@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.findInstanciaDetalle(requestInstanciaDTO);
	}

	/**
	 * Metodo que genera la instancia de un proceso.
	 * 
	 * @param requestAltaInstanciaDTO
	 * @return ResponseAltaInstanciaDTO contiene el identificador de la
	 *         instancia creada.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO createInstancia(
			@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.executeCrearInstanciaProceso(requestInstanciaDTO);
	}
	
	
	/**
	 * Metodo que realiza la consulta de instancias y las tareas.
	 * 
	 * @param requestFInstanciaDTO
	 *            criterios de busqueda.
	 * @return ResponseInstanciaDTO regresa las instancias/tareas por filtro.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO findInstancias(
			@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.findInstancias(requestInstanciaDTO);
	}	
	
	
	
	/**
	 * Metodo que cancela la instancia en base a su id
	 * 
	 * @param requestCancelaInstanciaDTO
	 * @return ResponseInstanciaDTO 
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO executeCancelarInstanciaProceso(@WebParam(name = "requestCancelaInstanciaDTO") RequestInstanciaDTO requestCancelaInstanciaDTO) {
		return instanciaIntegrator.executeCancelarInstanciaProceso(requestCancelaInstanciaDTO);
	}

	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * @param requestResponsablesInstDTO
	 * @return ResponseResponsablesInstDTO 
	 * 
	 */
	@WebMethod
	@WebResult(name = "responseResponsablesInstDTO")
	public ResponseResponsablesInstDTO findResponsablesInstancias(@WebParam(name = "requestResponsablesInstDTO") RequestResponsablesInstDTO requestResponsablesInstDTO) {
		return instanciaIntegrator.findResponsablesInstancias(requestResponsablesInstDTO);
	}

	
	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * 
	 * @param requestInstanciaDTO
	 * @return ResponseInstanciaDTO 
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO updateInstancia(@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.updateInstanciaDeProceso(requestInstanciaDTO);
	}

	
	/**
	 * Metodo que permite buscar los roles de instancia de proceso
	 * 
	 * @param requestInstanciaDTO
	 * @return ResponseInstanciaRolesDTO 
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaRolesDTO")
	public ResponseInstanciaRolesDTO findRolesByInstaceProcess(@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.findRolesByInstaceProcess(requestInstanciaDTO);
	}	
	
	
	/**
	 * Metodo que genera la instancia de un proceso.
	 * 
	 * @param requestAltaInstanciaDTO
	 * @return ResponseAltaInstanciaDTO contiene el identificador de la
	 *         instancia creada.
	 * @throws GestorFlujosServicesException
	 */
	@WebMethod
	@WebResult(name = "responseInstanciaDTO")
	public ResponseInstanciaDTO executeInitInstancia(
			@WebParam(name = "requestInstanciaDTO") RequestInstanciaDTO requestInstanciaDTO) {
		return instanciaIntegrator.executeInitInstanciaProceso(requestInstanciaDTO);
	}
	
	
}
