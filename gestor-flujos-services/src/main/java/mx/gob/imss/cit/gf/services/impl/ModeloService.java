/**
 * 
 */
package mx.gob.imss.cit.gf.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.constant.NombreParametroConstants;
import mx.gob.imss.cit.gf.constant.ValidacionesConstants;
import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IModeloService;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesException;
import mx.gob.imss.cit.gf.services.exception.GestorFlujosServicesExceptionBuilder;
import mx.gob.imss.cit.gf.util.ValidatorUtil;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ahernandezd
 *
 */

@Remote(IModeloService.class)
@Stateless
public class ModeloService implements IModeloService {

	/**
	 * Tamanio de lista
	 */
	private static final Integer ZERO = 0;

	/**
	 * Interfaz de AdapterBPM
	 */
	@EJB
	private IAdapterBPM adapterBPM;

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ModeloService.class);	
	
	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Consultar Modelo Procesos por Instancias.
	 *Descripcion : Metodo que extrae por id de instancua
	 *				 el modelo del proceso(actividades).
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param idInstancia identificador de la instancia.
	 * @return ModeloProcesoVO
	 * 			Contiene el modelo del proceso.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public ModeloProcesoVO findModeloProcesoInstancia(UsuarioVO usuarioVO,
			String idInstancia) throws GestorFlujosServicesException {
			ModeloProcesoVO modeloProcesoVO = null;
		
		try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.MODELO_ID_INSTANCIA, idInstancia);
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.MODELO_ID_INSTANCIA,idInstancia, ValidacionesConstants.ER_ALFANUMERICO_CON_GUION_1A50);
			
			adapterBPM.getSesion(usuarioVO);
			modeloProcesoVO = adapterBPM.findModeloProcesoInstancia(usuarioVO, idInstancia);
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return modeloProcesoVO;
	}

	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Consultar modelos de proceso por identificador.
	 *Descripcion: Metodo que obtiene todas los modelos de la 
	 *				instancias asociadas al proceso expuesto.
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param procesoDN identificador del proceso.
	 * @return lstModeloProcesoVO
	 * 			lista contiene los modelos y las instancias.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public List<ModeloProcesoVO> findModeloProcesoIdentificador(
			UsuarioVO usuarioVO, String procesoDN)
			throws GestorFlujosServicesException {
		List<ModeloProcesoVO> listModeloProcesoVO = null;
		try {
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.MODELO_PROCESO_DN, procesoDN);
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.MODELO_PROCESO_DN, procesoDN, ValidacionesConstants.ER_ALFANUMERICO_CON_ESPECIALES_1A150);
			
			adapterBPM.getSesion(usuarioVO);
			listModeloProcesoVO = adapterBPM.findModeloProcesoIdentificador(usuarioVO, procesoDN);
			
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return listModeloProcesoVO;
	}
	/**
	 **************************** DESCRIPCION *****************************
	 *Nombre CU: Consultar modelos de procesos por tipo
	 *Descripcion: Metodo que extrae los modelos de la instancia por tipo de proceso. 
	 **************************** PARMETROS ********************************
	 * @param usuarioVO
	 * 		  Contiene los datos para iniciar la sesion:
	 * 		  {usuario(String),password(String)}.
	 * @param tipo tipo de proceso
	 * @return lstModeloProcesoVO
	 * 			lista contiene los modelos y las instancias.
	 * @throws GestorFlujosServicesException
	 ************************** LISTA DE ERRORES **************************
	 *************************** DISENO TECNICO ***************************
	 *VALIDACIONES
	 *-------------------------
	 */
	@Override
	public List<ModeloProcesoVO> findModeloProcesoTipo(UsuarioVO usuarioVO,
			String tipo) throws GestorFlujosServicesException {
		List<ModeloProcesoVO> listaModelos = new ArrayList <ModeloProcesoVO>();
		Map<String, String> mapaProcesoDN = null;
		ModeloProcesoVO modeloProcesoVO = null;

		try {
			
			Map<String,Object> parametros=new HashMap<String, Object>();
			parametros.put(NombreParametroConstants.USUARIO, usuarioVO.getUsuario());
			parametros.put(NombreParametroConstants.PASSWORD, usuarioVO.getPassword());
			parametros.put(NombreParametroConstants.MODELO_PROCESO_TIPO, tipo);
			ValidatorUtil.validateParametros(parametros);
			
			//validacion de tipo formato
			ValidatorUtil.validateFormato(NombreParametroConstants.USUARIO,usuarioVO.getUsuario(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.PASSWORD,usuarioVO.getPassword(), ValidacionesConstants.ER_ALFANUMERICO1A50);
			ValidatorUtil.validateFormato(NombreParametroConstants.MODELO_PROCESO_TIPO, tipo, ValidacionesConstants.ER_ALFANUMERICO1A50);
			mapaProcesoDN = adapterBPM.findModeloProcesoTipo(usuarioVO, tipo);
			
			if(mapaProcesoDN != null && mapaProcesoDN.size() > ZERO) {
				adapterBPM.getSesion(usuarioVO);			
				for(Entry<String,String> entry:mapaProcesoDN.entrySet()){
					
					LOG.info("Clave : " + entry.getKey() + " - Valor : " + entry.getValue() );
					modeloProcesoVO = new ModeloProcesoVO();
					modeloProcesoVO.setInstancias(adapterBPM.findInstanciasProceso(usuarioVO, entry.getKey().toString()));
					modeloProcesoVO.setProcesoDN(entry.getKey());
					modeloProcesoVO.setNombreProceso(entry.getValue());
					listaModelos.add(modeloProcesoVO);
					
				}
			}
		} catch (AdapterBPMException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  
					e.getDescription());
		}catch (GestorFlujosException e) {
			throw GestorFlujosServicesExceptionBuilder.build(e.getCode(), e,  e.getDescription());
		}finally{
			adapterBPM.executeCerrarBPMContext(usuarioVO.getUsuario());
		}
		return listaModelos;
	}

}
