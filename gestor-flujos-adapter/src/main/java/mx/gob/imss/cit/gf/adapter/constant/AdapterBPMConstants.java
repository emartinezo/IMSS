package mx.gob.imss.cit.gf.adapter.constant;

/**
 * Clase de constantes
 * @author ajfuentes
 *
 */
public final class AdapterBPMConstants {

	/**
	 * Contructor privado
	 */
	private AdapterBPMConstants(){
		
	}
	
	/**
	 * atributo con el nombre del archivo de properties
	 */
	public static final String FILE_ADAPTER_CONFIG = "adapter-config";

	/**
	 * Indicador de pagina inicio.
	 */
	public static final int IND_PAGINA_INICIO = 0;	
	
	/**
	 * Indicador de pagina final.
	 */
	public static final int IND_PAGINA_FIN = 200;
		
	/**
	 * Constante para el contexto incial de weblogic.
	 */
	public static final String KEY_CONTEXTO_INICIAL_WL = "CONTEXTO_INICIAL_WL";
	/**
	 * URL del servidor BPM.
	 */
	public static final String KEY_URL_SERVIDOR_BPM = "URL_SERVIDOR_BPM";
	/**
	 * Indicador Start.
	 */
	public static final String IND_START = "START";
	/**
	 * Indicador End.
	 */
	public static final String IND_END = "END";
	/**
	 * Indicador Process.
	 */
	public static final String IND_PROCESS = "PROCESS";
	/**
	 * Indicador Cero(0).
	 */
	public static final Integer ZERO = 0;
	/**
	 * Prioridad Cero(0).
	 */
	public static final Integer PRIORIDAD_ZERO = 0;
	/**
	 * Tipo de elemento (ACTIVITY).
	 */
	public static final String TYPE_ELEMENT_ACTIVITY = "ACTIVITY";
	/**
	 * Indicador EVT.
	 */
	public static final String IND_EVT = "EVT";
	/**
	 * Tarea - Acccion de probacion.
	 */
	public static final String ACTION_APPROVE = "APPROVE";
	/**
	 * Separador.
	 */
	public static final String SEPARATOR = "/";
	/**
	 * Indicador asterisco.
	 */
	public static final String ASTERICO = "*";
	

}
