package mx.gob.imss.cit.gf.test.service;

import javax.ejb.EJB;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.ISesionService;
import mx.gob.imss.cit.gf.test.BaseTest;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase para probar la integracion
 * @author ajfuentes
 *
 */
@RunWith(Arquillian.class)
public class SesionServiceTest{
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SesionServiceTest.class);
	
	/**
	 * EJB de sesion
	 */
	@EJB
	private ISesionService sesionService;
	
	/**
	 * codigo error parametro nulo
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;	

	/**
	 * Metodo de prueba para iniciar la sesion
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testGetSesion() {

		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setUsuario("alex");
		usuarioVO.setPassword("alex01");
		SesionVO sesionVO=null;
		try {
			sesionVO=sesionService.getSesion(usuarioVO);
			LOG.debug(sesionVO.getUsuario());
			LOG.debug(sesionVO.getToken());
			Assert.assertNotNull("Exito", sesionVO);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para iniciar la sesion
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testGetSesion_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setUsuario("alex");

		SesionVO sesionVO=null;
		try {
			sesionVO=sesionService.getSesion(usuarioVO);
			LOG.debug(sesionVO.getToken());
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	/**
	 * Metodo de prueba para cerrar la sesion
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testCloseSesion() {

		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setUsuario("alex01");
		usuarioVO.setPassword("alex01");
		try {
			Boolean valor=sesionService.deleteSesion(usuarioVO);

			Assert.assertNotNull("Exito", valor);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para cerrar la sesion
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testCloseSesion_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		usuarioVO.setUsuario(null);

		try {
			sesionService.deleteSesion(usuarioVO);
			Assert.fail(" debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
}
