package mx.gob.imss.cit.gf.util;

import java.util.Map;
import java.util.Map.Entry;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;

/**
 * Clase que contiene metodos para validar si un
 * objeto es nulo o vacio
 * @author Admin
 *
 */
public final class ValidatorUtil {
	
	/**
	 * Separador
	 */
	private static final String SEPARADOR = ":";
	/**
	 * cadena vacia
	 */

	private static final String CADENA_VACIA = "";
	/**
	 * codigo error formato
	 */
	private static final int ERROR_FORMATO_CODIGO = 102;
	/**
	 * mensaje error formato
	 */
	private static final String ERROR_FORMATO_MSG = " No coincide con el formato requerido";

	/**
	 * codigo error formato
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;
	/**
	 * mensaje error formato
	 */
	private static final String ERROR_OBLIGATORIO_MSG = " Es requerido";

	
	/**
	 * COntructor privado
	 */
	private ValidatorUtil(){
		
	}
	/**
	 * Metodo que regresa True si algun objeto recibido
	 * como parametro es nulo
	 * @param objects
	 * @return Boolean
	 */
	public static Boolean isAnyNull(Object... objects){
		Boolean flag = false;		
		for (Object object : objects) {
			if(object == null){
				flag = true;						
			}
		}		
		return flag;
	}
	
	
	/**
	 * Metodo que regresa True si algun objeto recibido
	 * como parametro es nulo
	 * @param objects
	 * @return Boolean
	 */
	public static Boolean isAnyEmpty(Object... objects){
		Boolean flag = false;		
		for (Object object : objects) {
			if(object == null || CADENA_VACIA.equals(object)){
				flag = true;						
			}
		}		
		return flag;
	}
	

	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>
	 *<br>M�todo que valida que el atributo recibido tenga el formato que indica la
	 *<br>  expresion regular
	 *<br>
	 *<br>
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> PARAMETROS_INCORRECTOS. En caso de que el par�metro de entrada no coincida con el formato
	 *<br>  de la expresion regular introducida.
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> Validar que el par�metro recibido coincida con el formato
	 *<br>  de la expresion regular introducida
	 *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1. Si el atributo No coincide con el formato de la expresion regular recibida
	 *<br>  	1.1 Crear la excepci�n con los atributos: c�digo de la excepci�n = 102, el par�metro de entrada.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.  En caso de que el atributo No coincide con el formato de la expresion regular recibida.
	 *<br>
	 *<br>*********************************************************************/
	public static void validateFormato(String atributo,String valor, String expRegular)
	  throws GestorFlujosException{
		validateFormato(atributo,valor, expRegular, null);
	}
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>
	 *<br>M�todo que valida que el atributo recibido tenga el formato que indica la
	 *<br>  expresion regular, si el atributo viene vacio, no reliza la validacion
	 *<br>	correspondiente.
	 *<br>
	 *<br>
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> PARAMETROS_INCORRECTOS. En caso de que el par�metro de entrada no coincida con el formato
	 *<br>  de la expresion regular introducida.
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> Validar que el par�metro recibido coincida con el formato
	 *<br>  de la expresion regular introducida
	 *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1. Si el atributo No coincide con el formato de la expresion regular recibida
	 *<br>  	1.1 Crear la excepci�n con los atributos: c�digo de la excepci�n = 102, el par�metro de entrada.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.  En caso de que el atributo No coincide con el formato de la expresion regular recibida.
	 *<br>
	 *<br>*********************************************************************/
	
	public static void validateFormatoNoObligatorio(String atributo,String valor, String expRegular)
			throws GestorFlujosException{
		if(!ValidatorUtil.isAnyEmpty(valor)){
			validateFormato(atributo,valor, expRegular, null);
		}

	}

	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>
	 *<br>M�todo que valida que el atributo recibido tenga el formato que indica la
	 *<br>  expresion regular o la expresi�n regular 2
	 *<br>
	 *<br>
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> PARAMETROS_INCORRECTOS. En caso de que el par�metro de entrada no coincida con el formato
	 *<br>  de la expresion regular introducida.
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> Validar que el par�metro recibido coincida con el formato
	 *<br>  de la expresion regular introducida
	 *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1. Si la expresi�n regular 2 es diferente de vacia
	 *<br>    1.1 Valida si el atributo No coincide con el formato de la expresion regular recibida
	 *<br>    1.2 Crear la excepci�n con los atributos: c�digo de la excepci�n = 102, el par�metro de entrada.
	 *<br>  2. De lo contrario
	 *<br>    2.2 Valida si el atributo No coincide con el formato de alguna de las dos expresiones regulares recibidas
	 *<br>    2.3 Crear la excepci�n con los atributos: c�digo de la excepci�n = 102, el par�metro de entrada.
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.  En caso de que el atributo No coincide con el formato de la expresion regular recibida.
	 *<br>
	 *<br>*********************************************************************/
	public static void validateFormato(String atributo,String valor, String expRegular, String expRegular2)
	  throws GestorFlujosException{
		if(ValidatorUtil.isAnyEmpty(expRegular2)){
			if( !valor.matches(expRegular) ) {
				throw new GestorFlujosException(ERROR_FORMATO_CODIGO,generarMensajeError(atributo, valor));
			}		
		}
		else{
			if(!valor.matches(expRegular) && !atributo.matches(expRegular2) ){
				throw new GestorFlujosException(ERROR_FORMATO_CODIGO,generarMensajeError(atributo, valor));
			}
		}
	}
	/**
	 * @param atributo
	 * @param nombreAtributo
	 * @return
	 */
	private static String generarMensajeError(String atributo,
			String valor) {
		return atributo+SEPARADOR+valor+ERROR_FORMATO_MSG;
	}
	
	/**
	 *<br>*************************** DESCRIPCION *****************************
	 *<br>
	 *<br>M�todo que valida que los par�metros del mapa recibido no sean nulos o vac�os
	 *<br> 
	 *<br>  Detalle del mapa recibido:
	 *<br>  Map<String, Objeto>
	 *<br>  La llave del mapa (String) ser� el nombre del atributo.
	 *<br>  Los elementos (Objecto) son los valores a validar.
	 *<br>
	 *<br>
	 *<br>************************* LISTA DE ERRORES **************************
	 *<br>
	 *<br> PARAMETROS_INEXISTENTES. En caso de que el prerrequisito reciba par�metros inv�lidos o nulos  con c�digo  de rechazo 101
	 *<br>
	 *<br>************************** DISENO TECNICO ***************************
	 *<br>
	 *<br> Validaciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> Validar que la lista de par�metros PARAMETROS recibidos  no sean nulos o vac�os.
	 *<br>
	 *<br>Acciones
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1. Recorrer el mapa recibido como par�metro 'PARAMETROS' y verifica que no sean nulos o vacios los par�metros
	 *<br> 
	 *<br>         Iterar PARAMETROS{
	 *<br>               Que cada par�metro recibido de la lista no sea nulo o vac�o.
	 *<br>          }
	 *<br>
	 *<br>Control de Errores
	 *<br>---------------------------------------------------------------------
	 *<br>
	 *<br> 1.  En caso de que se reciban par�metros nulos o vac�os se lanza una excepci�n indicando el atributo vacio:
	 *<br> 
	 *<br>       Iterar PARAMETROS{
	 *<br>            Si (PARAMETRO = NULL){
	 *<br>                      lanzar excepci�n PARAMETROS_INEXISTENTES: 101
	 *<br>            }
	 *<br>            Si  (PARAMETRO es tipo String && PARAMETRO = ""  /cadenavac�a){
	 *<br>                      lanzar excepci�n PARAMETROS_INEXISTENTES: 101
	 *<br>            }
	 *<br>         }
	 *<br>
	 *<br>*********************************************************************/
	public static void validateParametros(Map<String, Object> parametros)
	  throws GestorFlujosException{
		for (Entry<String, Object> entry : parametros.entrySet()) {
		
			if (ValidatorUtil.isAnyNull(entry.getValue()) || ValidatorUtil.isAnyEmpty(entry.getValue())) {				
				throw new GestorFlujosException(ERROR_OBLIGATORIO_CODIGO,entry.getKey()+ERROR_OBLIGATORIO_MSG);
			}
		}
	}
	
}
