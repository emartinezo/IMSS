package mx.gob.imss.cit.gf.services.exception;

public enum GestorFlujosServicesCodeExceptionEnum {
	
	  /**
	   * Error desconocido 
	   */
	  GENERIC_UNKNOWN_ERROR(0),
	  /**
	   * Error en el adapter del bpm
	   */
	  ERROR_ADAPTER_BPM(1),
	  /**
	   * Error en el la validacion de estados de la tarea
	   */	  	  
	  ERROR_STATE_TASK(2);
	 
	  private int id;
	  
	  /**
	   * Constructor interno
	   * 
	   * @param id
	   */
	  private GestorFlujosServicesCodeExceptionEnum (int id){
		  this.id = id;
	  }
	  
	  /**
	   * 
	   * @return the id 
	   */	  
	  public int getId(){
		  return id;
	  }

}
