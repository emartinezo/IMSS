package mx.gob.imss.cit.gf.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.constant.NombreParametroConstants;
import mx.gob.imss.cit.gf.constant.ValidacionesConstants;
import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.ISesionService;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesExceptionBuilder;
import mx.gob.imss.cit.gf.util.ValidatorUtil;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implementacion de metodos referentes al manejo de la 
 * sesion del BPM
 * @author Admin
 *
 */
@Remote(ISesionService.class)
@Stateless
public class SesionService implements ISesionService{

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SesionService.class);
	
	/**
	 * Interfaz de AdapterBPM
	 */
	@EJB
	private IAdapterBPM adapterBPM;


	/**
     **************************** DESCRIPCION *****************************
     *Nombre CU: Iniciar sesion en el Administrador de Procesos
     *Descripcion : Metodo que obtiene el contexto (inicio de sesion)
     *				del engine del BPM.
     **************************** PARMETROS ********************************
     *@param usuarioVO 
     *			Objeto que contiene el password y usuario para la sesion.
     *@return sesionVO  
     *			Obtiene los datos de la sesion.
     ************************** LISTA DE ERRORES **************************
     *************************** DISENO TECNICO ***************************
     *VALIDACIONES
     *-------------------------
     */
	public SesionVO getSesion(UsuarioVO usuarioVO) throws GestorFlujosServicesException{
		
		LOG.info("obtener la sesion");
		SesionVO sesionVO =null;
		try {
			
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			
			sesionVO = adapterBPM.getSesion(usuarioVO);
		
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}
		
		return sesionVO;
	}

	/**
     **************************** DESCRIPCION *****************************
     *Nombre CU: Elimina sesion en el Administrador de Procesos
     *Descripcion : Metodo que obtiene el contexto (inicio de sesion)
     *				del engine del BPM.
     **************************** PARMETROS ********************************
     *@param usuarioVO 
     *			Objeto que contiene el password y usuario para la sesion.
     *@return sesionVO  
     *			Obtiene los datos de la sesion.
     ************************** LISTA DE ERRORES **************************
     *************************** DISENO TECNICO ***************************
     *VALIDACIONES
     *-------------------------
     */
	@Override
	public Boolean deleteSesion(UsuarioVO usuarioVO)
			throws GestorFlujosServicesException {
	try {
			
			//validacion de parametros
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);			
			
			adapterBPM.executeCerrarSesion(usuarioVO.getUsuario());
		
		} catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}
	
	   return Boolean.TRUE;
		
	}



}
