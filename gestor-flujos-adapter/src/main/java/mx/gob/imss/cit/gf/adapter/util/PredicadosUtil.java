/**
 * 
 */
package mx.gob.imss.cit.gf.adapter.util;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMCodeExceptionEnum;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMExceptionBuilder;
import mx.gob.imss.cit.gf.util.DateUtil;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import oracle.bpel.services.workflow.IWorkflowConstants;
import oracle.bpel.services.workflow.WorkflowException;
import oracle.bpel.services.workflow.repos.Column;
import oracle.bpel.services.workflow.repos.Predicate;
import oracle.bpel.services.workflow.repos.TableConstants;
import oracle.bpm.services.instancequery.IColumnConstants;

/**
 * @author ahernandezd
 *
 */
public final  class PredicadosUtil {
	
	/**
	 * Contructor privado
	 */
	private PredicadosUtil(){
		
	}
	


	/**
	 * Estado instancia (open).
	 */
	public static final String TASK_STATE_OPEN = "OPEN";
	/**
	 * Estado instancia (PLANNED).
	 */
	public static final String TASK_STATE_PLANNED = "PLANNED";
	/**
	 * Estado instancia (REJECTED).
	 */
	public static final String TASK_STATE_REJECTED = "REJECTED";
	/**
	 * Estado instancia (PROGRAMMEND).
	 */
	public static final String TASK_STATE_PROGRAMMED = "PROGRAMMED";
	/**
	 * Estado instancia (RUNNING).
	 */
	public static final String TASK_STATE_RUNNING = "RUNNING";
	
	
	
	/**
	 * MApa de predicados por estado
	 */
	private static Map<String,Predicate> predicadoEstado;
	
	/**
	 * Metodo que iniciliza el predicado para realizar la bsuqueda por estado.
	 * @param estado de la instancia.
	 * @return predicado de filtro para la busqueda por estado.
	 * @throws AdapterBPMException
	 */
	
	private static void initPredicado(Column columna) throws WorkflowException{
		predicadoEstado=new HashMap<String,Predicate>();
		predicadoEstado.put(TASK_STATE_OPEN, new Predicate(columna,Predicate.OP_EQ,TASK_STATE_OPEN));
		predicadoEstado.put(TASK_STATE_PLANNED, new Predicate(columna,Predicate.OP_EQ,TASK_STATE_PLANNED));
		predicadoEstado.put(TASK_STATE_REJECTED, new Predicate(columna,Predicate.OP_EQ,TASK_STATE_REJECTED));
		predicadoEstado.put(TASK_STATE_PROGRAMMED, new Predicate(columna,Predicate.OP_EQ,TASK_STATE_PROGRAMMED));
		predicadoEstado.put(TASK_STATE_RUNNING, new Predicate(columna,Predicate.OP_EQ,TASK_STATE_RUNNING));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_COMPLETED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_COMPLETED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_SUSPENDED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_SUSPENDED));		
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_WITHDRAWN, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_WITHDRAWN));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_EXPIRED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_EXPIRED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_ERRORED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_ERRORED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_ALERTED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_ALERTED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_INFO_REQUESTED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_INFO_REQUESTED));		
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_ASSIGNED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_ASSIGNED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_DELETED, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_DELETED));
		predicadoEstado.put(IWorkflowConstants.TASK_STATE_STALE, new Predicate(columna,Predicate.OP_EQ,IWorkflowConstants.TASK_STATE_STALE));

	}

	/**
	 * Metodo que genera el predicado para realizar la bsuqueda por estado.
	 * @param estado de la instancia.
	 * @return predicado de filtro para la busqueda por estado.
	 * @throws AdapterBPMException
	 */
	
	public static Predicate getPredicadoEstado(String estado, Column columna) throws AdapterBPMException  {
		Predicate predicado = null;	
		
		
		try{
			if(predicadoEstado==null){
				initPredicado(columna);
			}
			
			predicado=predicadoEstado.get(estado);
			
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_ESTADO, e);
		}catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_ESTADO, e);
		}

		return predicado;
	}
	
	/**
	 * Metodo que genera el predicado para realizar la bsuqueda por fecha de creacion.
	 * @param fecha de creacion de la instancia.
	 * @return predicado de filtro para la busqueda por fecha de creacion.
	 * @throws AdapterBPMException
	 */
	public static Predicate getPredicadoFechaCreacion(String fecha) throws AdapterBPMException{
		Predicate predicado = null;
		try {
			if(fecha!=null && !fecha.trim().isEmpty()){
				predicado = new Predicate(IColumnConstants.PROCESS_CREATEDDATE_COLUMN,
						Predicate.OP_ON,
						DateUtil.parseStringToDate(fecha));
			}
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_FH_CREACION, e);
		} catch (ParseException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_FORMATO_FECHA, e);
		}catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_FH_CREACION, e);
		}
		return predicado;
	}
	/**
	 * Metodo que genera el predicado para realizar la bsuqueda por fecha de creacion.
	 * @param usuario creador de la isntancia.
	 * @return predicado de filtro para la busqueda por usuario creador.
	 * @throws AdapterBPMException
	 */
	public static Predicate getPredicadoUsuarioCreador(String usuario, Column columna) throws AdapterBPMException{
		Predicate predicado = null;
		
		try {
			if(usuario!=null && !usuario.trim().isEmpty()){
				predicado = new Predicate(columna,
						Predicate.OP_EQ,usuario);
			}
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_USR_CREADOR, e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_USR_CREADOR, e);
		}
		return predicado;
	}
	/**
	 * Metodo que genera el predicado para realizar la bsuqueda por Id de la instancia.
	 * @param idIstancia del proceso.
	 * @return predicado de filtro para la busqueda por Id de la instancia.
	 * @throws AdapterBPMException
	 */
	public static Predicate getPredicadoIdInstancia(String idIstancia, Column columna) throws AdapterBPMException{
		Predicate predicado = null;
		
		try {
			if(idIstancia!=null && !idIstancia.trim().isEmpty()){
				predicado = new Predicate(columna, 
						Predicate.OP_EQ,idIstancia);
			}
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_ID_INSTANCIA, e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_ID_INSTANCIA, e);
		}
		return predicado;
	}
	
	
	

	/**
	 * Metodo que genera el predicado general con los filtros necesarios para ls busqueda.
	 * @param instanciaVO filtros necesarios para la busqueda.
	 * @return predidcado generado para la busqueda.
	 * @throws AdapterBPMException
	 */
	public static  Predicate getPredicadoInstancias(InstanciaVO instanciaVO) throws AdapterBPMException{

		
		Predicate predicado =null;
		
        try{
			predicado = construirPredicados(predicado, getPredicadoEstado(instanciaVO.getEstado(),IColumnConstants.PROCESS_STATE_COLUMN));
			predicado = construirPredicados(predicado, getPredicadoFechaCreacion(instanciaVO.getFechaCreacion()));
			predicado = construirPredicados(predicado, getPredicadoUsuarioCreador(instanciaVO.getUsuarioCreador(),
						IColumnConstants.PROCESS_CREATOR_COLUMN));
			predicado = construirPredicados(predicado, getPredicadoIdInstancia(instanciaVO.getIdInstancia(),
						IColumnConstants.PROCESS_ID_COLUMN));
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_CONSULTA_INSTANCIA, e);
		}catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_CONSULTA_INSTANCIA, e);
		}
		return predicado;
	}



	/**
	 * Metodo que genera el predicado general con los filtros necesarios para ls busqueda.
	 * @param instanciaVO filtros necesarios para la busqueda.
	 * @return predidcado generado para la busqueda.
	 * @throws AdapterBPMException
	 */
	private static Predicate construirPredicados(Predicate predicado1, Predicate predicado2) throws WorkflowException {
		
		Predicate predicado=null;
		
		if(predicado2!=null){
			if(predicado1!=null){
				predicado= new Predicate(predicado1, Predicate.AND, predicado2);
			} else {
				predicado = predicado2;
			}
		} else {
			if(predicado1!=null){
				predicado = predicado1;
			}
		}
		return predicado;
	}
	
	/**
	 * Metodo que genera el predicado de tareas.
	 * @param tareaVO objeto que contiene las propiedades de tarea.
	 * @param idInstancia identificador de la instancias del proceso.
	 * @return predicado de tareas.
	 * @throws AdapterBPMException
	 */
	public static Predicate getPredicadoTareas(TareaVO tareaVO, String idInstancia)throws AdapterBPMException {
		Predicate predicado=null;
		try{
			predicado = construirPredicados(predicado, getPredicadoEstado(tareaVO.getEstado(), TableConstants.WFTASK_STATE_COLUMN));
			predicado = construirPredicados(predicado, getPredicadoUsuarioCreador(tareaVO.getUsuarioCreador(),
						TableConstants.WFTASK_CREATOR_COLUMN));
			predicado = construirPredicados(predicado, getPredicadoIdInstancia(idInstancia,
					TableConstants.WFTASK_INSTANCEID_COLUMN));
		} catch (WorkflowException e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_TAREA, e);
		}catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PREDICADO_TAREA, e);	
		}
		return predicado;
	}
	
	
}
