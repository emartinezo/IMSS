/**
 * 
 */
package mx.gob.imss.cit.gf.services;

import java.util.List;

import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * @author ahernandezd
 *
 */
public interface IInstanciaService {


	/**
	 * Metodo que realiza la consulta de instancias.
	 * @param instanciaVO coentiene los criterios de busqueda.
	 * @param usuarioVO contiene los datos paara la sesion.
	 * @return listaInstanciaVO lista de instancias.
	 * @throws GestorFlujosServicesException
	 */
	List<InstanciaVO> findInstanciaDetalle(InstanciaVO instanciaVO,
			UsuarioVO usuarioVO) throws GestorFlujosServicesException;
	/**
	 * Metodo que crear una Instacia del proceso.
	 * @param usuarioVO objeto que contiene los datos de sesion.
	 * @param procesoDN referencia del proceso.
	 * @return idInstancia Identificador de la instacia del proceso.
	 * @throws GestorFlujosServicesException
	 */
	String executeCrearInstanciaProceso(UsuarioVO usuarioVO, String procesoDN)
			throws GestorFlujosServicesException;
	/**
	 * Metodo que obtiene las instancias con las taras asociadas.
	 * @param instanciaVO que contiene los criterios de bsuqueda.
	 * @param usuarioVO contiene los datos para la sesion.
	 * @return listaInstanciasVO la lista de instancias con tareas.
	 * @throws GestorFlujosServicesException
	 */
	List<InstanciaVO> findInstancias(InstanciaVO instanciaVO, UsuarioVO usuarioVO)
			throws GestorFlujosServicesException;

	/**
	 * Metodo que cancela una instancia de proceso en base al id de instancia
	 * @param usuarioVO objeto que contiene los datos de sesion.
	 * @param procesoId Identificador de la instacia del proceso.
	 * @throws GestorFlujosServicesException
	 */
	void executeCancelarInstanciaProceso(UsuarioVO usuarioVO, String procesoId) throws GestorFlujosServicesException;


	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * 
	 * @param idInstanciasProcesos
	 * @param usuarioVO contiene los datos para la sesion.
	 * 
	 * @return List<String>
	 * @throws GestorFlujosServicesException
	 */
	List<String> findResponsablesInstancias(List<String> idInstanciasProcesos, UsuarioVO usuarioVO)throws GestorFlujosServicesException;



	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws GestorFlujosServicesException
	 */
	boolean updateInstancia(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws GestorFlujosServicesException;
	
	/**
	 * Metodo que permite buscar los roles de la instancia del Proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return List<RolVO> Lista de los Roles
	 * @throws GestorFlujosServicesException
	 */		
	List<RolVO> findRolesByInstaceProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws GestorFlujosServicesException;
	
	
	/**
	 * Metodo que inicia un proceso.
	 * @param usuarioVO objeto que contiene los datos de sesion.
	 * @param procesoDN referencia del proceso.
	 * @return objeto de la instacia del proceso.
	 * @throws GestorFlujosServicesException
	 */
	InstanciaVO executeInitInstanciaProceso(UsuarioVO usuarioVO, String procesoDN)throws GestorFlujosServicesException;
}
