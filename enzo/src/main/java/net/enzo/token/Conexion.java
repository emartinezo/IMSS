package net.enzo.token;
 
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
 
import javax.naming.Context;
 
import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.StaleObjectException;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.client.IWorkflowServiceClient;
import oracle.bpel.services.workflow.client.IWorkflowServiceClientConstants;
import oracle.bpel.services.workflow.client.WorkflowServiceClientFactory;
import oracle.bpel.services.workflow.query.ITaskQueryService;
import oracle.bpel.services.workflow.repos.Column;
import oracle.bpel.services.workflow.repos.Ordering;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpel.services.workflow.task.ITaskService;
import oracle.bpel.services.workflow.task.impl.TaskUtil;
import oracle.bpel.services.workflow.task.model.CommentType;
import oracle.bpel.services.workflow.task.model.IdentityType;
import oracle.bpel.services.workflow.task.model.IdentityTypeImpl;
import oracle.bpel.services.workflow.task.model.ObjectFactory;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.bpel.services.workflow.verification.IWorkflowContext;
import oracle.bpm.client.BPMServiceClientFactory;
import oracle.bpm.services.authentication.IBPMUserAuthenticationService;
import oracle.bpm.services.client.IBPMServiceClient;
import oracle.bpm.services.instancemanagement.IInstanceManagementService;
import oracle.bpm.services.instancemanagement.model.IProcessInstance;
import oracle.bpm.services.instancequery.IColumnConstants;
import oracle.bpm.services.instancequery.IInstanceQueryInput;
import oracle.bpm.services.instancequery.IInstanceQueryService;
import oracle.bpm.services.instancequery.impl.InstanceQueryInput;
import oracle.bpm.services.processmetadata.IProcessMetadataService;
import oracle.bpm.services.processmetadata.ProcessMetadataSummary;
import oracle.soa.management.facade.ComponentInstance;
import oracle.soa.management.facade.CompositeInstance;
import oracle.soa.management.facade.Locator;
import oracle.soa.management.facade.LocatorFactory;
import oracle.soa.management.util.ComponentInstanceFilter;
import oracle.soa.management.util.CompositeInstanceFilter;
 
/**
* @author ahernandezd
*
*/
public class Conexion {
               
                private  static final String FECHA_FORMATO = "dd-MM-yyyy hh:mm:ss";
    private static String url = "t3://10.11.6.133:8001";
    private static SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);
   
//    http://10.11.6.133:10001/bpm/workspace/
   
    
    public static BPMServiceClientFactory getBPMServiceClientFactory() {
     Map<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String> properties =
            new HashMap<IWorkflowServiceClientConstants.CONNECTION_PROPERTY, String>();
 
        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY.CLIENT_TYPE,
                             WorkflowServiceClientFactory.REMOTE_CLIENT);
        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY
                            .EJB_PROVIDER_URL,url);
        properties.put(IWorkflowServiceClientConstants.CONNECTION_PROPERTY
                                      .EJB_INITIAL_CONTEXT_FACTORY,
                       "weblogic.jndi.WLInitialContextFactory");
        return BPMServiceClientFactory.getInstance(properties, null, null);
    }
   
    public static IBPMContext getIBPMContext(String username,
                                             String password) throws Exception{     
                return  getBPMServiceClientFactory().getBPMUserAuthenticationService()
                                      .authenticate(username,
                                                    password.toCharArray(),
                                                    null);
       
    }
 
    public static IWorkflowServiceClient getIWorkflowServiceClient() {
        return getBPMServiceClientFactory().getWorkflowServiceClient();
    }
   
    public static IBPMServiceClient getBPMServiceClient(){
        return getBPMServiceClientFactory().getBPMServiceClient();
    }
   
 
    public static ITaskQueryService  getTaskQueryService(){
                return getIWorkflowServiceClient().getTaskQueryService();
    }
    
    public static IWorkflowContext  getWorkflowContext(String username,String password) throws WorkflowException{
                return getTaskQueryService().authenticate(username, password.toCharArray(),"jazn.com");
    }
    public void testGetProcessInstances(){
        try {
                IInstanceQueryService queryService = Conexion.getBPMServiceClient().getInstanceQueryService();
                IBPMUserAuthenticationService authSvc = getBPMServiceClientFactory().getBPMUserAuthenticationService();
//            IBPMContext bpmContext = Conexion.getIBPMContext("weblogic", "w3bl0g1c");
                IBPMContext bpmContext = authSvc.authenticate("weblogic", "w3bl0g1c".toCharArray(), null);
               
               
               
                System.out.println("Token:"+ bpmContext.getToken());
               
            List<Column> columns = new ArrayList<Column>();
            columns.add(IColumnConstants.PROCESS_ID_COLUMN);
            columns.add(IColumnConstants.PROCESS_NUMBER_COLUMN);
            columns.add(IColumnConstants.PROCESS_STATE_COLUMN);
            columns.add(IColumnConstants.PROCESS_TITLE_COLUMN);
            columns.add(IColumnConstants.PROCESS_OWNERROLE_COLUMN);
            columns.add(IColumnConstants.PROCESS_OWNERUSER_COLUMN);
            columns.add(IColumnConstants.PROCESS_OWNERGROUP_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPOSITEDN_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPOSITEINSTANCEID_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPOSITENAME_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPOSITEVERSION_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPONENTNAME_COLUMN);
            columns.add(IColumnConstants.PROCESS_CREATOR_COLUMN);
            columns.add(IColumnConstants.PROCESS_CREATEDDATE_COLUMN);
            columns.add(IColumnConstants.PROCESS_COMPONENTTYPE_COLUMN);
            
            Ordering ordering = new Ordering(IColumnConstants.PROCESS_NUMBER_COLUMN,true,true); 
            Predicate pred1 = new Predicate(IColumnConstants.PROCESS_ID_COLUMN,Predicate.OP_CONTAINS, "30035-1");
            Predicate pred2 = new Predicate(IColumnConstants.PROCESS_CREATEDDATE_COLUMN,Predicate.OP_ON, sdf.parse("14-08-2014 11:30:55"));
            Predicate pred3 = new Predicate(IColumnConstants.PROCESS_CREATOR_COLUMN,Predicate.OP_EQ, "angelica");         
//            pred.addClause(logicalOperator, column1, operation, column2);
            InstanciaVO ivo = new InstanciaVO();
           
            ivo.setEstado("STALE");
            ivo.setFechaCreacion("14-08-2014 12:33:00");
            ivo.setIdInstancia("30034");
            ivo.setUsuarioCreador("angelica");
           
            Predicate pred =null; // PredicadosUtil.getPredicado(ivo);
//            pred = new Predicate(IColumnConstants.PROCESS_COMPONENTNAME_COLUMN,Predicate.OP_EQ, "ProcesoLargo");
//            pred = new Predicate(IColumnConstants.PROCESS_PROCESSDEFINITIONID_COLUMN,Predicate.OP_EQ, "default/ProyectoFormulario!1.0*/PasoDatos");
//            pred = new Predicate(IColumnConstants.PROCESS_COMPONENTTYPE_COLUMN,Predicate.OP_EQ, "BPMN");
            System.out.println(pred);
           
            Predicate predtmp1 = new Predicate(pred1, Predicate.AND, pred2);
//            Predicate pred = new Predicate(predtmp1, Predicate.AND, pred3);
            IInstanceQueryInput input = new InstanceQueryInput();
            input.setAssignmentFilter(IInstanceQueryInput.AssignmentFilter.ALL);
           
            List<IProcessInstance> processInstances =queryService.queryInstances(bpmContext, columns, pred, ordering, input);
            System.out.println("ProcessId\tProcess#\tState\tTitle\t\t\t\t\t\tProcess DN");
            for (IProcessInstance instance : processInstances) {
                System.out.println(instance.getSystemAttributes().getProcessInstanceId()
                                   + "\t" + instance.getSystemAttributes().getProcessNumber()
                                   + "\t" + instance.getSystemAttributes().getState()
                                   + "\t" + instance.getTitle()+"\t" + instance.getProcessDN()+"\t ####" + instance.getOwnerType().getId()+"###\t" + instance.getCreator()+"\t" + sdf.format(instance.getSystemAttributes()
                                   .getCreatedDate().getTime()));
//                System.out.println("CompositeDN: " + instance.getSca().getCompositeDN());
//                System.out.println("ComponentName: " + instance.getSca().getComponentName());
//                System.out.println("Component Type: " + instance.getSystemAttributes().getComponentType());
            }
            if (processInstances.isEmpty()){
                System.out.println("no result");
            }
            authSvc.destroyBPMContext(bpmContext);
           
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
    }
   
    public void testInitiatiable(){
        System.out.println(">>> initiating a task");
        try {
            IProcessMetadataService service = getBPMServiceClientFactory().getBPMServiceClient().getProcessMetadataService();
            IBPMContext bpmContext = Conexion.getIBPMContext("weblogic", "w3bl0g1c");
            List<ProcessMetadataSummary> initiableTasks =  service.getInitiatableProcesses(bpmContext);
            ProcessMetadataSummary pms = initiableTasks.get(0);//get the first initable task
            IInstanceManagementService ims = getBPMServiceClientFactory().getBPMServiceClient().getInstanceManagementService();
            Task task = ims.createProcessInstanceTask(bpmContext, pms.getCompositeDN()+"/"+pms.getProcessName());
            System.out.println(">>> task initiated");
            printTask(task);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private void printTask(Task task){
        System.out.println(TaskUtil.getInstance().toString(task));
    }
   
    public void initiateProcess(String processName) {
        System.out.println(" +++ initiateProcess() for :" + processName);
        try {
                IBPMServiceClient ibsc = getBPMServiceClient();
//            IProcessMetadataService service = getBPMServiceClientFactory().getBPMServiceClient().getProcessMetadataService();
//            IBPMContext bpmContext = Conexion.getIBPMContext("weblogic", "w3bl0g1c");
//            ------------------------
           
            IBPMUserAuthenticationService authSvc = getBPMServiceClientFactory().getBPMUserAuthenticationService();
            IBPMContext bpmContext = authSvc.authenticate("weblogic", "w3bl0g1c".toCharArray(), null);
//            IBPMContext bpmContext = authSvc.authenticate("javier", "password1".toCharArray(), null);
            String idInstancia = ibsc.getInstanceManagementService().createProcessInstance(bpmContext,processName);
            System.out.println("Intancia del proceso:"+ idInstancia);
            authSvc.destroyBPMContext(bpmContext);
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   
                public static Predicate getPredicadoFechaCreacion(String fecha)  {
                               Predicate predicado = null;
                               SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);
                               System.out.println("Entro a getPredicadoFechaCreacion..");
                               try {
                                               if(fecha!=null && !fecha.trim().isEmpty()){
                                                               System.out.println("Entro a getPredicadoFechaCreacion.. Validacion.");
                                                               predicado = new Predicate(IColumnConstants.PROCESS_CREATEDDATE_COLUMN,
                                                                                              Predicate.OP_ON,
                                                                                              sdf.parse(fecha));
                                               }
                               } catch (WorkflowException e) {
                                               e.printStackTrace();
                               } catch (Exception e) {
                                               e.printStackTrace();
                               }
                               return predicado;
                }
               
  public static void approvedTask(String idTask){
                IWorkflowContext workflowContext;
                try {
                               workflowContext = getWorkflowContext("javier", "password1");
                               Task t = getTaskQueryService().getTaskDetailsById(workflowContext, idTask);
                               System.out.println("TaskNumber:" + t.getSystemAttributes().getTaskNumber());
                              
                               ITaskService taskSvc = getIWorkflowServiceClient().getTaskService();
                               taskSvc.updateTaskOutcome(workflowContext, t, "APPROVE");
                               System.out.println("APPROVE");
                              
                } catch (WorkflowException e) {
                               e.printStackTrace();
                } catch (StaleObjectException e) {
                               e.printStackTrace();
                }
               
                  
  }
   
    public static void findTasksForProcess(String processInstanceId) {
        System.out.println("findTasksForProcess...");
        try {
                ObjectFactory on = new ObjectFactory();
                CommentType ct= on.createCommentType();
//            IWorkflowContext workflowContext= getIBPMContext("angelica", "password1");
          IWorkflowContext workflowContext= getWorkflowContext("angelica", "password1");
         
          
          System.out.println("Token: " + workflowContext.getDisplayNameLocale());
         
//          Task t = getTaskQueryService().getTaskDetailsById(workflowContext, "f62202c3-105c-4679-b1b2-37df4cffdc95");
//          t.getSystemAttributes().setState(IWorkflowConstants.TASK_STATE_ASSIGNED);
         
          
          Predicate instancePredicate = new Predicate(TableConstants.WFTASK_INSTANCEID_COLUMN, Predicate.OP_EQ, processInstanceId);
          Ordering taskOrdering = new Ordering(IColumnConstants.PROCESS_NUMBER_COLUMN,true,true); 
          List<String> queryColumns = new ArrayList<String>();
         
          queryColumns.add(TableConstants.WFTASK_TITLE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_PRIORITY_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_STATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_SUBSTATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ACQUIREDBY_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_TASKID_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_TASKNUMBER_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_PROCESSNAME_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_PROCESSID_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_INSTANCEID_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_PROCESSVERSION_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_IDENTIFICATIONKEY_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ASSIGNEEGROUPS_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ASSIGNEEUSERS_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_OWNERGROUP_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_OWNERROLE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_OWNERUSER_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_CREATEDDATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_CUSTOMATTRIBUTEDATE1_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_CUSTOMATTRIBUTEDATE2_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ASSIGNEDDATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ENDDATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_STARTDATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_FROMUSER_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_FROMUSERDSIPLAYNAME_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_DUEDATE_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_ASSIGNEES_COLUMN.getName());
          queryColumns.add(TableConstants.WFTASK_OUTCOME_COLUMN.getName());
 
         
          
          List<ITaskQueryService.OptionalInfo> optionalInfo = new ArrayList<ITaskQueryService.OptionalInfo>();
          optionalInfo.add(ITaskQueryService.OptionalInfo.CUSTOM_ACTIONS);
          optionalInfo.add(ITaskQueryService.OptionalInfo.COMMENTS);
          optionalInfo.add(ITaskQueryService.OptionalInfo.PAYLOAD);
          optionalInfo.add(ITaskQueryService.OptionalInfo.DISPLAY_INFO);
         
          List<Task> tasks =  getTaskQueryService().queryTasks(workflowContext,
                                                   queryColumns,
                                                   null,
                                                   ITaskQueryService.AssignmentFilter.ALL,
                                                   null,
                                                   instancePredicate,
                                                   taskOrdering,
                                                   0,
                                                   10);
         
          for (int i = 0; i < tasks.size(); i++) {
                  Task task = tasks.get(i);
              System.out.println("\t#task id: " + task.getSystemAttributes().getTaskId() +
                                                                               "\t#task number: " + task.getSystemAttributes().getTaskNumber() +
                                 "\t#creator: " + task.getCreator()+
                                 "\t#owner role: " + task.getOwnerRole() +
                                 "\t#owner user: " + task.getOwnerUser() +
                                 "\t#owner group: " + task.getOwnerGroup() +
                                 "\t#owner group display name: " + task.getOwnerGroupDisplayName() +
                                 "\t#owner role display name: " + task.getOwnerRoleDisplayName() +
                                 "\t#owner role user display name: " + task.getOwnerUserDisplayName() +
                                 "\t#title: " + task.getTitle() +
                                 "\t#state: "+task.getSystemAttributes().getState() +
                                 "\t#outcome: " + task.getSystemAttributes().getOutcome() +
//                                 "\t#date create: " + sdf.format(task.getSystemAttributes().getCreatedDate().getTime()) +
//                                 "\t#date end: " + sdf.format(task.getSystemAttributes().getEndDate().getTime()) +
//                                 "\t#date start: " + sdf.format(task.getStartDate().getTime()) +
                                 "\t#instanceId: " + task.getProcessInfo().getInstanceId() +
                                                                              "\t#processId: " + task.getProcessInfo().getProcessId() +
                                                                               "\t#processName: " + task.getProcessInfo().getProcessName());
             
//              System.out.println(task.getSystemAttributes().getAssignees().size());
             
              List l = task.getSystemAttributes().getAssignees();
              String assignees = "";
              IdentityTypeImpl iti;
       
              for (int j = 0; j < l.size(); j++) {
                  iti = (IdentityTypeImpl)l.get(j);
                  assignees = assignees + " " + iti.getId();
              }
 
              System.out.println(" Assignees:" +
                                 assignees);
//              executeAsignados(task);
          }
          getTaskQueryService().destroyWorkflowContext(workflowContext);
          System.out.println("Cierra sesion..");
        }catch (WorkflowException e){
                e.printStackTrace();
        }catch (Exception e) {
          e.printStackTrace();
        }
      }
   
    
                private static List<String> executeAsignados(Task tarea){
                               List<String> listaAsignados = new ArrayList<String>();
                               List <?> asignados = tarea.getSystemAttributes().getAssignees();
                              
        for (Object asignado : asignados) {
                listaAsignados.add(((IdentityType)asignado).getDisplayName());
                System.out.println((((IdentityType)asignado).getDisplayName()));
        }
                               return listaAsignados;
                }
                private static void recorre(Task task){
        String assigneess = "";
       List<IdentityType> listAssignees = task.getSystemAttributes().getAssignees();
        //if (!listAssignees.isEmpty())
 
        for (IdentityType it : listAssignees) {
            assigneess += it.getId() + " ";
            System.out.println("DisplayName: "+assigneess+" - "+it.getDisplayName() + " - " + it.getSystemVersionFlag() + " - "+ it.getType());
        }
       
        List<?> assignees = task.getSystemAttributes().getAssigneeUsers();
        StringBuffer buffer = null;
        for (int i = 0; i < assignees.size(); i++) {
            IdentityType type = (IdentityType)assignees.get(i);
            String name = type.getId();
            if (buffer == null) {
                buffer = new StringBuffer();
            } else {
                buffer.append(",");
            }
            buffer.append(name).append("(U)");
        }
//        System.out.println(buffer);
//        assignees = task.getSystemAttributes().getAssigneeGroups();
//        for (int i = 0; i < assignees.size(); i++) {
//            IdentityType type = (IdentityType)assignees.get(i);
//            String name = type.getId();
//            if (buffer == null) {
//                buffer = new StringBuffer();
//            } else {
//                buffer.append(",");
//            }
//            buffer.append(name).append("(G)");
//        }
//        if (buffer == null) {
//        } else {
//            System.out.println( buffer.toString());
//        }
       
        
        System.out.println( buffer);
                }
                public static List<TareaDTO> mapeaTarea(List <TareaVO> listaTareaVO){
                               List <TareaDTO> listaTareaDTO = new ArrayList<TareaDTO>();
                              
                               for (TareaVO tareaVO : listaTareaVO) {
                                               TareaVO tareaDTO = new TareaVO();
                                               tareaDTO.setEstado(tareaVO.getEstado());
                                               //tareaDTO.setFechaFin(tareaVO.getFechaFin());
                                               tareaDTO.setFechaInicio(tareaVO.getFechaInicio());
                                               tareaDTO.setIdTarea(tareaVO.getIdTarea());
                                               tareaDTO.setNombreTarea(tareaVO.getNombreTarea());
                                               tareaDTO.setNumeroTarea(tareaVO.getNumeroTarea());
                                               tareaDTO.setPrioridad(tareaVO.getPrioridad());
                                               tareaDTO.setRol(tareaVO.getRol());
                                               tareaDTO.setUrlTarea(tareaVO.getUrlTarea());
                                               tareaDTO.setUsuarioCreador(tareaVO.getUsuarioCreador());
                               }
                               return listaTareaDTO;
                }
   
    static void  tipoComponente() {
               
                Hashtable<String, String> jndiProps = new Hashtable<String, String>();
                jndiProps.put(Context.PROVIDER_URL, url);
                jndiProps.put(Context.INITIAL_CONTEXT_FACTORY,"weblogic.jndi.WLInitialContextFactory");
                jndiProps.put(Context.SECURITY_PRINCIPAL, "weblogic");
                jndiProps.put(Context.SECURITY_CREDENTIALS, "w3bl0g1c");
               
                Hashtable<String, String> processDN = new Hashtable<String, String>();
               
                ComponentInstanceFilter componentFilterBpel = new ComponentInstanceFilter();
                componentFilterBpel.setEngineType(Locator.SE_BPMN);
                List<CompositeInstance> composites;
                try {
                               Locator locator = LocatorFactory.createLocator(jndiProps);
 
                               CompositeInstanceFilter filter = new CompositeInstanceFilter();
                               composites = locator.getCompositeInstances(filter);
 
                               for (CompositeInstance composite : composites) {
                                               System.out.println("CompositeDN : " + composite.getCompositeDN());
                                              
                                               List<ComponentInstance> bpels = composite.getChildComponentInstances(componentFilterBpel);
                                               for (ComponentInstance bpel : bpels) {
                                                               System.out.println("ComponentName : " + bpel.getComponentName());
                                                               processDN.put(composite.getCompositeDN()+"/"+bpel.getComponentName() , composite.getCompositeDN()+"/"+bpel.getComponentName());
                                               }
                               }
                              
                               locator.close();
                              
                               Enumeration<String> e = processDN.keys();
                               Object clave;
                               Object valor;
                               while( e.hasMoreElements() ){
                                 clave = e.nextElement();
                                 valor = processDN.get( clave );
                                 System.out.println( "Clave : " + clave + " - Valor : " + valor );
                               }
                              
                } catch (Exception e) {
                               e.printStackTrace();
                }
 
    }
    public static void main(String[] args) {
            try {
//                           IInstanceQueryService queryService = Conexion.getBPMServiceClient().getInstanceQueryService();
//                                                           IBPMContext bpmContext = Conexion.getIBPMContext("weblogic", "w3bl0g1c");
//                                                          
               
//                           tipoComponente();
                Conexion client = new Conexion();
//                           client.testGetProcessInstances();
//                           client.testInitiatiable();
//                           client.initiateProcess("default/ProyectoDesarrollo!1.0*/ProcesoManual");
//                           client.testGetInitiatiableTasks();
//                           Predicate pred = getPredicadoFechaCreacion("holas");
                Conexion.findTasksForProcess("70001");
               
//                           Conexion.approvedTask("4da556f9-4353-42bf-baf1-3652fa8539d4");
//                           Conexion.findTasksForProcess("40047");
               
                //200171
//                           List <TareaDTO> tareas = mapeaTarea(null);
               
               
                                               } catch (Exception e) {
                                                               e.printStackTrace();
                                               }
                }
   
    
 
}