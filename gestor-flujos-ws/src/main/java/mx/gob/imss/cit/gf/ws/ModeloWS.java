/**
 * 
 * Clase que expone un webservice.
 */

package mx.gob.imss.cit.gf.ws;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import mx.gob.imss.cit.gf.integration.api.IModeloIntegrator;
import mx.gob.imss.cit.gf.integration.dto.RequestModeloDTO;
import mx.gob.imss.cit.gf.integration.dto.ResponseModeloDTO;

/**
 * @author ahernandezd
 *
 */
@WebService
public class ModeloWS {

	/**
	 * ejb de la capa de integracion
	 */
	@EJB
	private IModeloIntegrator modeloIntegrator;
	
	/**
	 * Metodo que extrae el modelo del proceso por identificador de la instancia.
	 * @param requestModeloDTO contiene los datos de usuario y el identificador 
	 * 							de instancia del proceso.
	 * @return responseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	@WebMethod
	@WebResult(name = "responseModeloDTO")
	public ResponseModeloDTO finModeloProcesoInstancia(
			@WebParam(name = "requestModeloDTO") RequestModeloDTO requestModeloDTO) {
		return modeloIntegrator.findModeloProcesoInstancia(requestModeloDTO);
	}
	
	/**
	 * Metodo que obtiene todas los modelos de la instancias asociadas al proceso expuesto.
	 * @param requestModeloDTO contiene los datos de usuario y procesoDN.
	 * @return responseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	@WebMethod
	@WebResult(name = "responseModeloDTO")
	public ResponseModeloDTO findModeloProcesoIdentificador(
			@WebParam(name = "requestModeloDTO") RequestModeloDTO requestModeloDTO) {
		return modeloIntegrator.findModeloProcesoIdentificador(requestModeloDTO);
	}
	
	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param requestModeloDTO contiene los datos de usuario y tipo.
	 * @return responseModeloDTO contiene las actividades ejecutadas y las proximas a ejecutar.
	 */
	@WebMethod
	@WebResult(name = "responseModeloDTO")
	public ResponseModeloDTO findModeloProcesoTipo(
			@WebParam(name = "requestModeloDTO") RequestModeloDTO requestModeloDTO) {
		return modeloIntegrator.findModeloProcesoTipo(requestModeloDTO);
	}
}
