package mx.gob.imss.cit.gf.adapter.util;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;

import mx.gob.imss.cit.gf.adapter.constant.AdapterBPMConstants;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMCodeExceptionEnum;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMExceptionBuilder;
import mx.gob.imss.cit.gf.util.DateUtil;
import mx.gob.imss.cit.gf.vo.UsuarioVO;
import oracle.bpel.services.workflow.task.model.Task;
import oracle.soa.management.facade.ComponentInstance;
import oracle.soa.management.facade.CompositeInstance;
import oracle.soa.management.facade.Locator;
import oracle.soa.management.facade.LocatorFactory;
import oracle.soa.management.util.ComponentInstanceFilter;
import oracle.soa.management.util.CompositeInstanceFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de utilerias de modelo
 * @author admin
 *
 */
public final class BPMUtil {
	
	/**
	 * Contructor privado
	 */
	private BPMUtil(){
		
	}
	

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BPMUtil.class);

	
	
	/**
	 * Metodo usado en updateTarea sirve para validar los valores Calendar NO obligarios que se pueden actualizar
	 * @param valorActualizar	Contiene el String con formaro Fecha el cual se va a Actualizar
	 * @param valorActual 		Contiene el Calendar Actual
	 * @return valor	 		Regresa valorActualizar si no es nulo o esta vacio, de lo contrario devuelve  valorActual
	 */		
	public static   Calendar updateFechaNoObligatorioStringCalendar(String valorActualizar,Calendar valorActual){
		Calendar calendar=valorActual;
		if (valorActualizar!=null&&!valorActualizar.isEmpty()){
			try {
				calendar=DateUtil.parseStringToCalendar(valorActualizar);							
			} catch (Exception e) {
				LOG.error(e.getMessage());				
			}
			
		}
		return calendar;
		
	}
	
	/**
	 * Metodo usado en updateTarea sirve para validar los valores Integer NO obligarios que se pueden actualizar
	 * @param valorActualizar	Contiene el Integer a Actualizar
	 * @param valorActual 		Contiene el int Actual
	 * @return valor	 		Regresa valorActualizar si no es nulo o esta vacio, de lo contrario devuelve  valorActual
	 */		
	public static int updateValorNoObligatorioInteger(Integer valorActualizar,int valorActual){
		
		if (valorActualizar!=null&&valorActualizar>0){
			return valorActualizar;
		}else{
			return valorActual;
		}
		
	}
	
	/**
	 * Metodo usado en updateTarea sirve para validar los valores String NO obligarios que se pueden actualizar
	 * @param valorActualizar	Contiene el String a Actualizar
	 * @param valorActual 		Contiene el String Actual
	 * @return valor	 		Regresa valorActualizar si no es nulo o esta vacio, de lo contrario devuelve  valorActual
	 */	
	public static String updateValorNoObligatorioString(String valorActualizar,String valorActual){
		String valor=valorActual;
		if (valorActualizar!=null&&!valorActualizar.isEmpty()){
			return valorActualizar;
		}
		return valor;		
	}


	/**
	 * Metodo usado en validar que el estatus de una tarea permita la actulizacion
	 * @param Task	Contiene la tarea con el estatus a validar
	 * @return regreso	Regresa 'true' si el estatus es valido
	 */		
	public static  boolean validateStatusUpdate(Task task){
		boolean regreso=false;
		if (PredicadosUtil.TASK_STATE_PLANNED.equals(task.getSystemAttributes().getState()) || PredicadosUtil.TASK_STATE_RUNNING.equals(task.getSystemAttributes().getState())
				||	PredicadosUtil.TASK_STATE_REJECTED.equals(task.getSystemAttributes().getState())	||PredicadosUtil.TASK_STATE_PROGRAMMED.equals(task.getSystemAttributes().getState())
				){
			regreso=true;
		}
		
			return regreso;
		
		
	}	
	
	/**
	 * Extrae todas las instancias que estan asociadas a un proceso.
	 * @param usuarioVO datos del usuario.
	 * @param procesoDN  proceso.
	 * @return listaInstacia regresa los Id de las instancias.
	 * @throws AdapterBPMException
	 */
	public static Map<String, String> findModeloProcesoTipo(UsuarioVO usuarioVO,
			String tipo) throws AdapterBPMException {
		
    	Map<String, String> mapaProcesoDN = new Hashtable<String, String>();
    	ComponentInstanceFilter componentFilter = new ComponentInstanceFilter();
    	List<CompositeInstance> composites = null;
    	Locator locator = getLocator(usuarioVO);
    	try {
    		componentFilter.setEngineType(tipo);	
    		CompositeInstanceFilter filter = new CompositeInstanceFilter();
    		composites = locator.getCompositeInstances(filter);

    		LOG.info("composites...." + composites.size());
    		for (CompositeInstance composite : composites) {
    			List<ComponentInstance> componentInstance = composite.getChildComponentInstances(componentFilter);
    			for (ComponentInstance component : componentInstance) {
    				mapaProcesoDN.put(composite.getCompositeDN() + AdapterBPMConstants.ASTERICO+ AdapterBPMConstants.SEPARATOR+component.getComponentName(), 
    						component.getComponentName());
    			}
    		}
    		
    		LOG.info("mapaProcesoDN.size()...." + mapaProcesoDN.size());
    		if(mapaProcesoDN == null || mapaProcesoDN.size() == AdapterBPMConstants.ZERO) {
    			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_PROCESO_NO_ENCONTRADO);
    		}
		} catch (AdapterBPMException e) {
			throw AdapterBPMExceptionBuilder.build(e.getCode(), e);
		} catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONSULTAR_PROCESO, e);
		} finally {
			locator.close();
		}
		return mapaProcesoDN;
	}
	
	/**
	 * Metodo carga propiedades para generar el servicio cliente.
	 * @param datos las propiedades a cargar.
	 * @param usuario datos del usuario.
	 * @return locator instancia que mantiene la conexion.
	 * @throws Exception
	 */
	public static Locator getLocator(UsuarioVO usuarioVO) throws AdapterBPMException {
		Map<String, String> properties = new Hashtable<String, String>();
		Locator locator = null;
		try{
			properties.put(Context.PROVIDER_URL, PropertiesAdapterUtil.getMessage(AdapterBPMConstants.KEY_URL_SERVIDOR_BPM));
			properties.put(Context.INITIAL_CONTEXT_FACTORY, PropertiesAdapterUtil.getMessage(AdapterBPMConstants.KEY_CONTEXTO_INICIAL_WL));
			properties.put(Context.SECURITY_PRINCIPAL, usuarioVO.getUsuario());
			properties.put(Context.SECURITY_CREDENTIALS, usuarioVO.getPassword());
			locator = LocatorFactory.createLocator(new Hashtable<String, String>(properties));
		}catch (Exception e) {
			throw AdapterBPMExceptionBuilder.build(AdapterBPMCodeExceptionEnum.ERROR_CONEXION_LOCATOR,e);
		}
		return locator;
	}
	

}
