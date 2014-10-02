package mx.gob.imss.cit.gf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public final class DateUtil {
	/**
	 * Formato de fecha.
	 */
	public static final String FECHA_FORMATO = "dd/MM/yyyy";

	/**
	 * Contructor privado
	 */
	private DateUtil(){
		
	}	
	
	/**
	 * Metodo realiza parse de un String del formato dd/MM/yyyy a Date
	 * @param dateS	Objeto que contiene el valor de la fecha en formato dd/MM/yyyy
	 * @return Date retorna la fecha
	 * @throws ParseException
	 */			
	public static Date parseStringToDate(String dateS) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);
		return sdf.parse(dateS);
	}	
	
	/**
	 * Metodo realiza formato dd/MM/yyyy a un objeto Date
	 * @param date	Objeto al que se le aplicara el formato dd/MM/yyyy
	 * @return sdf.format(date) retorna la fecha con formato
	 * @throws ParseException
	 */	
	public static String formatDateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);		
		return sdf.format(date);
	}

	/**
	 * Metodo que recibe como entrada una fecha(Date) al cual se le aplica un cast a Calendar
	 * @param date	Objeto de tipo Date a transformar en Calendar
	 * @return Calendar
	 * @throws ParseException
	 */	
	public static Calendar parseDateToCalendar(Date date){		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * Metodo que recibe como entrada una fecha tipo String con formato dd/MM/yyyy al cual se le aplica un cast a Calendar
	 * @param date	Objeto de tipo String con formato dd/MM/yyyy a transformar en Calendar
	 * @return Calendar
	 * @throws ParseException
	 */			
	public static Calendar parseStringToCalendar(String date) throws ParseException{		
		Calendar cal = Calendar.getInstance();		
		cal.setTime(parseStringToDate(date));
		return cal;
	}	
}
