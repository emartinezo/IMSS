package  mx.gob.imss.cit.gf.constant;

/**
 * Clase que contiene las diversos expresiones regulares
 * 
 * @author admin
 * @version 1.0
 */
public final class ValidacionesConstants {

	
	/**
	 * Constructor privado
	 */
	private ValidacionesConstants(){
		
	}

	/**
	 * Contante que revisa por cadenas de naturaleza alfabetica, sin numeros.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_ALFABETICO + "{X}$"
	 */
	public static final String ER_ALFABETICO = "^[a-zA-Z]";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_ALFABETICO_ESPACIO_LONGX + "{X}$".
	 */
	public static final String ER_ALFABETICO_ESPACIO_LONGX = "^[a-zA-Z\\ ]";
	/**
	 * Constante que revisa que la cadena solo contenga letras mayusculas.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_ALFABETICO_MAYUS + "{X}$".
	 */
	public static final String ER_ALFABETICO_MAYUS = "^[A-Z]";
	/**
	 * Constante que revisa que la cadena solo contenga letras mayusculas y sea de longitud 3.

	 */
	public static final String ER_ALFABETICO_MAYUS3 = "[A-Z]{3}";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO = "^[a-zA-Z0-9]+$";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios hasta 20 caracteres.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVAR20 = "^[a-zA-Z0-9\\ ]{1,20}$";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios hasta 30 caracteres.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVAR30 = "^[a-zA-Z0-9\\ ]{1,30}$";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios hasta 40 caracteres.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVAR40 = "^[a-zA-Z0-9\\ ]{1,40}$";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios hasta 30 caracteres.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVAR5 = "^[a-zA-Z0-9\\ ]{1,5}$";
	/**
	 * Constante que revisa por cadenas alfanumericos hasta 50 caracteres.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVAR50 = "^[a-zA-Z0-9\\ ]{1,50}$";
	/**
	 * Constante que revisa por cadenas alfanumericos hasta 30 caracteres.
	 */
	public static final String ER_ALFANUMERICO_GUION_BAJO_LONGVAR30 = "^[a-zA-Z0-9_]{1,30}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG1 = "^[a-zA-Z0-9]{1}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG10 = "^[a-zA-Z0-9]{10}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG11 = "^[a-zA-Z0-9]{11}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG16 = "^[a-zA-Z0-9]{16}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG2 = "^[a-zA-Z0-9]{2}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG3 = "^[a-zA-Z0-9]{3}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONG30 = "^[a-zA-Z0-9]{1,30}$";
	/**
	 * Constante que revisa por cadenas alfanumericos a 18 posiciones.
	 */
	public static final String ER_ALFANUMERICO_LONGVAR18 = "^[a-zA-Z0-9]{1,18}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONGVAR2 = "^[a-zA-Z0-9]{1,2}$";
	/**
	 * Constante que revisa por cadenas alfanumericos de 1 a 20 caracteres.
	 */
	public static final String ER_ALFANUMERICO_LONGVAR20 = "^[a-zA-Z0-9]{1,20}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 */
	public static final String ER_ALFANUMERICO_LONGVAR3 = "^[a-zA-Z0-9]{1,3}$";
	/**
	 * Constante que revisa por cadenas alfanumericos.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_ALFANUMERICO_LONGVARX + "{X,Y}$"
	 */
	public static final String ER_ALFANUMERICO_LONGVARX = "^[a-zA-Z0-9]";
	/**
	 * Constante que revisa por cadena alfanumerica para campo nss o curp.
	 */
	public static final String ER_ALFANUMERICO_NSS_CURP = "^[a-zA-Z0-9]{11,18}$";
	/**
	 * Constante que revisa que la cadena contenga numeros y y letras y sea de 1 a 16 posiciones.
	 */
	public static final String ER_ALFANUMERICO1A16 = "^[a-zA-Z0-9]{1,16}$";
	/**
	 * Constante que revisa que la cadena contenga numeros y letras y sea de 1 a 3 posiciones.
	 */
	public static final String ER_ALFANUMERICO1A3 = "^[a-zA-Z0-9]{1,3}$";
	/**
	 * Constante que revisa que la cadena contenga numeros y letras y sea de 1 a 50 posiciones.
	 */
	public static final String ER_ALFANUMERICO1A50 = "^[a-zA-Z0-9[ x0Bf ]-]{1,50}$";
	/**
	 * Constante que revisa que la cadena contenga numeros y letras y sea de 1 a 50 posiciones.
	 */
	public static final String ER_ALFANUMERICO1A60 = "^[a-zA-Z0-9[ x0Bf ]-]{1,60}$";
	/**
	 * Constante que revisa que la cadena contenga numeros, letras y comas y sea de 1 a 20 posiciones.
	 */
	public static final String ER_ALFANUMERICO_CON_COMA_1A20 = "^[a-zA-Z0-9,]{1,20}$";
	/**
	 * Constante que revisa que la cadena contenga numeros, letras , y tenga(!*.) y sea de 1 a 150 posiciones.
	 */
	public static final String ER_ALFANUMERICO_CON_ESPECIALES_1A150 = "^[a-zA-Z0-9/!*.]{1,150}$";
	
	/**
	 * Consntante que revisa que la cadena contenga numeros, letras y punto 
	 */
	public static final String ER_ALFANUMERICO_CON_ESPECIALES_1A50 = "^[a-zA-Z0-9/.]{1,50}$";
	
	/**
	 * Constante que revisa que la cadena contenga numeros, letras , y guion y sea de 1 a 50 posiciones.
	 */
	public static final String ER_ALFANUMERICO_CON_GUION_1A50 = "^[0-9[ x0Bf ]-]{1,50}$";
	
	/**
	 * Constante que encuentra los caracteres especiales.
	 */
	public static final String ER_CARACTERES_ESPECIALES = "[^a-zA-Z0-9\\ ]";
	/**
	 * Indicador del tipo de telefono
	 */
	public static final String ER_CERO_O_UNO = "^[01]$";

	public static final String ER_CVE_ICEFA = "^[a-zA-Z0-9]{3}$";
	/**
     *Constante que define la expresion regular para decimales  de 13 enteros 2 decimales
     */
	public static final String ER_DECIMAL13E_2D = "^[0-9]{1,13}+(\\.{0,1}[0-9]{0,2})$";
	/**
     *Constante que define la expresion regular para decimales  de 9 enteros 6 decimales
     */
	public static final String ER_DECIMAL9E_6D = "^[0-9]{1,9}+(\\.{0,1}[0-9]{0,6})$";

	/**
	 * Constante que define la expresi�n regular para Fecha de 8 d�gitos
	 */
	public static final String ER_FECHA = "[0-9]{8}";

	/**
	 * Constante que define la expresion regular para caracteres no alfabeticos
	 * Nota: esta constante se utliza con el metodo String.replaceAll, por lo que
	 *  no se le debe agregar los caracteres de inicio y final (^$)
	 */
	public static final String ER_NO_ALFABETICO = "[^a-zA-Z]";

	/**
	 * Constante que define la expresi�n regular para INDICADOR TIPO TELEFONO.
	 */
	public static final String ER_NUMERICO_LONG3 = "[0-9]{3}";
	/**
	 * Constante que valida que los saldos sean de 1 a 9 posiciones
	 */
	public static final String ER_NUMERICO_LONG9 = "^[0-9]{1,9}\\.?[0-9]{0,6}?$";
	/**
	 * Contante que revisa por cadenas de naturaleza numerica.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_NUMERICO_LONGX + "{X}$"
	 */
	public static final String ER_NUMERICO_LONGVAR10 = "^[0-9]{1,10}$";
	/**
	 * Constante que define la expresi�n regular para NUMERO TELEFONICO.
	 */
	public static final String ER_NUMERICO_LONGVAR16 = "^[0-9]{1,16}$";
	/**
	 * Constante que define la expresi�n regular para NUMERO 3 posiciones.
	 */
	public static final String ER_NUMERICO_LONGVAR3 = "^[0-9]{1,3}$";
	/**
	 * Constante que define la expresi�n regular para NUMERO TELEFONICO.
	 */
	public static final String ER_NUMERICO_LONGVAR8 = "^[0-9]{1,8}$";
	/**
	 * Contante que revisa por cadenas de naturaleza numerica.
	 * Adjuntar el largo de la cadena as� ValidacionesConstant.ER_NUMERICO_LONGX + "{X}$"
	 */
	public static final String ER_NUMERICO_LONGX = "^[0-9]";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 16 posiciones.
	 */
	public static final String ER_NUMERICO16 = "[0-9]{16}";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 posicion.
	 */
	public static final String ER_NUMERICO1 = "^[0-9]{1}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 2 posiciones.
	 */
	public static final String ER_NUMERICO2 = "^[0-9]{1,2}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 3 posiciones.
	 */
	public static final String ER_NUMERICO3 = "^[0-9]{1,3}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 4 posiciones.
	 */
	public static final String ER_NUMERICO4 = "^[0-9]{1,4}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 4 posiciones.
	 */
	public static final String ER_NUMERICO6 = "^[0-9]{6}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 7 posiciones.
	 */
	public static final String ER_NUMERICO7 = "^[0-9]{1,7}$";
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 8 posiciones.
	 */
	public static final String ER_NUMERICO8 = "^[0-9]{1,8}$";	
	/**
	 * Constante que revisa que la cadena solo contenga numeros y sea de 1 a 9 posiciones.
	 */
	public static final String ER_NUMERICO9 = "^[0-9]{1,9}$";
	/**
	 * Constante que define la expresi�n regular para N�meros Positivos
	 */
	public static final String ER_NUMERO_POSITIVO = "^[0-9]{1}";

	/**
	 * Constante que define el formato de fecha yyyyMMdd.
	 */
	public static final String FORMATO_FECHA = "yyyyMMdd";
	/**
	 * Constante que define el formato de fecha dd/MM/yy.
	 */
	public static final String FORMATO_FECHA_CORTO = "dd/MM/yy";
	/**
	 * Constante que define el formato de fecha dd/MM/yyyy.
	 */
	public static final String FORMATO_FECHA_LARGO = "dd/MM/yyyy";

	/**
	 * Constante que define la expresion regular de un alfabetico de hasta 40p
	 */
	public static final String ER_ALFABETICO_40 = "^[a-zA-Z]{1,40}$";	
	/**
	 * Constante que define la expresi�n regular para Fecha de 8 d�gitos
	 */
	public static final String ER_FECHA_YYYYMMDD = "^(\\d{4})(\\d{2})(\\d{2})";
	/**
	 * Constante que define la expresi�n regular para Fecha de 10 d�gitos
	 */
	public static final String ER_FECHA_DDMMYYYY = "^(\\d{2})/(\\d{2})/(\\d{4})";

	public static final String ER_ALFANUMERICO_ESPACIO_LONGVARGUION12 = "^[a-zA-Z0-9|\\s|\\_|\\.|\\-]{1,12}$";
	/**
	 * Constante que revisa por cadenas alfanumericos con espacios hasta 13
	 * caracteres y guiones bajos.
	 */
	public static final String ER_ALFANUMERICO_ESPACIO_LONGVARGUION13 = "^[a-zA-Z0-9|\\s|\\_|\\.|\\-]{1,13}$";	
	/**
	 * Constante que revisa por cadenas numerica, la validez del a�o a 4 posiciones
	 */
	public static final String ER_ANO = "^(\\d{4})$";
	/**
	 * Constante que revisa por cadenas numerica, la validez del mes a 2 posiciones
	 */
	public static final String ER_MES = "^(\\d{2})$";
	/**
	 * Constante que revisa por cadenas numerica, de ceros.
	 */
	public static final String ER_CEROS = "^[0]";

}