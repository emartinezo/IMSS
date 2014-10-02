/**
 * 
 */
package mx.gob.imss.cit.gf.services;

import java.util.List;

import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * Clasede servicio de la entidad tarea
 * @author ahernandezd
 *
 */
public interface ITareaService {

	/**
	 * Metodo que realiza la cosulta de tareas asociadas por una instancia.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param idInstancia Identificador de la instancia de proceso.
	 * @return List<TareaVO> lista de tareas.
	 * @throws GestorFlujosServicesException
	 */
	 List<TareaVO> findTareasInstancia(UsuarioVO usuarioVO, String idInstancia) throws GestorFlujosServicesException;
	 
	/**
	 * Metodo que permite rechazar o posponer una actividad 
	 * @param usuarioVO	Objeto que contiene los datos de sesion.            
	 * @param tareaVO 	Tarea la cual se actualizara.
	 * @return ban 		Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws GestorFlujosServicesException
	 */		 
	 boolean updateEstadoTarea(UsuarioVO usuarioVO, TareaVO tareaVO) throws GestorFlujosServicesException;
	 
	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param idInstancia Identificador de la instancia de proceso.
	 * @param idInstancia 	Id de la instancia de la tarea
	 * @return List<TareaVO> lista de tareas.
	 */	 
	 List<TareaVO> findRolTareasByInstancia(UsuarioVO usuarioVO,TareaVO tareaVO) throws GestorFlujosServicesException;
	 
	 /**Metodo que realiza la busqueda de tareas por estado y/o usuario. 
	  * @param usuarioVO Contiene los datos usuario/password para la sesion.
	  * @param tareaVO contiene los criterios de busqueda de tareas.
	  * @return List<TareaVO> lista de tareas.
	  * @throws GestorFlujosServicesException
	  */
	 List<TareaVO> findTareas(UsuarioVO usuarioVO, TareaVO tareaVO) throws GestorFlujosServicesException;

	 /**
	  * Metodo que hace la busqueda de lista de tareas por estado de asignadas y por id de instancia. 
	  * @param usuarioVO Contiene los datos usuario/password para la sesion.
	  * @param idInstancia  Identificador de la instancia de proceso.
	  * @return List<TareaVO> lista de tareas asignadas.
	  * @throws GestorFlujosServicesException
	  */
	 List<TareaVO> findTareasAsignadas(UsuarioVO usuarioVO, String idInstancia) throws GestorFlujosServicesException;
	 
	 /**
	  * Metodo para obtener el valor de una variable en una actividad
	  * @param consultaArgActividadVO
	  * @return String
	  * @throws GestorFlujosServicesException
	  */
	 String getArgumentoActividad(ConsultaArgActividadVO consultaArgActividadVO) throws GestorFlujosServicesException;
	 
	/**
	 * Metodo que permite actualizar una tarea que no este en EJECUCION, RECHAZADA o POSPUESTA 
	 * @param usuarioVO	Objeto que contiene los datos de sesion. 
	 * @param tareaVO objeto que contiene las propiedades de tarea.
	 * @return ban 		Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws GestorFlujosServicesException
	 */		 
	 boolean updateTarea(UsuarioVO usuarioVO, TareaVO tareaVO) throws GestorFlujosServicesException;
	/**
	 * Metodo que obtiene la lista de tareas en asignacion y ejecutar la primera tarea.  
	 * @param usuarioVO contiene los datos para iniciar sesion.
	 * @param idInstancia  Identificador de la instancia de proceso.
	 * @return tareaVO que fue ejecutada actualizada como  APROVED.
	 * @throws GestorFlujosServicesException
	 */
	 TareaVO executeTarea(UsuarioVO usuarioVO, String idInstancia) throws GestorFlujosServicesException;
	 
	/**
	 * Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de la actividad
	 * @return List<TareaVO> Tareas anteriores en la misma instancia del proceso
	 * @throws GestorFlujosServicesException
	 */		
	List<TareaVO> findTareasRemitentes(UsuarioVO usuarioVO, TareaVO tareaVO)throws GestorFlujosServicesException; 
	/**
	 * Metodo que busca la primera actividad de una instancia del proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return TareaVO retorna la primera tarea y su rol de la instancia del proceso
	 * @throws AdapterBPMException
	 */		
	TareaVO findFirstInstanceTaskForProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws GestorFlujosServicesException;
}
