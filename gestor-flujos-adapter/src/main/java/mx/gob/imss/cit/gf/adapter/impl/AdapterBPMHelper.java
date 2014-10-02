package mx.gob.imss.cit.gf.adapter.impl;

import java.util.ArrayList;
import java.util.List;

import mx.gob.imss.cit.gf.adapter.constant.AdapterBPMConstants;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMCodeExceptionEnum;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMExceptionBuilder;
import mx.gob.imss.cit.gf.adapter.util.PredicadosUtil;
import mx.gob.imss.cit.gf.util.DateUtil;
import mx.gob.imss.cit.gf.vo.ActividadVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;
import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Column;
import oracle.bpel.services.workflow.repos.Ordering;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.collections.Sequence;
import oracle.bpm.project.SequenceFlowImpl;
import oracle.bpm.project.model.ProjectObject;
import oracle.bpm.services.common.exception.BPMException;
import oracle.bpm.services.instancemanagement.IInstanceManagementService;
import oracle.bpm.services.instancemanagement.model.IIdentityType;
import oracle.bpm.services.instancemanagement.model.IProcessInstance;
import oracle.bpm.services.instancequery.IAuditInstance;
import oracle.bpm.services.instancequery.IColumnConstants;
import oracle.bpm.services.instancequery.IInstanceQueryInput;
import oracle.bpm.services.instancequery.IInstanceQueryService;
import oracle.bpm.services.instancequery.impl.InstanceQueryInput;
import oracle.bpm.services.internal.processmodel.IProcessModelService;
import oracle.bpm.services.internal.processmodel.model.IProcessModelPackage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * Clase quw ayuda a la impkementacion del API
 * @author admin
 *
 */
public final class AdapterBPMHelper {

	/**
	 * COntructor privado
	 */
	private AdapterBPMHelper() {

	}

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AdapterBPMHelper.class);

	/**
	 * 
	 * @param instance
	 * @return
	 */
	public static InstanciaVO executeIProcessInstanceToInstanciaVO(
			IProcessInstance instance) {
		InstanciaVO instanciavo = new InstanciaVO();
		try {
			instanciavo.setEstado(instance.getSystemAttributes().getState());
			instanciavo.setFechaCreacion(DateUtil.formatDateToString(instance
					.getSystemAttributes().getCreatedDate().getTime()));
			instanciavo.setIdInstancia(instance.getSystemAttributes()
					.getProcessInstanceId());
			instanciavo.setNombreProceso(instance.getTitle());
			instanciavo.setIdProceso(Long.valueOf(instance.getSystemAttributes().getProcessNumber()));
			instanciavo.setProcesoDN(instance.getProcessDN());
			instanciavo.setRol(instance.getOwnerType().getId());
			instanciavo.setUsuarioCreador(instance.getCreator());

			LOG.info(instance.getSystemAttributes().getProcessInstanceId()
					+ "\t"
					+ instance.getSystemAttributes().getProcessNumber()
					+ "\t"
					+ instance.getSystemAttributes().getState()
					+ "\t"
					+ instance.getTitle()
					+ "\t"
					+ instance.getProcessDN()
					+ "\t"
					+ instance.getOwnerType()
					+ "\t"
					+ instance.getCreator()
					+ "\t"
					+ DateUtil.formatDateToString(instance
							.getSystemAttributes().getCreatedDate().getTime()));
		} catch (Exception ex) {

			instanciavo = null;
		}
		return instanciavo;
	}
	
	/**
	 * Metodo que extrae los valores para la lista de tareas.
	 * @param listaTareas lista de tareas.
	 * @return listaTareaVO lista de tareas mapeadas.
	 */
	public static List<TareaVO> executeMapeaListaTarea(List<Task> listaTareas) {

		List<TareaVO> listaTareaVO = null;
		listaTareaVO = new ArrayList<TareaVO>();
		for (int i = 0; i < listaTareas.size(); i++) {
			Task task = listaTareas.get(i);
			TareaVO tarea = new TareaVO();
			tarea.setNombreTarea(task.getTitle());
			tarea.setEstado(task.getSystemAttributes().getState());
			tarea.setIdTarea(task.getSystemAttributes().getTaskId());
			tarea.setNumeroTarea(task.getSystemAttributes().getTaskNumber());
			tarea.setPrioridad(task.getPriority() != AdapterBPMConstants.PRIORIDAD_ZERO?task.getPriority() : null);
			tarea.setUsuarioCreador(task.getCreator());
			tarea.setFechaInicioEjecucion(task.getStartDate() != null?DateUtil.formatDateToString(task.getStartDate().getTime()) : null);
			tarea.setFechaFinEjecucion(task.getSystemAttributes().getEndDate() != null ? DateUtil.formatDateToString(task.getSystemAttributes().getEndDate()
					.getTime()) : null);
			tarea.setFechaCompromiso(task.getDueDate() != null?DateUtil.formatDateToString(task.getDueDate().getTime()) : null);
			tarea.setFechaInicio(task.getSystemAttributes().getCreatedDate() != null?DateUtil.formatDateToString(task.getSystemAttributes().getCreatedDate()
					.getTime()) : null);
			tarea.setUrlTarea(task.getTaskDisplayUrl());
			tarea.setRol(task.getOwnerRole());
			tarea.setResponsableActividad(executeAsignados(task));
			listaTareaVO.add(tarea);
		}
		return listaTareaVO;
	}	
	
	/**
	 * Meotodo que extrae los usuarios asignados a la tareas.
	 * @param tarea contiene usuarios asignados.
	 * @return listaAsignados la lista de usuarios.
	 */
	public static List<String> executeAsignados(Task tarea){
		List<String> listaAsignados = new ArrayList<String>();
		List <?> asignados = tarea.getSystemAttributes().getAssignees();
		
        for (Object asignado : asignados) {
        	listaAsignados.add(((IdentityType)asignado).getDisplayName());
        }
		return listaAsignados;
	}

	/**
	 * 
	 * @param bpmServiceClientFactory
	 * @param instanciaVO
	 * @param usuarioVO
	 * @return
	 * @throws AdapterBPMException
	 */
	public static List<InstanciaVO> findInstanciaDetalle(
			BPMServiceClientFactory bpmServiceClientFactory,
			InstanciaVO instanciaVO, UsuarioVO usuarioVO)
			throws AdapterBPMException {

		LOG.info("Consulta instancias...");
		List<InstanciaVO> listaInstancia = new ArrayList<InstanciaVO>();
		try {

			IInstanceQueryService queryService = bpmServiceClientFactory
					.getBPMServiceClient().getInstanceQueryService();

			List<Column> columns = new ArrayList<Column>();
			columns.add(IColumnConstants.PROCESS_ID_COLUMN);
			columns.add(IColumnConstants.PROCESS_NUMBER_COLUMN);
			columns.add(IColumnConstants.PROCESS_STATE_COLUMN);
			columns.add(IColumnConstants.PROCESS_TITLE_COLUMN);
			columns.add(IColumnConstants.PROCESS_OWNERROLE_COLUMN);
			columns.add(IColumnConstants.PROCESS_OWNERUSER_COLUMN);
			columns.add(IColumnConstants.PROCESS_COMPOSITEDN_COLUMN);
			columns.add(IColumnConstants.PROCESS_CREATOR_COLUMN);
			columns.add(IColumnConstants.PROCESS_CREATEDDATE_COLUMN);

			Ordering ordering = new Ordering(
					IColumnConstants.PROCESS_NUMBER_COLUMN, true, true);
			Predicate pred = PredicadosUtil.getPredicadoInstancias(instanciaVO);
			IInstanceQueryInput input = new InstanceQueryInput();
			input.setAssignmentFilter(IInstanceQueryInput.AssignmentFilter.ALL);

			List<IProcessInstance> listProcessInstance = queryService
					.queryInstances((IBPMContext) ContextUserCache
							.get(usuarioVO.getUsuario()), columns, pred,
							ordering, input);
			if (listProcessInstance == null || listProcessInstance.isEmpty()) {
				throw AdapterBPMExceptionBuilder.build(
						AdapterBPMCodeExceptionEnum.ERROR_NO_RESULTADOS,
						new AdapterBPMException());
			}

			for (IProcessInstance instance : listProcessInstance) {
				InstanciaVO instanciavo = AdapterBPMHelper
						.executeIProcessInstanceToInstanciaVO(instance);
				if (instanciavo != null) {
					listaInstancia.add(instanciavo);
				}
			}

		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO,
							e);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (BPMException e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO,
							e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO,
							e);
		}

		return listaInstancia;
	}

	/**
	 * 
	 * @param usuarioVO
	 * @param tareaVO
	 * @param idInstancia
	 * @return
	 * @throws AdapterBPMException
	 */
	public static List<TareaVO> findTareasInstancia(BPMServiceClientFactory bpmServiceClientFactory,UsuarioVO usuarioVO,
			TareaVO tareaVO, String idInstancia) throws AdapterBPMException {
		List<TareaVO> listaTareaVO = null;
		List<Task> listaTareas = null;
		try {
			IWorkflowContext workflowContext = ContextUserCache.get(usuarioVO
					.getUsuario());
			ITaskQueryService tQueryService = bpmServiceClientFactory
					.getWorkflowServiceClient().getTaskQueryService();
			Predicate instancePredicate = PredicadosUtil.getPredicadoTareas(
					tareaVO, idInstancia);
			Ordering ordening = new Ordering(
					IColumnConstants.PROCESS_NUMBER_COLUMN, true, true);

			List<String> queryColumns = new ArrayList<String>();
			queryColumns.add(TableConstants.WFTASK_TITLE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_PRIORITY_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_STATE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_TASKID_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_TASKNUMBER_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_CREATOR_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_STARTDATE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_ENDDATE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_TASKDISPLAYURL_COLUMN
					.getName());
			queryColumns.add(TableConstants.WFTASK_OWNERROLE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_OWNERGROUP_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_OWNERUSER_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_DUEDATE_COLUMN.getName());
			queryColumns
					.add(TableConstants.WFTASK_CREATEDDATE_COLUMN.getName());
			queryColumns.add(TableConstants.WFTASK_ASSIGNEES_COLUMN.getName());

			boolean masTareas = true;
			listaTareas = new ArrayList<Task>();
			int inicio = AdapterBPMConstants.IND_PAGINA_INICIO;
			int fin = AdapterBPMConstants.IND_PAGINA_FIN;
			while (masTareas) {
				List<Task> tareas = tQueryService.queryTasks(workflowContext,
						queryColumns, null,
						ITaskQueryService.AssignmentFilter.ALL, null,
						instancePredicate, ordening, inicio, fin);
				listaTareas.addAll(tareas);
				if (tareas.size() < AdapterBPMConstants.IND_PAGINA_FIN) {
					masTareas = false;
				} else {
					inicio += AdapterBPMConstants.IND_PAGINA_FIN;
					fin += AdapterBPMConstants.IND_PAGINA_FIN;
				}
			}

			if (listaTareas.isEmpty()) {
				throw AdapterBPMExceptionBuilder
						.build(AdapterBPMCodeExceptionEnum.ERROR_TAREAS_NO_ENCONTRADAS,
								new AdapterBPMException());
			} else {
				listaTareaVO = executeMapeaListaTarea(listaTareas);
			}

		} catch (WorkflowException e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(
					AdapterBPMCodeExceptionEnum.ERROR_CONUSULTAR_TAREA, e);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			LOG.info(e.getMessage());
			throw AdapterBPMExceptionBuilder.build(
					AdapterBPMCodeExceptionEnum.ERROR_CONUSULTAR_TAREA, e);
		}
		return listaTareaVO;
	}
	
	
	
	/**
	 * Metodo que realiza la busqueda de modelo de proceso por instancia,
	 * 	extrae las activides que ya fueron ejecutadas y las proximas a ejecutar.
	 * @param usuarioVO objeto que contiene los datos para iniciar sesion.
	 * @param idInstancia identificador de instancias de procesos.
	 * @return ModeloProcesoVO contiene el modelo de la instancias.
	 * @throws AdapterBPMException
	 */
	public static ModeloProcesoVO findModeloProcesoInstancia(BPMServiceClientFactory bpmServiceClientFactory, UsuarioVO usuarioVO, String idInstancia) throws AdapterBPMException {
		ModeloProcesoVO modeloProcesoVO = null;
		try {
			IBPMContext bpmContext = (IBPMContext)ContextUserCache.get(usuarioVO.getUsuario());
			IInstanceQueryService instanceQueryService =
					bpmServiceClientFactory.getBPMServiceClient().getInstanceQueryService();
			IProcessInstance processInstance =
					instanceQueryService.getProcessInstance(bpmContext, idInstancia);
			if (processInstance == null) {
				throw AdapterBPMExceptionBuilder.build(
				  AdapterBPMCodeExceptionEnum.ERROR_MODELO_NO_ENCONTRADO, new AdapterBPMException());
			}
			IProcessModelService processModelService =  bpmServiceClientFactory.getBPMServiceClient()
					.getProcessModelService();
			IProcessModelPackage processModelPackage = processModelService.getProcessModel(bpmContext, 
					processInstance.getSca().getCompositeDN(), processInstance.getSca().getComponentName());
			List<IAuditInstance> ltsAuditInstances =
					bpmServiceClientFactory.getBPMServiceClient().getInstanceQueryService()
					.queryAuditInstanceByProcessId(bpmContext, idInstancia);
			
			modeloProcesoVO = findActividades(ltsAuditInstances, processModelPackage.getProcessModel().getChildren());
			modeloProcesoVO.setNombreProceso(processInstance.getSca().getComponentName());
			modeloProcesoVO.setProcesoDN(processInstance.getProcessDN());
			modeloProcesoVO.setIdInstancia(processInstance.getSystemAttributes().getProcessInstanceId());
			
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (BPMException e) {
			throw AdapterBPMExceptionBuilder.build(
					AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_MODELO_PROCESO_INSTANCIA, e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(
					AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_MODELO_PROCESO_INSTANCIA, e);
		}
		return modeloProcesoVO; 
	}

	/**
	 * Metodo que extrae la lista de actividades ejecutadas y a ejecutarse.
	 * @param lstAuditInstances lista de componentes iniciados.
	 * @param lstSequence lista de componentes del flujo.
	 * @return ModeloProcesoVO
	 */
	public static ModeloProcesoVO findActividades(List<IAuditInstance> lstAuditInstances, Sequence<? extends ProjectObject> lstSequence ) {
		
		List <ActividadVO> lstActividadIniciada = new ArrayList <ActividadVO>();
		List <IAuditInstance> lstAuditInstance = new ArrayList<IAuditInstance>();
		ModeloProcesoVO modeloProcesoVO = new ModeloProcesoVO();
		
		for (IAuditInstance auditInstance : lstAuditInstances) {
			if(auditInstance.getAuditInstanceType().compareTo(AdapterBPMConstants.IND_START) == 0 &&  
					auditInstance.getFlowElementType().compareTo(AdapterBPMConstants.TYPE_ELEMENT_ACTIVITY)==AdapterBPMConstants.ZERO){
				ActividadVO actividadVO = new ActividadVO();
				
				actividadVO.setIdActividad(auditInstance.getActivityId());
				actividadVO.setNivelAuditoria(auditInstance.getAuditInstanceType());
				actividadVO.setNombre(auditInstance.getLabel());
				actividadVO.setRol(auditInstance.getRoleId());
				actividadVO.setTipoElemento(auditInstance.getActivityName());
				
				lstActividadIniciada.add(actividadVO);
				lstAuditInstance.add(auditInstance);
			}
		}
		
		List <ProjectObject> listaPO  = new ArrayList<ProjectObject>();
		for(ProjectObject po : lstSequence) {
			listaPO.add(po);
		}
		
		modeloProcesoVO.setListaActividadIniciada(lstActividadIniciada);
		modeloProcesoVO.setListaActividadProxima(findActividadesProximas(lstAuditInstance, listaPO));
		
		return modeloProcesoVO;
	}
	/**
	 * Metodo que extrae la lista de actividades proximas a ejecutarse.
	 * @param lstAuditInstance lista de elementos iniciados.
	 * @param listaPO lista que contiene los elmentos del flujo.
	 * @return lstActividadProxima lista de componentes proximos.
	 */
	public static List<ActividadVO> findActividadesProximas(List<IAuditInstance> lstAuditInstance, List <ProjectObject> listaPO){
		List <ActividadVO> lstActividadProxima = new ArrayList <ActividadVO>();
		int indice =0;
		int contador=0;
		
		for (IAuditInstance auditInstance : lstAuditInstance) {
			contador = 0;
			for (ProjectObject po : listaPO) {
				if (po instanceof SequenceFlowImpl && ((SequenceFlowImpl)po).getTarget().getId().compareTo(auditInstance.getActivityId()) == 0) {
					indice = contador;
					break;
				}				
				contador++;
			}
			listaPO.remove(indice);
		}
		
		for (ProjectObject po : listaPO) {
			if (po instanceof SequenceFlowImpl && !((SequenceFlowImpl)po).getTarget().getId().startsWith(AdapterBPMConstants.IND_EVT)) {
					ActividadVO actividadVO = new ActividadVO();					
					actividadVO.setIdActividad(((SequenceFlowImpl)po).getTarget().getId());
					actividadVO.setTipoElemento(((SequenceFlowImpl)po).getTarget().getBpmnType().toString());
					actividadVO.setNombre(((SequenceFlowImpl)po).getTarget().getDefaultLabel());
					lstActividadProxima.add(actividadVO);				
			}
		}
		
		
		return lstActividadProxima;
	}
	/**
	 * Metodo que permite buscar una instancia por su Id
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO Objeto que contiene el id de la instancia.
	 * @return InstanciaVO Si fue encontrado el id de la instancia.
	 */
	public static IProcessInstance findInstanciaById(BPMServiceClientFactory bpmServiceClientFactory,InstanciaVO instanciaVO,UsuarioVO usuarioVO){
		IProcessInstance instance=null;
		try {
			IInstanceQueryService queryService = bpmServiceClientFactory.getBPMServiceClient().getInstanceQueryService();
			instance =
					queryService.getProcessInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()), instanciaVO.getIdInstancia());

		}catch(Exception e){
			LOG.error(e.getMessage(),e);
		}
		return instance;
	}


	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws AdapterBPMException
	 */
	public static boolean updateInstancia(BPMServiceClientFactory bpmServiceClientFactory,UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException{
		boolean ban=false;
		LOG.info(usuarioVO.toString());
		LOG.info(instanciaVO.getIdInstancia());
		try {
			IProcessInstance processInstance= findInstanciaById(bpmServiceClientFactory,instanciaVO, usuarioVO);
			if (processInstance==null){
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_NO_ENCONTRADA);
			}
			InstanciaVO instanciaVOTemp=AdapterBPMHelper.executeIProcessInstanceToInstanciaVO(processInstance);

			if (PredicadosUtil.TASK_STATE_RUNNING.equals(instanciaVOTemp.getEstado())  || PredicadosUtil.TASK_STATE_PLANNED.equals(instanciaVOTemp.getEstado()) ||
					IInstanceQueryInput.PROCESS_STATE_OPEN.equals(instanciaVOTemp.getEstado())){

				IInstanceManagementService queryService = bpmServiceClientFactory.getBPMServiceClient().getInstanceManagementService();


				if (IInstanceQueryInput.PROCESS_CANCEL_STATE.equals(instanciaVO.getEstado()))
				{
					queryService.cancelProcessInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()), processInstance);
					ban=true;
				}else if (IInstanceQueryInput.PROCESS_STATE_SUSPENDED.equals(instanciaVO.getEstado())){
					queryService.suspendInstance((IBPMContext)ContextUserCache.get(usuarioVO.getUsuario()), processInstance);
					ban=true;
				}

			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_TASK_ESTADO_NO_VALIDO, new AdapterBPMException() );
			}

		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch(Exception e){
			LOG.error(e.getMessage(),e);
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_UPDATE_INSTANCE, e);
		}
		return ban;
	}

	
	/**
	 * Metodo que permite buscar los roles de la instancia del Proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return List<RolVO> Lista de los Roles
	 * @throws AdapterBPMException
	 */	
	public static List<RolVO> findRolesByInstaceProcess(BPMServiceClientFactory bpmServiceClientFactory,UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws AdapterBPMException{
		
		List<RolVO> roles=null;
		
		LOG.info("\n\n\n\n\n\n finduserByInstace TEST");
		try{
			IProcessInstance processInstance= findInstanciaById(bpmServiceClientFactory,instanciaVO, usuarioVO);
			
			if (processInstance!=null){
			
			IInstanceQueryInput input = new InstanceQueryInput();
	        input.setAssignmentFilter(IInstanceQueryInput.AssignmentFilter.MY_AND_GROUP);
	        
	        
			List<IIdentityType> listIdemtity=processInstance.getSystemAttributes().getAssignees();
				if (!listIdemtity.isEmpty()) {
					roles= new ArrayList<RolVO>();
					for (IIdentityType identityType:listIdemtity){
						
						RolVO rol = new RolVO();
						rol.setRolName(identityType.getId());
						LOG.info("identityType="+identityType.getDisplayName()+","+identityType.getType()+","+identityType.getId());
						roles.add(rol);
					}
				}else{
					throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_ROLES_INSTANCIA_NO_ENCONTRADOS, new AdapterBPMException());
				}
			}else{
				throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_INSTANCIA_NO_ENCONTRADA, new AdapterBPMException());
			}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO, e);
		}
		return roles;	
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
	public static List<String> findResponsablesInstancias(BPMServiceClientFactory bpmServiceClientFactory,List<String> idInstanciasProcesos, UsuarioVO usuarioVO) throws AdapterBPMException {

		List<String> responsables = null;
		Predicate pred = null;

		try {
			IInstanceQueryService queryService = bpmServiceClientFactory.getBPMServiceClient().getInstanceQueryService();

			List<Column> columns = new ArrayList<Column>();
			columns.add(IColumnConstants.PROCESS_CREATOR_COLUMN);

			Ordering ordering = new Ordering(IColumnConstants.PROCESS_NUMBER_COLUMN, true, true);

			pred = new Predicate(IColumnConstants.PROCESS_STATE_COLUMN, Predicate.OP_EQ, PredicadosUtil.TASK_STATE_OPEN);

			if (idInstanciasProcesos != null && !idInstanciasProcesos.isEmpty()) {
				pred.addClause(Predicate.AND, IColumnConstants.PROCESS_ID_COLUMN, Predicate.OP_IN, idInstanciasProcesos);
			}

			IInstanceQueryInput input = new InstanceQueryInput();
			input.setAssignmentFilter(IInstanceQueryInput.AssignmentFilter.ALL);

			List<IProcessInstance> processInstances = queryService.queryInstances((IBPMContext) ContextUserCache.get(usuarioVO.getUsuario()), columns, pred,
					ordering, input);

			if (!processInstances.isEmpty()) {
				responsables = new ArrayList<String>();

				for (IProcessInstance instance: processInstances) {
					responsables.add(instance.getCreator());
				}
			}

		} catch(BPMException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_RECUPERAR_RESPONSABLES_INSTANCIAS_PROCESO, e);
		} catch(Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_RECUPERAR_RESPONSABLES_INSTANCIAS_PROCESO, e);
		}

		return responsables;
	}
	


	

	/**
	 * Metodo que extrae todas las instancias asociadas 
	 * a un Identificador del modelo.
	 * @param bpmServiceClientFactory
	 * @param usuarioVO datos para iniciar la sesion.
	 * @param compositeDN uri del proceso.
	 * @return listProcessInstance lista de isntancias.
	 * @throws AdapterBPMException
	 */
	public static List<IProcessInstance> findInstanciaProcesoDN(BPMServiceClientFactory bpmServiceClientFactory,
			UsuarioVO usuarioVO, String procesoDN) throws AdapterBPMException {

		LOG.info("Consulta instancias...");
		List<IProcessInstance> listProcessInstance = null;
		try {
			IInstanceQueryService queryService = bpmServiceClientFactory
					.getBPMServiceClient().getInstanceQueryService();

			List<Column> columns = new ArrayList<Column>();
			columns.add(IColumnConstants.PROCESS_ID_COLUMN);
			columns.add(IColumnConstants.PROCESS_NUMBER_COLUMN);
			columns.add(IColumnConstants.PROCESS_COMPOSITEDN_COLUMN);
			columns.add(IColumnConstants.PROCESS_COMPONENTNAME_COLUMN);

			Ordering ordering = new Ordering(IColumnConstants.PROCESS_NUMBER_COLUMN, true, true);
			Predicate pred = new Predicate(IColumnConstants.PROCESS_PROCESSDEFINITIONID_COLUMN,Predicate.OP_EQ, procesoDN);
			IInstanceQueryInput input = new InstanceQueryInput();
			input.setAssignmentFilter(IInstanceQueryInput.AssignmentFilter.ALL);

			listProcessInstance = queryService.queryInstances((IBPMContext) ContextUserCache
							.get(usuarioVO.getUsuario()), columns, pred, ordering, input);
			
			if (listProcessInstance == null || listProcessInstance.isEmpty()) {
				throw AdapterBPMExceptionBuilder.build(
						AdapterBPMCodeExceptionEnum.ERROR_NO_RESULTADOS);
			}

		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO, e);
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (BPMException e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO, e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder
					.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_INSTANCIA_PROCESO, e);
		}

		return listProcessInstance;
	}
		
	
}
