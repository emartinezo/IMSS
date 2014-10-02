/**
 * 
 */
package mx.gob.imss.cit.gf.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.constant.NombreParametroConstants;
import mx.gob.imss.cit.gf.constant.ValidacionesConstants;
import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.ITareaService;
import mx.gob.imss.cit.gf.services.constans.EstadoActividadConstants;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesCodeExceptionEnum;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesExceptionBuilder;
import mx.gob.imss.cit.gf.util.ValidaCatalogo;
import mx.gob.imss.cit.gf.util.ValidatorUtil;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clasede servicio de la entidad tarea
 * @author ahernandezd
 *
 */
@Remote(ITareaService.class)
@Stateless
public class TareaService implements ITareaService {

	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TareaService.class);
	
	/**
	 * Interfaz de AdapterBPM
	 */
	@EJB
	private IAdapterBPM adapterBPM;
	
	
	/**
     **************************** DESCRIPCION *****************************
     *Nombre CU: Consultar actividades de una instancia de proceso
     *Descripcion :  Metodo que realiza la consulta de tareas asociadas por una instancia.
     **************************** PARMETROS ********************************
	 * @param usuarioVO 
	 * 		  Contiene los datos usuario/password para la sesion.
	 * 		  {usuario(String),password(String)}.
	 * @param idInstancia
	 * 		  Identificador de sesion.
	 * @return List<TareaVO> 
	 * 			Contiene la lista de tareas asociada a una instancia.
	 * @throws GestorFlujosServicesException
     ************************** LISTA DE ERRORES **************************
     *************************** DISENO TECNICO ***************************
     *VALIDACIONES
     *-------------------------
     */
	@Override
	public List<TareaVO> findTareasInstancia(UsuarioVO usuarioVO,
			String idInstancia) throws GestorFlujosServicesException {

		LOG.info("iniciando find tareas");
		List<TareaVO> listaTareaVO = null;
		try {			
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO);
			ValidatorUtil.validateParametros(parametros);			
			parametros.clear();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.ID_INSTANCIA, idInstancia);
			ValidatorUtil.validateParametros(parametros);			
		
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.INSTANCIA_ID_INSTANCIA,idInstancia, ValidacionesConstants.ER_ALFANUMERICO_CON_GUION_1A50);

			adapterBPM.getSesion(usuarioVO);
			listaTareaVO = adapterBPM.findTareasInstancia(usuarioVO, new TareaVO(), idInstancia);
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return listaTareaVO;
	}


	
	/**
     **************************** DESCRIPCION *****************************
     *Nombre CU: Consultar lista de actividades pendientes
     *Descripcion : Metodo que realiza la busqueda de tareas por estado y/o usuario.
     **************************** PARMETROS ********************************
	 * @param usuarioVO 
	 * 		  Contiene los datos usuario/password para la sesion.
	 * 		  {usuario(String),password(String)}.
	 * @param tareaVO 
	 * 		  contiene los criterios de busqueda de tareas.
	 * @return List<TareaVO> 
	 * 			List<TareaVO> lista de tareas.
	 * @throws GestorFlujosServicesException
     ************************** LISTA DE ERRORES **************************
     *************************** DISENO TECNICO ***************************
     *VALIDACIONES
     *-------------------------
     */
	@Override
	public List<TareaVO> findTareas(UsuarioVO usuarioVO, TareaVO tareaVO)
			throws GestorFlujosServicesException {
		List<TareaVO> listaTareaVO = null;
		try {
			
			Map<String,Object> parametros=new HashMap<String, Object>();
			validarDatos(usuarioVO, tareaVO, parametros);			
			parametros.clear();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);		
			parametros.clear();
			parametros.put(NombreParametroConstants.TAREA_ESTADO, tareaVO.getEstado());
			ValidatorUtil.validateParametros(parametros);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ESTADO,tareaVO.getEstado(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TAREA_USUARIO_CREADOR,tareaVO.getUsuarioCreador(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			
			adapterBPM.getSesion(usuarioVO);

			listaTareaVO = adapterBPM.findTareasInstancia(usuarioVO, tareaVO, null);
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return listaTareaVO;
	}



	/**
	 * @param usuarioVO
	 * @param tareaVO
	 * @param parametros
	 * @throws GestorFlujosException
	 */
	private void validarDatos(UsuarioVO usuarioVO, TareaVO tareaVO,
			Map<String, Object> parametros) throws GestorFlujosException {
		parametros.put(NombreParametroConstants.USUARIO, usuarioVO);
		parametros.put(NombreParametroConstants.TAREA, tareaVO);
		ValidatorUtil.validateParametros(parametros);
	}

	
	/**
	 * Metodo que permite rechazar o posponer una actividad 
	 * @param usuarioVO	Objeto que contiene los datos de sesion.            
	 * @param tareaVO 	Tarea la cual se actualizara.
	 * @return bandera	Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws GestorFlujosServicesException
	 */		 
	 public boolean updateEstadoTarea(UsuarioVO usuarioVO, TareaVO tareaVO) throws GestorFlujosServicesException{
			boolean bandera=false;
			try {
				Map<String,Object> parametros=new HashMap<String, Object>();
				validarDatos(usuarioVO, tareaVO, parametros);			
				parametros.clear();
				parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
				parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
				ValidatorUtil.validateParametros(parametros);		
				parametros.clear();								
				parametros.put(NombreParametroConstants.TAREA_ID, tareaVO.getIdTarea());
				parametros.put(NombreParametroConstants.TAREA_ESTADO, tareaVO.getEstado());
				ValidatorUtil.validateParametros(parametros);
				
				ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ESTADO,tareaVO.getEstado(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ID,tareaVO.getIdTarea(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				
				if (validateStateTask(tareaVO)){
					adapterBPM.getSesion(usuarioVO);
					bandera = adapterBPM.updateEstadoTarea(usuarioVO, tareaVO);
				}else{
					throw GestorFlujosServicesExceptionBuilder.build(GestorFlujosServicesCodeExceptionEnum.ERROR_STATE_TASK,new GestorFlujosServicesException());
				}
				
			} catch (AdapterBPMException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
						e.getDescription());
			}catch (GestorFlujosException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
			}finally{
				adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
			}
			return bandera;
	 }
	 


	 
	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param idInstancia Identificador de la instancia de proceso.
	 * @return List<TareaVO> lista de tareas.
	 */
		@Override
		public List<TareaVO> findRolTareasByInstancia(UsuarioVO usuarioVO,
				TareaVO tareaVO) throws GestorFlujosServicesException{
			List<TareaVO> list=null;
			try{
				Map<String,Object> parametros=new HashMap<String, Object>();
				validarDatos(usuarioVO, tareaVO, parametros);			
				parametros.clear();
				parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
				parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
				parametros.put(NombreParametroConstants.TAREA_ID, tareaVO.getIdTarea());
				ValidatorUtil.validateParametros(parametros);		
				parametros.clear();	
				ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ID,tareaVO.getIdTarea(), ValidacionesConstants.ER_ALFANUMERICO1A50);
				
				
				adapterBPM.getSesion(usuarioVO);
				list=adapterBPM.findRolTareasInstancia(usuarioVO,tareaVO);
				
			} catch (AdapterBPMException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
			} catch (GestorFlujosException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
			}finally{
				adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
			}
			return list;
		}
	 
		 
	/**
	* Metodo que realiza la validasion de los estados de la tarea 
	* @param task Contiene los datos de las tareas
	* @return booelan validate es true si la informacion es correcta
	*/		
	private boolean validateStateTask(TareaVO task){
		boolean validate=false;
		if (task!=null && EstadoActividadConstants.WITHDRAWN.equals(task.getEstado()))
		{
			validate=true;
		}
		else if (task!=null && EstadoActividadConstants.SUSPENDED.equals(task.getEstado()))
		{
					
			validate=true;						
		}
		return validate;
		}
	

	/**
     **************************** DESCRIPCION *****************************
     *Nombre CU: Consultar actividades en ejecucion
     *Descripcion :  Metodo que realiza la consulta de tareas asignadas
     *				 asociadas por una instancia.
     **************************** PARMETROS ********************************
	 * @param usuarioVO 
	 * 		  Contiene los datos usuario/password para la sesion.
	 * 		  {usuario(String),password(String)}.
	 * @param idInstancia
	 * 		  Identificador de sesion.
	 * @return List<TareaVO> 
	 * 			Contiene la lista de tareas asignadas asociada de
	 * 			 una instancia.
	 * @throws GestorFlujosServicesException
     ************************** LISTA DE ERRORES **************************
     *************************** DISENO TECNICO ***************************
     *VALIDACIONES
     *-------------------------
     */

	@Override
	public List<TareaVO> findTareasAsignadas(UsuarioVO usuarioVo, String idInstancia) throws GestorFlujosServicesException {
		List<TareaVO> listaTareaVO = null;
		TareaVO tareaVO = new TareaVO(); 
		try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			validarDatos(usuarioVo, tareaVO, parametros);			
			parametros.clear();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVo.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVo.getPassword());
			parametros.put(NombreParametroConstants.ID_INSTANCIA, idInstancia);
			ValidatorUtil.validateParametros(parametros);		
			parametros.clear();	
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVo.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVo.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.INSTANCIA_ID_INSTANCIA,idInstancia, ValidacionesConstants.ER_ALFANUMERICO_CON_GUION_1A50);

			adapterBPM.getSesion(usuarioVo);
			tareaVO.setEstado(EstadoActividadConstants.ASSIGNED);
			listaTareaVO = adapterBPM.findTareasInstancia(usuarioVo, tareaVO, idInstancia);
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVo.getUsuario());
		}			
		
		return listaTareaVO;
	}
	
	/**
	 * Metodo para obtener el valor de una variable en una actividad
	 * @param consultaArgActividadVO
	 * @return String
	 * @throws GestorFlujosServicesException
	 */
	@Override
	public String getArgumentoActividad(ConsultaArgActividadVO consultaArgActividadVO) throws GestorFlujosServicesException {
		
		String valor =  null;
		
		try{
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, consultaArgActividadVO.getUsuarioVO().getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, consultaArgActividadVO.getUsuarioVO().getPassword());
			parametros.put(NombreParametroConstants.ID_INSTANCIA_ACTIVIDAD, consultaArgActividadVO.getIdInstanciaActividad());
			parametros.put(NombreParametroConstants.NOMBRE_VARIABLE, consultaArgActividadVO.getNombreVble());
			
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO, consultaArgActividadVO.getUsuarioVO().getUsuario(), 
					ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD, consultaArgActividadVO.getUsuarioVO().getPassword(), 
					ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.ID_INSTANCIA_ACTIVIDAD, consultaArgActividadVO.getIdInstanciaActividad(), 
					ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.NOMBRE_VARIABLE, consultaArgActividadVO.getNombreVble(), 
					ValidacionesConstants.ER_ALFANUMERICO1A50);
			
			adapterBPM.getSesion(consultaArgActividadVO.getUsuarioVO());
			valor = adapterBPM.getArgumentoActividad(consultaArgActividadVO);
			
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		} finally{
			adapterBPM.executeCerrarBPMContext(consultaArgActividadVO.getUsuarioVO().getUsuario());
		}	
		
		return valor;
	}	
	/**
	 * Metodo que permite actualizar una tarea que no este en EJECUCION, RECHAZADA o POSPUESTA 
	 * @param usuarioVO	Objeto que contiene los datos de sesion. 
	 * @param tareaVO objeto que contiene las propiedades de tarea.
	 * @return ban 		Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws GestorFlujosServicesException
	 */		 
	 public boolean updateTarea(UsuarioVO usuarioVO, TareaVO tareaVO) throws GestorFlujosServicesException{
		 boolean ban=false;	
		 try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			validarDatos(usuarioVO, tareaVO, parametros);			
			parametros.clear();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);		
			parametros.clear();	
			
			parametros.put(NombreParametroConstants.TAREA_ID, tareaVO.getIdTarea());
			ValidatorUtil.validateParametros(parametros);

			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ID,tareaVO.getIdTarea(), ValidacionesConstants.ER_ALFANUMERICO1A50);			
			
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_FH_COMPROMISO,tareaVO.getFechaCompromiso(), ValidacionesConstants.ER_FECHA_DDMMYYYY);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_FH_INICIO,tareaVO.getFechaInicioEjecucion(), ValidacionesConstants.ER_FECHA_DDMMYYYY);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_FH_FIN,tareaVO.getFechaFinEjecucion(), ValidacionesConstants.ER_FECHA_DDMMYYYY);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TAREA_ESTADO,tareaVO.getEstado(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_PRIORITY,tareaVO.getPrioridad()!=null?tareaVO.getPrioridad().toString():null, ValidacionesConstants.ER_NUMERICO2);		
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_COMMENT,tareaVO.getComentario(), ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A150);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.TASK_URL,tareaVO.getUrlTarea(), ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A150);
			
			if (tareaVO.getEstado()==null || tareaVO.getEstado().isEmpty() || ValidaCatalogo.validaEstado(EstadoActividadConstants.class, tareaVO.getEstado())){
				if (findValueInObjects(tareaVO.getFechaCompromiso(),tareaVO.getFechaInicio(),tareaVO.getFechaFinEjecucion(),tareaVO.getEstado(),
									tareaVO.getPrioridad(),tareaVO.getComentario(),tareaVO.getUrlTarea())){
					adapterBPM.getSesion(usuarioVO);
				 	ban=adapterBPM.updateTarea(usuarioVO, tareaVO);
				}else{
					return true;
				}
			}else{
				throw GestorFlujosServicesExceptionBuilder.build(GestorFlujosServicesCodeExceptionEnum.ERROR_STATE_TASK,new GestorFlujosServicesException());
			}
			
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
				adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		 	

		 return ban;
	 }
	 
	 private boolean findValueInObjects(Object ... lista ){
		 int cont =0;
		 for (Object obj:lista){
			 if (obj instanceof String){
				 String value = (String) obj;
				 if (!value.trim().isEmpty())
				 { 
					 cont++;
				 }
				 
			 }else if (obj instanceof Integer){
				 Integer value = (Integer)obj;
				 if (value>0){
					 cont++;
				 }
			 }
		 }		 
		 LOG.info("Existen ["+cont+"] campos con valores");
		 return cont>0?true:false;
	 }

	 /**
	  **************************** DESCRIPCION *****************************
	  *Nombre CU:  Ejecutar la actividad de una instancia de proceso
	  *Descripcion : Metodo que obtiene la lista de tareas en asignacion 
	  *				 a partir de un identificador de una instancia. 
	  *				 y ejecutar la primera tarea.
	  **************************** PARMETROS ********************************
	  * @param usuarioVO 
	  * 		  Contiene los datos usuario/password para la sesion.
	  * 		  {usuario(String),password(String)}.
	  * @param idInstancia
	  * 		  Identificador de sesion.
	  * @return tareaVO que fue ejecutada actualizada como  APROVED.
	  * @throws GestorFlujosServicesException
	  ************************** LISTA DE ERRORES **************************
	  *************************** DISENO TECNICO ***************************
	  *VALIDACIONES
	  *-------------------------
	  */
		@Override
		public TareaVO executeTarea(UsuarioVO usuarioVO, String idInstancia)
				throws GestorFlujosServicesException {
			TareaVO tareaVO = null;
			try {
				List<TareaVO> listaTareaVO = null;
				listaTareaVO = findTareasAsignadas(usuarioVO, idInstancia);
				adapterBPM.getSesion(usuarioVO);
				if(!listaTareaVO.isEmpty() && listaTareaVO.size() > 0){				
					tareaVO = adapterBPM.executeTarea(usuarioVO.getUsuario(), listaTareaVO.get(0).getIdTarea());
				}
			} catch (AdapterBPMException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
						e.getDescription());
			}catch (GestorFlujosException e) {
				throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
			}finally{
				adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
			}
			return tareaVO;
		}

	

	/**
	 * Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de la actividad
	 * @return List<TareaVO> Tareas anteriores en la misma instancia del proceso
	 * @throws GestorFlujosServicesException
	 */		
	public List<TareaVO> findTareasRemitentes(UsuarioVO usuarioVO, TareaVO tareaVO)throws GestorFlujosServicesException{
		List<TareaVO> listRemitentes=null;
	try{
		Map<String,Object> parametros=new HashMap<String, Object>();
		validarDatos(usuarioVO, tareaVO, parametros);			
		parametros.clear();
		parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
		parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
		ValidatorUtil.validateParametros(parametros);		
		parametros.clear();	
		ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		ValidatorUtil.validateFormato(NombreParametroConstants.TAREA_ID,tareaVO.getIdTarea(), ValidacionesConstants.ER_ALFANUMERICO1A50);
		
		adapterBPM.getSesion(usuarioVO);	
		listRemitentes=adapterBPM.findTareasRemitentes(usuarioVO,tareaVO);
		
	} catch (AdapterBPMException e) {
		throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
				e.getDescription());
	}catch (GestorFlujosException e) {
		throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
	}finally{
		adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
	}
	return listRemitentes;
	}
	
	
	/**
	 * Metodo que busca la primera actividad de una instancia del proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return TareaVO retorna la primera tarea y su rol de la instancia del proceso
	 * @throws AdapterBPMException
	 */		
	public TareaVO findFirstInstanceTaskForProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws GestorFlujosServicesException{
		TareaVO rol=null;
		try{	
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO);
			parametros.put(NombreParametroConstants.INSTANCIA, instanciaVO);
			ValidatorUtil.validateParametros(parametros);			
			parametros.clear();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);		
			parametros.clear();	
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.ID_INSTANCIA,instanciaVO.getIdInstancia(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			
			adapterBPM.getSesion(usuarioVO);
			rol=adapterBPM.findFirstInstanceTaskForProcess(usuarioVO,instanciaVO);
			
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return rol;
		
	}
}
