/**
 * 
 */
package mx.gob.imss.cit.gf.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.constant.NombreParametroConstants;
import mx.gob.imss.cit.gf.constant.ValidacionesConstants;
import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IInstanciaService;
import mx.gob.imss.cit.gf.services.ITareaService;
import mx.gob.imss.cit.gf.services.constans.ServiceBPMConstants;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesExceptionBuilder;
import mx.gob.imss.cit.gf.util.ValidatorUtil;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementacion de metodos referentes al manejo a las
 * instancias
 * @author ahernandezd
 *
 */
@Remote(IInstanciaService.class)
@Stateless
public class InstanciaService implements IInstanciaService {


	private static final int CERO = 0;

	/**
	 * Interfaz de AdapterBPM
	 */
	@EJB
	private IAdapterBPM adapterBPM;

	/**
	 * aributo de servicio
	 */
	@EJB
	private ITareaService tareaService;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(InstanciaService.class);	

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Consultar detalle de instancia de procesos.
	 *Descripcion : Metodo que realiza la consulta de isntancias.
	 **************************** PARMETROS ********************************
	 * @param instanciaVO
	 * 			Contiene los criterios de busqueda, no obligatorios:
	 * 			{estado(String),fechaCreacion(String,DD-MM-YYYY HH:MM:SS),
	 * 			idInstancia(String),usuarioCreador(String)}
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @return listaInstanciaVO
	 * 			Contiene lista de instancias.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public List<InstanciaVO> findInstanciaDetalle(InstanciaVO instanciaVO,
			UsuarioVO usuarioVO) throws GestorFlujosServicesException {

		List<InstanciaVO> listaInstanciaVO = null;
		try {


			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.INSTANCIA_ID_INSTANCIA,instanciaVO.getIdInstancia(), ValidacionesConstants.ER_ALFANUMERICO_CON_GUION_1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.INSTANCIA_USR_CREADOR,instanciaVO.getUsuarioCreador(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.INSTANCIA_ESTADO,instanciaVO.getEstado(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.INSTANCIA_FH_CREACION,instanciaVO.getFechaCreacion(), ValidacionesConstants.ER_FECHA_DDMMYYYY);



			adapterBPM.getSesion(usuarioVO);
			listaInstanciaVO = adapterBPM.findInstanciaDetalle(instanciaVO, usuarioVO);

		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,
					e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return listaInstanciaVO;
	}

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Generar una instancia de proceso
	 *Descripcion : Metodo que genera o crea una instancia del proceso.
	 **************************** PARMETROS ********************************
	 * @param instanciaVO
	 * 			Contiene los criterios de busqueda, no obligatorios:
	 * 			{estado(String),fechaCreacion(String,DD-MM-YYYY HH:MM:SS),
	 * 			idInstancia(String),usuarioCreador(String)}
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public String executeCrearInstanciaProceso(UsuarioVO usuarioVO, String procesoDN)
			throws GestorFlujosServicesException {
		String idInstancia = null;
		try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.INSTANCIA_PROCESO_DN, procesoDN);
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.INSTANCIA_PROCESO_DN,procesoDN, ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A150);

			adapterBPM.getSesion(usuarioVO);
			idInstancia = adapterBPM.executeCrearInstanciaProceso(usuarioVO, procesoDN);

		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,
					e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return idInstancia;
	}

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Iniciar una instancia de proceso
	 *Descripcion : Metodo que genera o crea una instancia del proceso.
	 **************************** PARMETROS ********************************
	 * @param instanciaVO
	 * 			Contiene los criterios de busqueda, no obligatorios:
	 * 			{estado(String),fechaCreacion(String,DD-MM-YYYY HH:MM:SS),
	 * 			idInstancia(String),usuarioCreador(String)}
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public InstanciaVO executeInitInstanciaProceso(UsuarioVO usuarioVO, String procesoDN)
			throws GestorFlujosServicesException {
		String idInstancia = null;
		List<InstanciaVO> instanciaVOs=null;
		try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.INSTANCIA_PROCESO_DN, procesoDN);
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.INSTANCIA_PROCESO_DN,procesoDN, ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A150);

			adapterBPM.getSesion(usuarioVO);
			idInstancia = adapterBPM.executeCrearInstanciaProceso(usuarioVO, procesoDN);
			
			
			InstanciaVO instancia=new InstanciaVO();
			instancia.setIdInstancia(idInstancia);
			instanciaVOs=findInstanciaDetalle(instancia, usuarioVO);

		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,
					e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return instanciaVOs.get(CERO);
	}


	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Consultar instancias de procesos
	 *Descripcion : Metodo  Orquestador que consulta las instancias y tareas de un proceso.
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 			Objeto que contiene los datos de sesion (usario,password).
	 * @param procesoDN
	 * 			Referencia del proceso.
	 * @return listaInstanciasVO
	 * 			Lista de instancias con las tareas asociadas.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public List<InstanciaVO> findInstancias(InstanciaVO instanciaVO, UsuarioVO usuarioVO)
			throws GestorFlujosServicesException {
		List<InstanciaVO> listaInstanciasVO= null;
		try {

			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.INSTANCIA_FH_CREACION, instanciaVO.getFechaCreacion());
			ValidatorUtil.validateParametros(parametros);
			ValidatorUtil.validateFormatoNoObligatorio(NombreParametroConstants.INSTANCIA_FH_CREACION,instanciaVO.getFechaCreacion(), ValidacionesConstants.ER_FECHA_DDMMYYYY);
			
			listaInstanciasVO = findInstanciaDetalle(instanciaVO, usuarioVO);

			for(InstanciaVO instancia:listaInstanciasVO){
				try {
					List<TareaVO> listaTareas =  tareaService.findTareasInstancia(usuarioVO, instancia.getIdInstancia());
					instancia.setListaTareas(listaTareas);
				} catch (GestorFlujosException e) {
					if(e.getCode()==ServiceBPMConstants.COD_LISTA_TAREA_VACIA){
						instancia.setListaTareas(null);
					} else {
						throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
					}
				}
			}
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}
		return listaInstanciasVO;

	}

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Cancelar una instancia de proceso
	 *Descripcion : Metodo que cancela una instancia de proceso en base al
	 * identificador de instancia recibido como parametro de entrada
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 			Objeto que contiene los datos de sesion (usario,password).
	 * @param procesoId
	 * 			Referencia del proceso.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public void executeCancelarInstanciaProceso(UsuarioVO usuarioVO, String procesoId)
			throws GestorFlujosServicesException {
		try{
			
			
			
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.PROCESO_ID, procesoId);
			ValidatorUtil.validateParametros(parametros);
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PROCESO_ID, procesoId, ValidacionesConstants.ER_NUMERICO_LONGVAR16);

			LOG.info("InstanciaService - procesoId: " + procesoId + "- longitud: " + procesoId.length());
			
			adapterBPM.getSesion(usuarioVO);
			adapterBPM.executeCancelarInstanciaProceso(usuarioVO, procesoId);
		}catch(GestorFlujosException e){
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
	}

	/**
	 * Metodo para obtener los responsables de las instancias de proceso.
	 * 
	 * @param idInstanciasProcesos
	 * @param usuarioVO contiene los datos para la sesion.
	 * 
	 * @return List<String>
	 * @throws GestorFlujosServicesException
	 */
	@Override
	public List<String> findResponsablesInstancias(List<String> idInstanciasProcesos, UsuarioVO usuarioVO)
			throws GestorFlujosServicesException {

		List<String> responsables = null;

		try{
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);

			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);

			adapterBPM.getSesion(usuarioVO);
			responsables = adapterBPM.findResponsablesInstancias(idInstanciasProcesos, usuarioVO);
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		} finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}

		return responsables;
	}




	/**
	 * Metodo que permite actualizar la tarea de una Instancia dependiendo de su estado
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return true si fue actualizado correctamente.
	 * @throws GestorFlujosServicesException
	 * @throws AdapterBPMException
	 */
	public boolean updateInstancia(UsuarioVO usuarioVO, InstanciaVO instanciaVO) throws GestorFlujosServicesException{
		boolean flag=false;
		try{
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO);
			parametros.put(NombreParametroConstants.INSTANCIA, instanciaVO);
			ValidatorUtil.validateParametros(parametros);			
			parametros.clear();
			
			parametros.put(NombreParametroConstants.USUARIO, 	usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, 	usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.ID_INSTANCIA, instanciaVO.getIdInstancia());
			parametros.put(NombreParametroConstants.INSTANCIA_ESTADO, instanciaVO.getEstado());
			ValidatorUtil.validateParametros(parametros);
			
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.ID_INSTANCIA,instanciaVO.getIdInstancia(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.INSTANCIA_ESTADO,instanciaVO.getEstado(), ValidacionesConstants.ER_ALFANUMERICO1A50);
	

			adapterBPM.getSesion(usuarioVO);
			flag = adapterBPM.updateInstancia(usuarioVO,instanciaVO );
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return flag;
	}


	/**
	 * Metodo que permite buscar los roles de la instancia del Proceso
	 * @param usuarioVO	Objeto que contiene los datos de sesion.
	 * @param instanciaVO objeto que contiene las propiedades del proceso.
	 * @return List<RolVO> Lista de los Roles
	 * @throws AdapterBPMException
	 */	
	public List<RolVO> findRolesByInstaceProcess(UsuarioVO usuarioVO, InstanciaVO instanciaVO)throws GestorFlujosServicesException{
		
		List<RolVO> roles=null;
		try{
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO);
			parametros.put(NombreParametroConstants.INSTANCIA, instanciaVO);
			ValidatorUtil.validateParametros(parametros);			
			parametros.clear();
			
			parametros.put(NombreParametroConstants.USUARIO, 	usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, 	usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.ID_INSTANCIA, instanciaVO.getIdInstancia());
			ValidatorUtil.validateParametros(parametros);
			
			
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.ID_INSTANCIA,instanciaVO.getIdInstancia(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			
			adapterBPM.getSesion(usuarioVO);
			roles=adapterBPM.findRolesByInstaceProcess(usuarioVO, instanciaVO);
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e, e.getDescription());
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return roles;
	}
}
