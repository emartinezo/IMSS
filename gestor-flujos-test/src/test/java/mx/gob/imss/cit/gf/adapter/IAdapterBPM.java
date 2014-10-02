package mx.gob.imss.cit.gf.adapter;

import java.util.List;
import java.util.Map;

import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

/**
 * Interface que contiene metodos que implementan
 * el API de BPM.
 * @author Admin
 *
 */

public interface IAdapterBPM {

	/**
	 * Metodo que obtiene el contexto (inicio de sesion)
	 * del engine del BPM
	 * @param usuarioVO
	 * @return
	 * @throws AdapterBPMException
	 */
	SesionVO getSesion(UsuarioVO usuarioVO) throws AdapterBPMException;

	/**
	 * Metodo que cierra la sesion
	 * @param sesionVO
	 * @return
	 * @throws AdapterBPMException
	 */
	Boolean executeCerrarBPMContext(String user);

	/**
	 * Metodo que consulta los proceso de acuerdo a los criterios de busqueda.
	 * @param instanciaVO datos de busqueda.
	 * @param usuarioVO datos usuario para iniciar sesion.
	 * @return lista de instancias del proceso.
	 * @throws AdapterBPMException.
	 */
	List<InstanciaVO> findInstanciaDetalle(InstanciaVO instanciaVO, UsuarioVO usuarioVO) throws AdapterBPMException;

	/**
	 * Metodo que crear una Instacia del proceso.
	 * @param usuarioVO objeto que contiene los datos de sesion.
	 * @param procesoDN refrencia del proceso.
	 * @return idInstancia Identificador de la instacia del proceso.
	 * @throws AdapterBPMException
	 */
	String executeCrearInstanciaProceso(UsuarioVO usuarioVO, String procesoDN) throws AdapterBPMException;

	/**
	 * Metodo que cancela una instancia de proceso
	 * @param usuarioVO
	 * @param procesoId
	 * @throws AdapterBPMException
	 */
	void executeCancelarInstanciaProceso(UsuarioVO usuarioVO, String procesoId) throws AdapterBPMException;

	/**
	 * Metodo que realiza la cosulta de tareas asociadas por una instancia.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param tareaVO Criterios de busqueda de tarea.
	 * @param idInstancia Identificador de la instancia de proceso.
	 * @return List<TareaVO> lista de tareas.
	 * @throws AdapterBPMException
	 */
	List<TareaVO> findTareasInstancia(UsuarioVO usuarioVO, TareaVO tareaVO, String idInstancia) throws AdapterBPMException;

	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia de tarea.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param tareaVO Contiene el identificador de la instancia de tarea.
	 * @param idInstancia 	Id de la instancia de la tarea
	 * @return List<TareaVO> lista de tareas con roles.
	 */	
	List<TareaVO> findRolTareasInstancia(UsuarioVO usuarioVO,TareaVO tareaVO) throws AdapterBPMException;
	
	/**
	 * Metodo que crea el usuario en el administrador de procesos del BPM
	 * @param usuario
	 * @param createUsuario
	 * @return DatosUsuarioVO
	 * @throws AdapterBPMException
	 */
	DatosUsuarioVO executeGuardarMetadatosUsuario(UsuarioVO usuario, DatosUsuarioVO createUsuario) throws AdapterBPMException;

	/**
	 * Metodo que borra el usuario en el administrador de procesos del BPM
	 * @param usuario
	 * @param idUsuario
	 * @throws AdapterBPMException
	 */
	void deleteMetadatosUsuario(UsuarioVO usuario, String idUsuario, String datosAplicacion) throws AdapterBPMException;

	/**
	 * Método que obtiene los usuarios enmarcados dentro de los datos
	 * 
	 * @param usuario
	 * @param datosUsuario
	 * @return List<DatosUsuarioVO>
	 */
	List<DatosUsuarioVO> findMetadatosUsuarios(UsuarioVO usuario, DatosUsuarioVO datosUsuario) throws AdapterBPMException;

	/**
	 * Metodo que retorna los participantes que pueden ser asignados a una instancia de actividad
	 * 
	 * @param consultaParticipantes
	 * 
	 * @return List<ParticipanteVO>
	 * 
	 * @throws Exception
	 */
	List<ParticipanteVO> findParticipantesAsignablesAActividad(ConsultaParticipantesVO consultaParticipantes) throws AdapterBPMException;


	/**
	 * Metodo que permite rechazar o posponer una actividad
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO 	Tarea la cual se actualizara.
	 * @return ban 		Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws AdapterBPMException
	 */
	boolean updateEstadoTarea(UsuarioVO usuarioVO,  TareaVO tareaVO)throws AdapterBPMException;


	/**
	 * Elimina la sesion
	 * @param user
	 * @return
	 * @throws AdapterBPMException
	 */
	Boolean executeCerrarSesion(String user) throws AdapterBPMException;

	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * 
	 * @param idInstanciasProcesos
	 * @param usuarioVO contiene los datos para la sesion.
	 * 
	 * @return List<String>
	 * @throws AdapterBPMException
	 */
	List<String> findResponsablesInstancias(List<String> idInstanciasProcesos, UsuarioVO usuarioVO)
			throws AdapterBPMException;

	/**
	 * Metodo para obtener el valor de una variable en una actividad
	 * @param consultaArgActividadVO
	 * @return String
	 * @throws AdapterBPMException
	 */
	String getArgumentoActividad(ConsultaArgActividadVO consultaArgActividadVO)
			throws AdapterBPMException;

	/**
	 * Metodo que permite actualizar la tarea de una instancia dependiendo el estado de la instancia
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de tarea.
	 * @return true si fue actualizado correctamente.
	 * @throws AdapterBPMException
	 */
	boolean updateTarea(UsuarioVO usuarioVO,  TareaVO tareaVO)throws AdapterBPMException;
	/**
	 * Metodo que realiza la busqueda de modelo de proceso por instancia,
	 * 	extrae las activides que ya fueron ejecutadas y las proximas a ejecutar.
	 * @param usuarioVO objeto que contiene los datos para iniciar sesion.
	 * @param idInstanciasProcesos identificador de instancias de procesos.
	 * @return ModeloProcesoVO contiene el modelo de la isntancias.
	 * @throws AdapterBPMException
	 */
	ModeloProcesoVO findModeloProcesoInstancia(UsuarioVO usuarioVO, String idInstancia)
			throws AdapterBPMException;
	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws AdapterBPMException
	 */
	boolean updateInstancia(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException;

	
	/**
	 * Metodo que permite buscar los roles de la instancia del Proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return List<RolVO> Lista de los Roles
	 * @throws AdapterBPMException
	 */	
	List<RolVO> findRolesByInstaceProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException;
	/**
	 * Metodo que ejecuta(APROVED) una tarea por medio de un identificador unico.
	 * @param usuario identificador de usuario.
	 * @param idTarea identificador de tarea.
	 * @return tareaVO que fue ejecutada actualizada como  APROVED.
	 * @throws AdapterBPMException
	 */
	TareaVO executeTarea(String usuario, String idTarea) throws AdapterBPMException;
	
	/**
	 * Metodo que permite buscar la primera actividad de una instancia del proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return TareaVO retorna la primera tarea de la instancia del proceso
	 * @throws AdapterBPMException
	 */		
	TareaVO findFirstInstanceTaskForProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException;
	
	/**
	 * Metodo que permite buscar las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de la actividad
	 * @return List<TareaVO> Tareas anteriores en la misma instancia del proceso
	 * @throws AdapterBPMException
	 */		
	List<TareaVO> findTareasRemitentes(UsuarioVO usuarioVO, TareaVO tareaVO)throws AdapterBPMException;

	/**
	 * Metodo que extrae los modelos de la instancia por un indentificador de proceso.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param procesoDN identificador del proceso.
	 * @return lstModeloProcesoVO lista qye contiene  el modelo de la instancias.
	 * @throws AdapterBPMException
	 */
	List<ModeloProcesoVO> findModeloProcesoIdentificador(UsuarioVO usuarioVO, String procesoDN)
			throws AdapterBPMException;
	
	
	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tipo tipo de proceso.
	 * @return mapaProcesoDN lista que contiene los procesos.
	 * @throws AdapterBPMException
	 */
	Map<String, String> findModeloProcesoTipo(UsuarioVO usuarioVO, String tipo)
			throws AdapterBPMException;
	

	/**
	 * Extrae todas las instancias que estan asociadas a un proceso.
	 * @param usuarioVO datos del usuario.
	 * @param procesoDN  proceso.
	 * @return listaInstacia regresa los Id de las instancias.
	 * @throws AdapterBPMException
	 */
	List<String> findInstanciasProceso(UsuarioVO usuarioVO, String procesoDN)
			throws AdapterBPMException;
}
