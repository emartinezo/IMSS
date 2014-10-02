package mx.gob.imss.cit.gf.test.service;

import javax.ejb.EJB;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IModeloService;
import mx.gob.imss.cit.gf.test.BaseTest;
import mx.gob.imss.cit.gf.vo.ActividadVO;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para probar la capa de serivicio de Modelo
 * @author Admin
 *
 */

@RunWith(Arquillian.class)
public class ModeloServiceTest {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ModeloServiceTest.class);
	
	/**
	 * EJB de modelo
	 */
	@EJB
	private IModeloService iModeloService;
	
	/**
	 * codigo error parametro nulo
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;	
	
	
	/**
	 * Metodo de prueba para consultar el modelo de proceso de una instancia
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindModeloProcesoInstancia() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		String idInstancia = "123456";
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			ModeloProcesoVO modelo = iModeloService.findModeloProcesoInstancia(usuarioVO, idInstancia);
			LOG.info(modelo.getNombreProceso());
			LOG.info(modelo.getProcesoDN());
			
			for(ActividadVO actividadVO:modelo.getListaActividadIniciada()){
				LOG.info(actividadVO.getIdActividad());
				LOG.info(actividadVO.getNivelAuditoria());
				LOG.info(actividadVO.getNombre());
				LOG.info(actividadVO.getRol());
				LOG.info(actividadVO.getTipoElemento());
			}
			
			for(ActividadVO actividadVO:modelo.getListaActividadProxima()){
				LOG.info(actividadVO.getIdActividad());
				LOG.info(actividadVO.getNivelAuditoria());
				LOG.info(actividadVO.getNombre());
				LOG.info(actividadVO.getRol());
				LOG.info(actividadVO.getTipoElemento());
			}
			
			Assert.assertNotNull(modelo);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar el modelo de proceso de una instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindModeloProcesoInstancia_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		try {
			String idInstancia = null;	
			iModeloService.findModeloProcesoInstancia(usuarioVO, idInstancia);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
}
