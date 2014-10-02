package mx.gob.imss.cit.gf.util;

import java.lang.reflect.Field;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ValidaCatalogo {
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ValidaCatalogo.class);
	
	/**
	 * Constructor privado
	 */
	private ValidaCatalogo(){
		
	}
	
/**
 * Metodo que busca en una clase las variables estaticas de tipo String y las compara con un valor
 * @param clazz Es el class dentri de la cual se buscaran las variables static tipo String
 * @param  value valor con el que se comparan las varibles static String         
 * @return retorna Es el resultado de la busqueda de value dentro de las variables static string
 * */
	public static boolean validaEstado (Class clazz, String value){
		boolean retorna=false;
        for (Field field : clazz.getDeclaredFields()) {
            try {
            	if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) && String.class== field.getType() && field.get(clazz).toString().equals(value)){
					retorna=true;
					break;
				}		
			} catch (IllegalArgumentException e) {
				LOG.error(e.getMessage(),e);
			} catch (IllegalAccessException e) {
				LOG.error(e.getMessage(),e);
			}
        }
		
        return retorna;
	}
	
	
}
