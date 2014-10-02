/**
 * 
 */
package mx.gob.imss.cit.gf.services;

import java.util.List;

import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * @author ahernandezd
 *
 */
public interface IModeloService {

	/**
	 * Metodo que realiza la busqueda de modelo de proceso por instancia,
	 * 	extrae las activides que ya fueron ejecutadas y las proximas a ejecutar.
	 * @param usuarioVO contiene los datos para iniciar la sesion.
	 * @param idInstancia identificador de la instancia.
	 * @return ModeloProcesoVO modelo del proceso.
	 * @throws GestorFlujosServicesException
	 */
	ModeloProcesoVO findModeloProcesoInstancia(UsuarioVO usuarioVO, String idInstancia) 
			throws GestorFlujosServicesException;
	
	/**
	 * Metodo que obtiene todas los modelos de la instancias asociadas al proceso expuesto.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param procesoDN identificador del proceso.
	 * @return lstModeloProcesoVO lista contiene los modelos y las instancias.
	 * @throws GestorFlujosServicesException
	 */
	List<ModeloProcesoVO> findModeloProcesoIdentificador(UsuarioVO usuarioVO, String procesoDN)
			throws GestorFlujosServicesException;
	
	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tipo tipo de proceso.
	 * @return lstModeloProcesoVO lista qye contiene  el modelo de la instancias.
	 * @throws GestorFlujosServicesException
	 */
	List<ModeloProcesoVO> findModeloProcesoTipo(UsuarioVO usuarioVO, String tipo)
			throws GestorFlujosServicesException;
}
