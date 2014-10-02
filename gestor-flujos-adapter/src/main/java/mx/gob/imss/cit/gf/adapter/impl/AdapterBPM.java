package mx.gob.imss.cit.gf.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.constant.AdapterBPMConstants;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMCodeExceptionEnum;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMExceptionBuilder;
import mx.gob.imss.cit.gf.adapter.util.BPMUtil;
import mx.gob.imss.cit.gf.adapter.util.PropertiesAdapterUtil;
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
import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.IWorkflowConstants;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.task.model.CommentType;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.user.IUserMetadataService;
import oracle.bpel.services.workflow.user.model.ObjectFactory;
import oracle.bpel.services.workflow.user.model.UserApplicationDataType;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.services.client.IBPMServiceClient;
import oracle.bpm.services.common.exception.BPMException;
import oracle.bpm.services.instancemanagement.model.IProcessInstance;
import oracle.tip.pc.services.common.ServiceFactory;
import oracle.tip.pc.services.identity.BPMAppRole;
import oracle.tip.pc.services.identity.BPMAuthorizationService;
import oracle.xml.parser.v2.XMLElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementacion de metodos del API-BPM
 * 
 * @author Admin
 * 
 */
@Remote(value = IAdapterBPM.class)
@Stateless
public class AdapterBPM implements IAdapterBPM {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AdapterBPM.class);


	/**
	 * BPMServiceClientFactory
	 */
	private static BPMServiceClientFactory bpmServiceClientFactory;


	/**
	 * Metodo que obtiene el contexto (inicio de sesion) del engine del BPM
	 */
	public SesionVO getSesion(UsuarioVO usuarioVO) throws AdapterBPMException {

		SesionVO sesionVO = new SesionVO();
		boolean noExisteCache=true;

		String token = "";
		try{
			token= ContextUserCache.get(usuarioVO.getUsuario()).getToken();
			noExisteCache=false;
			LOG.info("El cache  existe y no se creara la sesion");
		}catch(AdapterBPMException e){
			noExisteCache=true;
			LOG.info("El cache no existe y se creara la sesion");
		}

		try {
			if(noExisteCache){
				token=getBPMContext(usuarioVO);

			}
			sesionVO.setToken(token);
			sesionVO.setUsuario(usuarioVO.getUsuario());
			LOG.info("Adapter- getSesion - token obtenido: " + sesionVO.getToken());
		} catch (BPMException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_OBTENER_SESION, e);
		}
		return sesionVO;
	}

	/**
	 * Metodo que cierra la conexion al bpm.
	 * 
	 * @param sesionVO
	 *            contiene las intancias de sesion bpm.
	 * @return true cierra correctamente la sesion o false si fallo al cerrar
	 *         conexion.
	 * @throws AdapterBPMException
	 */
	public Boolean executeCerrarSesion(String user) throws AdapterBPMException {

		try {

			bpmServiceClientFactory.getBPMUserAuthenticationService().destroyBPMContext((IBPMContext)ContextUserCache.get(user));
			ContextUserCache.remove(user);

		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CERRAR_SESION_BPM_CONTEXT, e);
		}

		return true;
	}


	/**
	 * Metodo para inicializar el contexto
	 * 
	 * @throws BPMException

	 */
	private static String getBPMContext(UsuarioVO usuarioVO) throws BPMException {

		IBPMContext bpmContext = getBPMServiceClientFactory().getBPMUserAuthenticationService().authenticate(usuarioVO.getUsuario(),
				usuarioVO.getPassword().toCharArray(), null);

		ContextUserCache.put(usuarioVO.getUsuario(), bpmContext);

		return bpmContext.getToken();

	}

	/**
	 * Metodo carga propiedades para generar el servicio cliente.
	 * 
	 * @return BPMServiceClientFactory.
	 */
	private static BPMServiceClientFactory getBPMServiceClientFactory() {

		if (bpmServiceClientFactory == null) {

			Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> properties = new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();

			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE, WorkflowServiceClientFactory.REMOTE_CLIENT);
			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_PROVIDER_URL,
					PropertiesAdapterUtil.getMessage(AdapterBPMConstants.KEY_URL_SERVIDOR_BPM));
			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_INITIAL_CONTEXT_FACTORY,
					PropertiesAdapterUtil.getMessage(AdapterBPMConstants.KEY_CONTEXTO_INICIAL_WL));
			bpmServiceClientFactory = BPMServiceClientFactory.getInstance(properties, null, null);
		}

		return bpmServiceClientFactory;
	}	
	
	/**
	 * Metodo carga propiedades para generar el servicio cliente.
	 * 
	 * @return BPMServiceClientFactory.
	 */
	public static BPMServiceClientFactory getBPMServiceClientFactory(Map<String,String> datos) {

		if (bpmServiceClientFactory == null) {

			Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> properties = new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();

			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE, WorkflowServiceClientFactory.REMOTE_CLIENT);
			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_PROVIDER_URL,
					datos.get(AdapterBPMConstants.KEY_URL_SERVIDOR_BPM));
			properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.EJB_INITIAL_CONTEXT_FACTORY,
					datos.get(AdapterBPMConstants.KEY_CONTEXTO_INICIAL_WL));
			bpmServiceClientFactory = BPMServiceClientFactory.getInstance(properties, null, null);
		}

		return bpmServiceClientFactory;
	}
	
	
	/**
	 * Metodo que cierra la conexion al bpm.
	 * 
	 * @param sesionVO
	 *            contiene las intancias de sesion bpm.
	 * @return true cierra correctamente la sesion o false si fallo al cerrar
	 *         conexion.
	 */
	public Boolean executeCerrarBPMContext(String user) {

		try {

			bpmServiceClientFactory.getBPMUserAuthenticationService().destroyBPMContext((IBPMContext)ContextUserCache.get(user));
			ContextUserCache.remove(user);

		} catch(Exception e) {
			LOG.info("No existe sesion para cerrar");
		}

		return true;
	}

	/**
	 * Metodo que se encarga de realizar la busqueda de instancias.
	 * 
	 * @param instanciaVO
	 *            criterios de bsuqueda.
	 * @param usuarioVO
	 *            datos par iniciar sesion.
	 * @return listaInstancias lista de instancias.
	 */
	@Override
	public List<InstanciaVO> findInstanciaDetalle(InstanciaVO instanciaVO,UsuarioVO usuarioVO) throws AdapterBPMException {

		return AdapterBPMHelper.findInstanciaDetalle(bpmServiceClientFactory, instanciaVO, usuarioVO);
	}


	

	/**
	 * Metodo que crear una Instacia del proceso.
	 * 
	 * @param usuarioVO
	 *            objeto que contiene los datos de sesion.
	 * @param procesoDN
	 *            refrencia del proceso.
	 * @return idInstancia Identificador de la instacia del proceso.
	 * @throws AdapterBPMException
	 */
	@Override
	public String executeCrearInstanciaProceso(UsuarioVO usuarioVO, String procesoDN) throws AdapterBPMException {

		String idInstancia = null;

		try {
			LOG.info("Crear Instancia...");

			IBPMServiceClient ibsc = bpmServiceClientFactory.getBPMServiceClient();
			idInstancia = ibsc.getInstanceManagementService().createProcessInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()), procesoDN);

			if (idInstancia == null || idInstancia.isEmpty()) {
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CREAR_INSTANCIA_PROCESO, new AdapterBPMException());
			}

			LOG.info("Crear Instancia - idInstancia: " + idInstancia);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch(BPMException e) {

			LOG.info("executeCrearInstancia BPMException: " + e.getDescription());

			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CREAR_INSTANCIA_PROCESO, e);
		} catch(Exception e) {

			LOG.info("executeCrearInstancia xception: " + e.getMessage());

			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CREAR_INSTANCIA_PROCESO, e);
		}
		LOG.info("Crear Instancia - idInstancia: " + idInstancia);
		return idInstancia;
	}

	/**
	 * Metodo que cancela una Instacia de proceso.
	 * 
	 * @param usuarioVO
	 *            objeto que contiene los datos de sesion.
	 * @param procesoId
	 *            refrencia de la instancia del proceso
	 * @throws AdapterBPMException
	 */
	@Override
	public void executeCancelarInstanciaProceso(UsuarioVO usuarioVO, String procesoId)
			throws AdapterBPMException {
		LOG.info("ADAPTER - Cancelar Instancia - procesoID:" + procesoId);
		try {
			IBPMServiceClient ibsc = bpmServiceClientFactory.getBPMServiceClient();
			IProcessInstance processInstance = ibsc.getInstanceQueryService().getProcessInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()),
					procesoId);
			if(processInstance != null){
				LOG.info("Cancelar Instancia - processInstance:" + processInstance.getProcessDN());
				ibsc.getInstanceManagementService().cancelProcessInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()), processInstance);
			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_NO_ENCONTRADA);
			}
			LOG.info("Cancelar Instancia - Proceso BPM - id: " + procesoId + " se cancelo exitosamente");
		} catch(BPMException bpme) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CANCELAR_INSTANCIA_PROCESO, bpme);
		} catch(AdapterBPMException adapE) {
			throw AdapterBPMExceptionBuilder.build(adapE.getCode(), adapE);
		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CANCELAR_INSTANCIA_PROCESO, e);
		}
	}

	/**
	 * Metodo que realiza la cosulta de tareas asociadas por una instancia.
	 * 
	 * @param usuarioVO
	 * 			Contiene los datos usuario/password para la sesion.
	 * @param tareaVO
	 * 			Criterios de busqueda de tarea.
	 * @param idInstancia
	 * 			Identificador de la instancia de proceso.
	 * @return List<TareaVO> lista de tareas.
	 * @exception AdapterBPMException
	 */
	@Override
	public List<TareaVO> findTareasInstancia(UsuarioVO usuarioVO,
			TareaVO tareaVO, String idInstancia) throws AdapterBPMException {
	
		return AdapterBPMHelper.findTareasInstancia(bpmServiceClientFactory, usuarioVO, tareaVO, idInstancia);
	}
	

	
	/**
	 * Metodo que realiza la consulta de tareas y roles asociados por una instancia de tarea.
	 * @param usuarioVO Contiene los datos usuario/password para la sesion.
	 * @param tareaVO Contiene el identificador de la instancia de tarea.
	 * @param idInstancia 	Id de la instancia de la tarea
	 * @return List<TareaVO> lista de tareas con roles.
	 */	
	@SuppressWarnings("unchecked")
	@Override
	public List<TareaVO> findRolTareasInstancia(UsuarioVO usuarioVO,TareaVO tareaVO) throws AdapterBPMException {
		List<TareaVO> listaTareaVO = new ArrayList<TareaVO>();
		try {	
			IWorkflowContext workflowContext = ContextUserCache.get(usuarioVO.getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			Task task = tQueryService.getTaskDetailsById(workflowContext, tareaVO.getIdTarea());

			if (task!=null){
				List<oracle.bpel.services.workflow.task.model.IdentityType> listIdentityType=task.getSystemAttributes().getAssignees();
				
				TareaVO tareaVORes= new TareaVO();
				for (oracle.bpel.services.workflow.task.model.IdentityType identityType:listIdentityType){
					tareaVORes.setIdTarea(task.getSystemAttributes().getTaskId());
					tareaVORes.setRol(identityType.getId());
				}
				listaTareaVO.add(tareaVORes);
			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_NOFOUND, new AdapterBPMException());
			}
		} catch (WorkflowException e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONUSULTAR_TAREA, e);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch(Exception e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONUSULTAR_TAREA, e);
		}
		return listaTareaVO;
	}	
	
	
	/**
	 * Guarda los datos del usuario
	 * 
	 * @return UserApplicationDataType
	 */
	public DatosUsuarioVO executeGuardarMetadatosUsuario(UsuarioVO usuario, DatosUsuarioVO datosUsuario) throws AdapterBPMException {

		//UserApplicationDataType usuarioMetadata = null;
		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuario.getUsuario());
			IWorkflowServiceClient workflowServiceClient = bpmServiceClientFactory.getWorkflowServiceClient();
			IUserMetadataService userMetadataService = workflowServiceClient.getUserMetadataService();
			ObjectFactory oFactory = new ObjectFactory();
			UserApplicationDataType usuarioACrear = oFactory.createUserApplicationDataType();
			usuarioACrear.setName(datosUsuario.getName());
			usuarioACrear.setOwner(datosUsuario.getOwner());
			usuarioACrear.setApplicationDataType(datosUsuario.getApplicationDataType());
			usuarioACrear.setData(datosUsuario.getData());//Aqui van todos los datos del usuario, apellidos, nombre, etc.
			usuarioACrear.setIdentityContext(datosUsuario.getIdentityContext());
			userMetadataService.createUserApplicationData(workflowContext, usuarioACrear);
		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CREAR_METADATOS_USUARIO, e);
		}
		return datosUsuario;
	}

	/**
	 * Borra el usuario del sistema
	 * 
	 * @return UserApplicationDataType
	 */
	public void deleteMetadatosUsuario(UsuarioVO usuario, String idUsuario, String datosAplicacion) throws AdapterBPMException {

		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuario.getUsuario());
			workflowContext.getToken();
			IWorkflowServiceClient workflowServiceClient = bpmServiceClientFactory.getWorkflowServiceClient();
			IUserMetadataService userMetadataService = workflowServiceClient.getUserMetadataService();
			UserApplicationDataType usuarioADesactivar = userMetadataService.getUserApplicationData(workflowContext, idUsuario);
			userMetadataService.deleteUserApplicationData(workflowContext, usuarioADesactivar.getId());
		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_BORRAR_METADATOS_USUARIO, e);
		}
	}

	/**
	 * Método encargado de encontrar los datos guardados dentro de la aplicación
	 * 
	 * @param usuario datos de sesión del usuario
	 * @param datosUsuario datos con los que se hace la búsqueda de los usuarios
	 * @return UserApplicationDataType
	 */
	public List<DatosUsuarioVO> findMetadatosUsuarios(UsuarioVO usuario, DatosUsuarioVO datosUsuario) throws AdapterBPMException {

		List<DatosUsuarioVO> usuarios = null;

		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuario.getUsuario());
			workflowContext.getToken();
			IWorkflowServiceClient workflowServiceClient = bpmServiceClientFactory.getWorkflowServiceClient();
			IUserMetadataService userMetadataService = workflowServiceClient.getUserMetadataService();
			List<UserApplicationDataType> usuariosUADT = userMetadataService.listUserApplicationData(workflowContext, datosUsuario.getApplicationDataType(),
					datosUsuario.getOwner());			
			if(!usuariosUADT.isEmpty()){
				usuarios = new ArrayList<DatosUsuarioVO>(usuariosUADT.size());
				for(UserApplicationDataType userAppDataT: usuariosUADT){
					DatosUsuarioVO usuarioD = new DatosUsuarioVO();
					usuarioD.setApplicationDataType(userAppDataT.getApplicationDataType());
					usuarioD.setOwner(userAppDataT.getOwner());
					usuarioD.setName(userAppDataT.getName());
					usuarioD.setData(userAppDataT.getData());
					usuarioD.setId(userAppDataT.getId());
					usuarioD.setIdentityContext(userAppDataT.getIdentityContext());
					usuarios.add(usuarioD);
				}				
			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_LISTA_METADATOS_VACIA);
			}
			
		}catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);						
		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_METADATOS_USUARIO, e);
		}
		return usuarios;
	}

	/**
	 * Metodo que retorna los participantes que pueden ser asignados a una instancia de actividad
	 * 
	 * @param consultaParticipantes
	 * 
	 * @return List<ParticipanteVO>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ParticipanteVO> findParticipantesAsignablesAActividad( ConsultaParticipantesVO consultaParticipantes) throws AdapterBPMException {

		List<ParticipanteVO> participantes = new ArrayList<ParticipanteVO>();

		try {
			BPMAuthorizationService service=ServiceFactory.getAuthorizationServiceInstance();
			List<BPMAppRole> roles = service.getAppRoles();
			for (BPMAppRole bpmAppRole : roles) {
				ParticipanteVO participanteVO=new ParticipanteVO();
				participanteVO.setNombre(bpmAppRole.getName());
				participanteVO.setUsuario(bpmAppRole.getUniqueName());
				participanteVO.setCorreoElectronico(bpmAppRole.getEmail());
				participantes.add(participanteVO);
			}

		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_RECUPERAR_PARTICIPANTES_ASIGNABLES, e);
		}

		return participantes;
	}

	/**
	 * Metodo que permite rechazar o posponer una actividad 
	 * @param usuarioVO		Objeto que contiene los datos de sesion.            
	 * @param tareaVO 		Tarea la cual se actualizara.
	 * @return ban 			Bandera que permite saber conocer si a actualizacion fue efectuada con exito.
	 * @throws AdapterBPMException
	 */	
	public boolean updateEstadoTarea(UsuarioVO usuarioVO, TareaVO tareaVO) throws AdapterBPMException {

		boolean ban = false;
		LOG.info(usuarioVO.toString());
		LOG.info(tareaVO.toString());

		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuarioVO.getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			String state = tareaVO.getEstado();
			tareaVO.setEstado(null);

			Task t = tQueryService.getTaskDetailsById(workflowContext, tareaVO.getIdTarea());
			if (t != null) {

				ITaskService taskSvc = getBPMServiceClientFactory().getWorkflowServiceClient().getTaskService();
				Task tResponse; 
				
				if (oracle.bpel.services.workflow.IWorkflowConstants.TASK_STATE_WITHDRAWN.equals(state)){
					tResponse= taskSvc.withdrawTask(workflowContext, t);
				}else{
					tResponse= taskSvc.suspendTask(workflowContext, t);
				}
				
				if (tResponse.getSystemAttributes().getState().equals(state)) {
					ban = true;
				}
			} else {
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_NOFOUND, new AdapterBPMException());
			}

		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CHANGE_STATE_TASK, e);
		}
		return ban;
	}

	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * 
	 * @param idInstanciasProcesos
	 * @param usuarioVO contiene los datos para la sesion.
	 * 
	 * @return List<String>
	 * @throws AdapterBPMException
	 */
	@Override
	public List<String> findResponsablesInstancias(List<String> idInstanciasProcesos, UsuarioVO usuarioVO) throws AdapterBPMException {

		return AdapterBPMHelper.findResponsablesInstancias(bpmServiceClientFactory, idInstanciasProcesos, usuarioVO);
	}

	/**
	 * Metodo para obtener el valor de una variable en una actividad
	 * @param consultaArgActividadVO
	 * @return String
	 * @throws AdapterBPMException
	 */
	@Override
	public String getArgumentoActividad(ConsultaArgActividadVO consultaArgActividadVO) throws AdapterBPMException {
		String valueArgument=null;
		try {	
			IWorkflowContext workflowContext = ContextUserCache.get(consultaArgActividadVO.getUsuarioVO().getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			Task task = tQueryService.getTaskDetailsById(workflowContext, consultaArgActividadVO.getIdInstanciaActividad());
			if (task!=null){
				
		        XMLElement nsElement = (XMLElement)task.getPayloadAsElement();
		        if (nsElement == null) {
		        	throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_ARGUMENTS_NOFOUND, new AdapterBPMException());
		        }else{
		        
			        NodeList byName=nsElement.getElementsByTagName(consultaArgActividadVO.getNombreVble());
			        if (byName!=null && byName.getLength()>0){			        				        	
			        	int byNameI = byName.getLength();
			        	LOG.info("\n SI ENCONTRO ElementsByTagName="+consultaArgActividadVO.getNombreVble());
			        	for (int index =0;index<byNameI;index++){
			        		Node nodo=byName.item(index);
			        		LOG.info("nodo.getNodeName()="+nodo.getNodeName());
			        		LOG.info("nodo.getNodeValue()="+nodo.getNodeValue());
			        		LOG.info("nodo.getTextContent()="+nodo.getTextContent());
			        		if (consultaArgActividadVO.getNombreVble().equals(nodo.getNodeName())){
			        			valueArgument= nodo.getTextContent();
			        			break;
			        		}			        	
			        	}			        
			        }else{
			        	LOG.info("\n NOFOUND ElementsByTagName"+consultaArgActividadVO.getNombreVble());
			        	throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_ARGUMENT_NOFOUND, new AdapterBPMException());
			        }
		        }

			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_NOFOUND, new AdapterBPMException());
			}
		} catch (WorkflowException e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_ARGUMENTO_DE_ACTIVIDAD, e);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch(Exception e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_ARGUMENTO_DE_ACTIVIDAD, e);
		}
	
		return valueArgument;
	}


	

	/**
	 * Metodo que permite actualizar la tarea de una instancia dependiendo el estado de la instancia
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de tarea.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws AdapterBPMException
	 */
	public boolean updateTarea(UsuarioVO usuarioVO,  TareaVO tareaVO)throws AdapterBPMException{
		boolean ban=false;
		LOG.info(usuarioVO.toString());
		LOG.info(tareaVO.toString());

		try {

			IWorkflowContext workflowContext = ContextUserCache.get(usuarioVO.getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			Task task = tQueryService.getTaskDetailsById(workflowContext, tareaVO.getIdTarea());

			if (task!=null){
				if (BPMUtil.validateStatusUpdate(task)){
					throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_TASK_ESTADO_NO_VALIDO);
				}else{

					task.setDueDate(BPMUtil.updateFechaNoObligatorioStringCalendar(tareaVO.getFechaCompromiso(),task.getDueDate()));
					task.setPriority(BPMUtil.updateValorNoObligatorioInteger(tareaVO.getPrioridad(),task.getPriority()));
					task.getSystemAttributes().setTaskNumber(BPMUtil.updateValorNoObligatorioInteger(tareaVO.getNumeroTarea(),task.getSystemAttributes().getTaskNumber()));
					task.getSystemAttributes().setState(BPMUtil.updateValorNoObligatorioString(tareaVO.getEstado(),task.getSystemAttributes().getState()));
					task.getSystemAttributes().setEndDate(BPMUtil.updateFechaNoObligatorioStringCalendar(tareaVO.getFechaFinEjecucion(),task.getSystemAttributes().getEndDate()));
					task.setStartDate(BPMUtil.updateFechaNoObligatorioStringCalendar(tareaVO.getFechaInicioEjecucion(),task.getStartDate()));
					task.setTaskDisplayUrl( BPMUtil.updateValorNoObligatorioString(tareaVO.getUrlTarea(),task.getTaskDisplayUrl()));


					if (tareaVO.getComentario()!=null && tareaVO.getComentario().length()>0){
						task.removeAllUserComment();
						oracle.bpel.services.workflow.task.model.ObjectFactory factory = new oracle.bpel.services.workflow.task.model.ObjectFactory();
						CommentType ct=factory.createCommentType();
						ct.setComment(tareaVO.getComentario());
						task.addUserComment(ct);
					}


					ITaskService taskSvc = getBPMServiceClientFactory().getWorkflowServiceClient().getTaskService();
					Task tResponse=taskSvc.updateTask(workflowContext, task);
					if (tResponse.getSystemAttributes().getState().equals(task.getSystemAttributes().getState())){
						ban=true;
					}


				}

			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_NOFOUND, new AdapterBPMException());
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_UPDATE_TASK, e);
		}
		return ban;
	}

	/**
	 * Metodo que realiza la busqueda de modelo de proceso por instancia,
	 * 	extrae las activides que ya fueron ejecutadas y las proximas a ejecutar.
	 * @param usuarioVO objeto que contiene los datos para iniciar sesion.
	 * @param idInstancia identificador de instancias de procesos.
	 * @return ModeloProcesoVO contiene el modelo de la isntancias.
	 * @throws AdapterBPMException
	 */
	@Override
	public ModeloProcesoVO findModeloProcesoInstancia(UsuarioVO usuarioVO, String idInstancia) throws AdapterBPMException {
		
		return AdapterBPMHelper.findModeloProcesoInstancia(bpmServiceClientFactory, usuarioVO, idInstancia); 
	}

	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws AdapterBPMException
	 */
	public boolean updateInstancia(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException{
	
		return AdapterBPMHelper.updateInstancia(bpmServiceClientFactory, usuarioVO, instanciaVO);
	}

	
	/**
	 * Metodo que permite buscar los roles de la instancia del Proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return List<RolVO> Lista de los Roles
	 * @throws AdapterBPMException
	 */	
	public List<RolVO> findRolesByInstaceProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException{

		return AdapterBPMHelper.findRolesByInstaceProcess(bpmServiceClientFactory, usuarioVO, instanciaVO);	
	}
	/**
	 * Metodo que ejecuta(APROVED) una tarea por medio de un identificador unico.
	 * @param usuario identificador de usuario.
	 * @param idTarea identificador de tarea.
	 * @return tareaVO que fue ejecutada actualizada como  APROVED.
	 * @throws AdapterBPMException
	 */
	@Override
	public TareaVO executeTarea(String usuario, String idTarea) throws AdapterBPMException {
		TareaVO tareaVO = null;
		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuario);
			ITaskQueryService taskQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			Task tarea = taskQueryService.getTaskDetailsById(workflowContext, idTarea);
			
			if (tarea!=null) {
				ITaskService taskService = getBPMServiceClientFactory().getWorkflowServiceClient().getTaskService();
				tarea = taskService.updateTaskOutcome(workflowContext, tarea, AdapterBPMConstants.ACTION_APPROVE);
				if (tarea.getSystemAttributes().getState().equals(IWorkflowConstants.TASK_STATE_COMPLETED)) {
					tareaVO = new TareaVO();
					tareaVO.setNombreTarea(tarea.getTitle());
					tareaVO.setEstado(tarea.getSystemAttributes().getState());
					tareaVO.setIdTarea(tarea.getSystemAttributes().getTaskId());
					tareaVO.setNumeroTarea(tarea.getSystemAttributes().getTaskNumber());
				} else {
					throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_TAREA_YA_HA_SIDO_EJECUTADA);
				}
			} else {
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_ENCONTRAR_INSTANCIA_TAREA);
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_TAREA_NO_EJECUTADA, e);
		}
		return tareaVO;
	}	
	
	
	
	/**
	 * Metodo que busca las tareas remitentes(ejecutadas anteriormente en la misma instancia del proceso)
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tareaVO objeto que contiene las propiedades de la actividad
	 * @return List<TareaVO> Tareas anteriores en la misma instancia del proceso
	 * @throws AdapterBPMException
	 */		
	public List<TareaVO> findTareasRemitentes(UsuarioVO usuarioVO, TareaVO tareaVO)throws AdapterBPMException{
		List<TareaVO> lTareasVOReturn= null;
		try {
			
			IWorkflowContext workflowContext = ContextUserCache.get(usuarioVO.getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory.getWorkflowServiceClient().getTaskQueryService();
			Task task = tQueryService.getTaskDetailsById(workflowContext, tareaVO.getIdTarea());

			if (task!=null){
				String idInstancia=task.getProcessInfo().getInstanceId();
				List<TareaVO> lTareasVO=findTareasInstancia(usuarioVO,new TareaVO (),  idInstancia);
					if (lTareasVO!=null && !lTareasVO.isEmpty()){
						
						lTareasVOReturn= new ArrayList<TareaVO>();						
						for (TareaVO tarea:lTareasVO){
							if (tarea.getIdTarea().equals(task.getSystemAttributes().getTaskId())){
								break;
							}else{
								lTareasVOReturn.add(tarea);
							}
						}
					}else{
						throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TAREAS_NO_ENCONTRADAS, new AdapterBPMException());
					}
			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TASK_NOFOUND, new AdapterBPMException());
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO, e);
		}
		return lTareasVOReturn;
	}
	
	
	/**
	 * Metodo que busca la primera actividad de una instancia del proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return TareaVO retorna la primera tarea de la instancia del proceso
	 * @throws AdapterBPMException
	 */		
	public TareaVO findFirstInstanceTaskForProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException{
		TareaVO tareaVO=null;
		try {
			List<TareaVO> lTareasVO=findTareasInstancia(usuarioVO,new TareaVO (),  instanciaVO.getIdInstancia());

			if (lTareasVO!=null && !lTareasVO.isEmpty()){
				tareaVO= new TareaVO();
				tareaVO=lTareasVO.get(0);		
				List<TareaVO> roles=findRolTareasInstancia(usuarioVO,tareaVO);
				if (roles!=null && !roles.isEmpty()){
					tareaVO=roles.get(0);
				}else{
					throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_ROL_DE_INSTANCIA_DE_TAREA_NO_ENCONTRADO, new AdapterBPMException());
				}

			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_TAREAS_NO_ENCONTRADAS, new AdapterBPMException());
			}

		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_ROL_DE_ACTIVIDAD_INICIAL, e);
		}
		return tareaVO;
	}

	/**
	 * Metodo que extrae los modelos de la instancia por un indentificador de proceso.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param procesoDN identificador del proceso.
	 * @return lstModeloProcesoVO lista qye contiene  el modelo de la instancias.
	 * @throws AdapterBPMException
	 */
	@Override
	public List<ModeloProcesoVO> findModeloProcesoIdentificador(
			UsuarioVO usuarioVO, String procesoDN) throws AdapterBPMException {

		List<ModeloProcesoVO> lstModeloProcesoVO = null;
		try {
			List<IProcessInstance> lstProcessInstance = AdapterBPMHelper.findInstanciaProcesoDN(getBPMServiceClientFactory(), usuarioVO, procesoDN);
			lstModeloProcesoVO = new ArrayList<ModeloProcesoVO>();
			if (lstProcessInstance == null ||  lstProcessInstance.size() == AdapterBPMConstants.ZERO) {
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_MODELOS_NO_ENCONTRADOS);
			}
			for (IProcessInstance processInstance : lstProcessInstance) {
				ModeloProcesoVO modeloProcesoVO  = findModeloProcesoInstancia(usuarioVO, processInstance.getSystemAttributes().getProcessInstanceId());
				lstModeloProcesoVO.add(modeloProcesoVO);
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_ENCONTRAR_MODELOS_CON_INDENTIFICADOR, e);
		}
		return lstModeloProcesoVO;
	}

	/**
	 * Metodo que extrae los modelos de la instancia por tipo de proceso.
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param tipo tipo de proceso.
	 * @return mapaProcesoDN lista que contiene los procesos.
	 * @throws AdapterBPMException
	 */
	@Override
	public Map<String, String> findModeloProcesoTipo(UsuarioVO usuarioVO,
			String tipo) throws AdapterBPMException {
		    
		return BPMUtil.findModeloProcesoTipo(usuarioVO, tipo);
	}

	/**
	 * Extrae todas las instancias que estan asociadas a un proceso.
	 * @param usuarioVO datos del usuario.
	 * @param procesoDN  proceso.
	 * @return listaInstacia regresa los Id de las instancias.
	 * @throws AdapterBPMException
	 */
	@Override
	public List<String> findInstanciasProceso(UsuarioVO usuarioVO,
			String procesoDN) throws AdapterBPMException {
		List<String> listaInstancia = new ArrayList<String>();
		try {
			List<IProcessInstance> lstProcessInstance = AdapterBPMHelper.findInstanciaProcesoDN(getBPMServiceClientFactory(), usuarioVO, procesoDN);
			
			if(lstProcessInstance != null && lstProcessInstance.size() > AdapterBPMConstants.ZERO) {
				for (IProcessInstance processInstance: lstProcessInstance){
					listaInstancia.add(processInstance.getSystemAttributes().getProcessInstanceId());
				}
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIAS_PROCESO, e);
		}
		
		return listaInstancia;
	}
	
	

}