package mx.gob.imss.cit.gf.test.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IInstanciaService;
import mx.gob.imss.cit.gf.test.BaseTest;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
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
public class InstanciaServiceTest{
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(InstanciaServiceTest.class);
	
	/**
	 * EJB de sesion
	 */
	@EJB
	private IInstanciaService iInstanciaService;

	/**
	 * codigo error parametro nulo
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;	
	

	/**
	 * Metodo de prueba para buscar una instancia
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindInstancia() {


		InstanciaVO instanciaVO=new InstanciaVO();
		UsuarioVO usuarioVO=new UsuarioVO();
		try {
			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciaVO.setEstado("Uno");
			instanciaVO.setIdInstancia("132456");
			instanciaVO.setIdProceso(50L);
			instanciaVO.setNombreProceso("nombre");
			instanciaVO.setProcesoDN("proceso");
			instanciaVO.setRol("admin");
			instanciaVO.setUsuarioCreador("alex3");
			instanciaVO.setFechaCreacion("12/08/2014");
			iInstanciaService.findInstancias(instanciaVO, usuarioVO);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para buscar instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindInstancia_error() {


		InstanciaVO instanciaVO=new InstanciaVO();
		UsuarioVO usuarioVO=new UsuarioVO();
		try {
			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword(null);
			instanciaVO.setEstado("Uno");
			instanciaVO.setIdInstancia("12345");
			instanciaVO.setIdProceso(50L);
			instanciaVO.setNombreProceso("nombre");
			instanciaVO.setProcesoDN("proceso");
			instanciaVO.setRol("admin");
			instanciaVO.setUsuarioCreador("alex3");

			iInstanciaService.findInstancias(instanciaVO, usuarioVO);
			Assert.fail("debio fallar");
		} catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	
	/**
	 * Metodo de prueba para crear instancia de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteCrearInstanciaProceso() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoDN = null;
		String idInstancia= null;
		try{			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			procesoDN = "123456";
			idInstancia = iInstanciaService.executeCrearInstanciaProceso(usuarioVO, procesoDN);
			Assert.assertNotNull(idInstancia);
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para crear instancia de proceso con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteCrearInstanciaProceso_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoDN = null;
		try{			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			procesoDN = "";
			iInstanciaService.executeCrearInstanciaProceso(usuarioVO, procesoDN);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	

	/**
	 * Metodo de prueba para cancelar una instancia de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteCancelarInstanciaProceso() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoId = null;
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			procesoId = "123456";
			iInstanciaService.executeCancelarInstanciaProceso(usuarioVO, procesoId);			
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
		
	
	/**
	 * Metodo de prueba para cancelar una instancia de proceso con error
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteCancelarInstanciaProceso_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoId = null;
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			iInstanciaService.executeCancelarInstanciaProceso(usuarioVO, procesoId);			
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	
	
	
	/**
	 * Metodo de prueba para consultar responsables de instancia
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindResponsablesInstancias() {
		UsuarioVO usuarioVO=new UsuarioVO();
		List<String> instanciasProcesos = null;
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciasProcesos = new ArrayList<String>();
			String instanciaProceso1 = "inst1";
			instanciasProcesos.add(instanciaProceso1);
			List<String> lista=iInstanciaService.findResponsablesInstancias(instanciasProcesos, usuarioVO);
			for (String s : lista) {
				LOG.info(s);
			}
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	
	/**
	 * Metodo de prueba para consultar responsables de instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindResponsablesInstancias_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		List<String> instanciasProcesos = null;
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword(null);
			instanciasProcesos = new ArrayList<String>();
			String instanciaProceso1 = "inst1";
			instanciasProcesos.add(instanciaProceso1);
			iInstanciaService.findResponsablesInstancias(instanciasProcesos, usuarioVO);			
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	
	/**
	 * Metodo de prueba para actualizar una instancia
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateInstancia() {
		UsuarioVO usuarioVO=new UsuarioVO();
		InstanciaVO instanciaVO = new InstanciaVO();
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciaVO.setIdInstancia("123456");
			instanciaVO.setEstado("COMPLETED");
			iInstanciaService.updateInstancia(usuarioVO, instanciaVO);			
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}	
	
	
	/**
	 * Metodo de prueba para actualizar una instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateInstancia_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		InstanciaVO instanciaVO = new InstanciaVO();
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			
			instanciaVO.setEstado(null);
			iInstanciaService.updateInstancia(usuarioVO, instanciaVO);			
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	/**
	 * Metodo de prueba para consultar roles por instancia de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindRolesByInstaceProcess() {
		UsuarioVO usuarioVO=new UsuarioVO();
		InstanciaVO instanciaVO = new InstanciaVO();
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciaVO.setIdInstancia("123456");
			List<RolVO> roles = iInstanciaService.findRolesByInstaceProcess(usuarioVO, instanciaVO);
			for (RolVO rolVO : roles) {
				LOG.info(rolVO.getRolName());
			}
			Assert.assertNotNull(roles);
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	
	/**
	 * Metodo de prueba para consultar roles por instancia de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindRolesByInstaceProcess_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		InstanciaVO instanciaVO = new InstanciaVO();
		try{						
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciaVO.setIdInstancia(null);
			iInstanciaService.findRolesByInstaceProcess(usuarioVO, instanciaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	
	
	
	/**
	 * Metodo de prueba para crear instancia de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteInitInstanciaProceso() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoDN = null;
		InstanciaVO idInstancia= null;
		try{			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			procesoDN = "dndndn";
			idInstancia = iInstanciaService.executeInitInstanciaProceso(usuarioVO, procesoDN);
			
			
			LOG.info(idInstancia.getEstado());
			LOG.info(idInstancia.getFechaCreacion());
			LOG.info(idInstancia.getIdInstancia());
			LOG.info(idInstancia.getNombreProceso());
			LOG.info(idInstancia.getProcesoDN());
			LOG.info(idInstancia.getRol());
			LOG.info(idInstancia.getUsuarioAsignado());
			LOG.info(idInstancia.getUsuarioCreador());
			for(TareaVO tarea:idInstancia.getListaTareas()){
				LOG.info(tarea.getComentario());
				LOG.info(tarea.getEstado());
				LOG.info(tarea.getFechaCompromiso());
				LOG.info(tarea.getFechaFinEjecucion());
				LOG.info(tarea.getFechaInicio());
				LOG.info(tarea.getFechaInicioEjecucion());
				LOG.info(tarea.getIdTarea());
				LOG.info(tarea.getNombreTarea());
				LOG.info(tarea.getRol());
				LOG.info(tarea.getUrlTarea());
				LOG.info(tarea.getUsuarioCreador());
				LOG.info(""+tarea.getNumeroTarea());
				LOG.info(""+tarea.getPrioridad());
				LOG.info(""+tarea.getResponsableActividad());		
			}
			
			
			Assert.assertNotNull(idInstancia);
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para crear instancia de proceso con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteInitInstanciaProceso_error() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoDN = null;
		try{			
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			procesoDN = "";
			iInstanciaService.executeInitInstanciaProceso(usuarioVO, procesoDN);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
}
