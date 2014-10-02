/**
 * 
 */
package mx.gob.imss.cit.gf.adapter.util;

/**
 * @author ajfuentes
 *
 */

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
/**
 * clase para serializar
 * @author ajfuentes
 *
 */
public final class SerializerUtil {
	
	/**
	 * COntructor privado
	 */
	private SerializerUtil(){
		
	}
	
	/**
	 * serialize
	 * @param obj
	 * @return
	 * @throws IOException
	 */
    public static byte[] serialize(Serializable obj) throws IOException {
    	return SerializationUtils.serialize(obj);
    }

    /**
     * deserialize
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
    	return SerializationUtils.deserialize(bytes);
    }
}