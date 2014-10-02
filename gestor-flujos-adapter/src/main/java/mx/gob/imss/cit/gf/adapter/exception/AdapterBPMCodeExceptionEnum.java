package mx.gob.imss.cit.gf.adapter.exception;

/**
 * Clase con los codigos de errores para las excepciones
 * en el adapter
 * @author Admin
 *
 */
public enum AdapterBPMCodeExceptionEnum {

	/**
	 * Error desconocido
	 */
	GENERIC_UNKNOWN_ERROR(0),

	/**
	 * Error al obtener la sesion
	 */
	ERROR_OBTENER_SESION(1),

	/**
	 * Error al cerrar la sesion
	 */
	ERROR_CERRAR_SESION_BPM_CONTEXT(2),

	/**
	 * Error al consultar la instancia de proceso
	 */
	ERROR_CONSULTAR_INSTANCIA_PROCESO(3),

	/**
	 * Error al crear la instancia de proceso
	 */
	ERROR_CREAR_INSTANCIA_PROCESO(4),

	/**
	 * Error al crear el usuario en el sistema
	 */
	ERROR_CREAR_METADATOS_USUARIO(5),

	/**
	 * Error al borrar el usuario del sistema
	 */
	ERROR_BORRAR_METADATOS_USUARIO(6),

	/**
	 * Error al consultar una tarea de un proceso.
	 */
	ERROR_CONUSULTAR_TAREA(7),
	/**
	 * Error en crear predicado tarea.
	 */
	ERROR_PREDICADO_TAREA(8),
	/**
	 * Error en crear el predicado del estado.
	 */
	ERROR_PREDICADO_ESTADO(9),
	/**
	 * Error en generar el predicado de la fecha de creacion.
	 */
	ERROR_PREDICADO_FH_CREACION(10),
	/**
	 * El formato de la fecha creacion no es correcta.
	 */
	ERROR_FORMATO_FECHA(11),
	/**
	 * Error en generar en crear el predicado de usuario creador.
	 */
	ERROR_PREDICADO_USR_CREADOR(12),
	/**
	 * Error en generar el predicado de Id Instancia.
	 */
	ERROR_PREDICADO_ID_INSTANCIA(13),
	/**
	 * Error en el predicado para la consulta de instancias.
	 */
	ERROR_PREDICADO_CONSULTA_INSTANCIA(14),
	/**
	 * Error al cambiar el estado de las tareas
	 */
	ERROR_CHANGE_STATE_TASK(15),
	/**
	 * Error en el caso de que al solicitar cancelar una instancia de proceso
	 * no se encuentre por el id recibido como parametro de entrada
	 */
	ERROR_INSTANCIA_NO_ENCONTRADA(16),
	/**
	 * Error que indica que no se pudo cancelar una instancia
	 */
	ERROR_CANCELAR_INSTANCIA_PROCESO(17),
	/**
	 * Error al cerrar la sesion en el work flow context
	 */
	ERROR_CERRAR_SESION_WORK_FLOW_CONTEXT(18),
	/**
	 * Error al cambiar el estado de las tareas
	 */
	ERROR_TASK_NOFOUND(19),
	/**
	 * No Hay Resultados.
	 */
	ERROR_NO_RESULTADOS(20),
	/**
	 * Error recuperar responsables instancias porceso
	 */
	ERROR_RECUPERAR_RESPONSABLES_INSTANCIAS_PROCESO(21),

	/**
	 * Error recuperar los participantes a asignarse a instancias
	 */
	ERROR_RECUPERAR_PARTICIPANTES_ASIGNABLES(22),
	/**
	 * Error recuperar los participantes a asignarse a instancias
	 */
	ERROR_RECUPERAR_ARGUMENTO_ACTIVIDAD(23),
	/**
	 * Error en el caso de actualizar tarea y el estatus de la instancia no lo permita
	 */
	ERROR_INSTANCIA_TASK_ESTADO_NO_VALIDO(24),
	/**
	 * Error al actualizar la tarea
	 */
	ERROR_UPDATE_TASK(25),
	/**
	 * Error en consultar el modelo de proceso por instancias
	 */
	ERROR_CONSULTAR_MODELO_PROCESO_INSTANCIA(26),

	/**
	 * Error en consultar el modelo de proceso por instancias
	 */
	ERROR_EJECUTAR_OPERACION(27),
	/**
	 * Error no hay tareas existentes.
	 */
	ERROR_TAREAS_NO_ENCONTRADAS(28),
	/**
	 * Error recuperar responsables instancias proceso
	 */
	ERROR_UPDATE_INSTANCE(29),

	/**
	 * Error al crear el usuario en el sistema
	 */
	ERROR_CONSULTAR_METADATOS_USUARIO(30),
	/**
	 * Error al no encontrar el modelo.
	 */
	ERROR_MODELO_NO_ENCONTRADO(31),
	/**
	 * Error los roles de para esta instancia no fueron encontrados
	 */
	ERROR_ROLES_INSTANCIA_NO_ENCONTRADOS(32),
	/**
	 * Error al ejcutar la actividad(tarea).
	 */
	ERROR_INSTANCIA_TAREA_NO_EJECUTADA(33),
	/**
	 * No se pudo encontrar la tarea a ejecutar. 
	 */
	ERROR_ENCONTRAR_INSTANCIA_TAREA(34),
	/**
	 * Error para cuando la lista de metadatos de usuarios esta vacia
	 */
	ERROR_LISTA_METADATOS_VACIA(35),
	/**
	 * No se encontraron argumentos de entrada en la tarea
	 */
	ERROR_TASK_ARGUMENTS_NOFOUND(36),
	/**
	 * No se encontro ningun modelo con el identificador. 
	 */
	ERROR_ENCONTRAR_MODELOS_CON_INDENTIFICADOR(37),
	/**
	 * No se encontro ningun modelo con el identificador. 
	 */
	ERROR_MODELOS_NO_ENCONTRADOS(38),
	/** No se encontraron argumentos de entrada en la tarea
	 */
	ERROR_TASK_ARGUMENT_NOFOUND(39),
	/**
	 * Error para cuando la tarea se quiere ejecutar y ya ha sido
	 * ejecutada
	 */
	ERROR_INSTANCIA_TAREA_YA_HA_SIDO_EJECUTADA(40),
	/**
	 * Error para cuando la consulta de los roles de una instancia falla
	 * 
	 */
	ERROR_CONSULTAR_ROLES_DE_INSTANCIA_DE_PROCESO(41),
	/**
	 * Error para cuando no se encuentra el rol de una instancia de tarea
	 */	
	ERROR_ROL_DE_INSTANCIA_DE_TAREA_NO_ENCONTRADO(42),
	/**
	 * Error para cuando falla la operacion de consultar el rol de actividad
	 * inicial de una instancia de proceso
	 */
	ERROR_CONSULTAR_ROL_DE_ACTIVIDAD_INICIAL(43),
	/**
	 * Error para cuando falla la operacion de consultar el argumento de una
	 * actividad
	 */
	ERROR_CONSULTAR_ARGUMENTO_DE_ACTIVIDAD(44),
	/**
	 * Error al consultar actividades remitentes
	 */
	ERROR_CONSULTAR_ACTIVIDADES_REMITENTES(45),
	/** 
	 * No se encontraron procesos.
	 */
	ERROR_PROCESO_NO_ENCONTRADO(46),
	/** 
	 * Error al realizar la busuqeda de procesos.
	 */
	ERROR_CONSULTAR_PROCESO(47),
	/** 
	 * Error al realizar la conexion del locator.
	 */
	ERROR_CONEXION_LOCATOR(48),
	/** 
	 * Error al obtner instancias por proceso.
	 */
	ERROR_CONSULTAR_INSTANCIAS_PROCESO(49);

	private int id;

	/**
	 * Constructor interno
	 * 
	 * @param id
	 */
	private AdapterBPMCodeExceptionEnum(int id) {

		this.id = id;
	}

	/**
	 * 
	 * @return the id
	 */
	public int getId() {

		return id;
	}
}
