package mx.gob.imss.cit.gf.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import oracle.bpel.services.bpm.common.IBPMContext;
import oracle.bpel.services.workflow.verification.IWorkflowContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase singleton que permite  tener un cache de sesiones
 * a traves de usuario de la sesion
 */
public final class ContextUserCache {
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ContextUserCache.class);

	/**
	 * instancia unica
	 */
	private static ContextUserCache contextCache;
	
	/**
	 * Mapa que contiene las llaves de usuario el valor los contextos
	 */
	private static Map<String, IWorkflowContext> cache= new HashMap<String, IWorkflowContext>();

	/**
	 * COnstructor privado para hacerla singleton
	 */
	private ContextUserCache() {
		LOG.info("ContextCache", "Constructed");
	}

	/**
	 * Regresa la instancia
	 * 
	 * @return 
	 */
	public static synchronized ContextUserCache getContextCache() {
		LOG.info("obteniendo el cache");
		if (contextCache == null) {
			contextCache = new ContextUserCache();
		}
		return contextCache;
	}

	/**
	 * añade un nuevo IWorkflowContext al cache
	 * 
	 * @param  user
	 * @param ctx
	 * */
	public static synchronized void put(String user, IBPMContext ctx) {
		LOG.info("agregando " + user + " ctx=" + ctx);
		cache.put(user, ctx);
	}

	/**
	 * Obtiene the <code>IWorkflowContext</code> a traves del user
	 * 
	 * @param 
	 * @return 
	 * @throws AdapterBPMException 
	 */
	public static synchronized IWorkflowContext get(String user) throws AdapterBPMException {
		IWorkflowContext context=null;
		LOG.info("obteniendo contexto "+ user);

		if (cache.containsKey(user)) {
			LOG.info(" contexto encontrado "+ user);
			context= cache.get(user);
		}else{
			 throw new AdapterBPMException("Error al obtener el contexto del cache");
		}
		
		return context;
	}

	/**
	 * Remueve del cache el contexto
	 * @param user
	 *           
	 */
	public static synchronized void remove(String user) {
		LOG.info("ContextCache", "Removiendo token " + user);
		if (user != null && cache.containsKey(user)) {
				cache.remove(user);			
		}
	}

}