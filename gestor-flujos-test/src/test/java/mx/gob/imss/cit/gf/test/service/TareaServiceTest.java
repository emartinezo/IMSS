package mx.gob.imss.cit.gf.test.service;

import java.util.List;

import javax.ejb.EJB;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.ITareaService;
import mx.gob.imss.cit.gf.test.BaseTest;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
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
 * Clase para probar la capa de serivicio de Tarea
 * 
 * @author Admin
 */

@RunWith(Arquillian.class)
public class TareaServiceTest {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TareaServiceTest.class);
	
	/**
	 * EJB de TareaService
	 */
	@EJB
	private ITareaService iTareaService;
	
	/**
	 * codigo error parametro nulo
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;	
	
	/**
	 * Metodo de prueba para consultar tareas de una instancia
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasInstancia() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		String idInstancia = "123456";
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			List<TareaVO>tareas=iTareaService.findTareasInstancia(usuarioVO, idInstancia);
			for(TareaVO tarea:tareas){
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
			
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar tareas de una instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasInstancia_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			String idInstancia = null;	
			iTareaService.findTareasInstancia(usuarioVO, idInstancia);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	
	
	
	/**
	 * Metodo de prueba para consultar tareas de una instancia
	 * por estado
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareas() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			tareaVO.setEstado("COMPLETED");
			tareaVO.setIdTarea("666");
			List<TareaVO>tareas=iTareaService.findTareas(usuarioVO, tareaVO);
			
			for(TareaVO tarea:tareas){
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
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar tareas de una instancia
	 * por estado con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareas_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		TareaVO tareaVO = null;
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");	
			iTareaService.findTareas(usuarioVO, tareaVO);
			Assert.fail("debio fallar");
		} catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	
	
	/**
	 * Metodo de prueba para actualizar el estado de una tarea
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateEstadoTarea() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			tareaVO.setIdTarea("Task1");
			tareaVO.setEstado("SUSPENDED");
			boolean res=iTareaService.updateEstadoTarea(usuarioVO, tareaVO);
			Assert.assertTrue(res);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para actualizar el estado de una tarea con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateEstadoTarea_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		TareaVO tareaVO = null;
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");	
			iTareaService.updateEstadoTarea(usuarioVO, tareaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	

	/**
	 * Metodo de prueba para consultar un rol de tarea por instancia
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindRolTareasByInstancia() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			tareaVO.setIdTarea("Task1");
			List<TareaVO>tareas=iTareaService.findRolTareasByInstancia(usuarioVO, tareaVO);
			
			for(TareaVO tarea:tareas){
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
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar un rol de tarea por instancia con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindRolTareasByInstancia_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		TareaVO tareaVO = null;
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");	
			iTareaService.findRolTareasByInstancia(usuarioVO, tareaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	/**
	 * Metodo de prueba para consultar tarea con estatus asignada
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasAsignadas() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		String idInstancia = "123456";
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			List<TareaVO>tareas=iTareaService.findTareasAsignadas(usuarioVO, idInstancia);
			
			for(TareaVO tarea:tareas){
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
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar tarea con estatus asignada
	 * con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasAsignadas_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		String idInstancia = null;
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");	
			iTareaService.findTareasAsignadas(usuarioVO, idInstancia);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());			
		}
	}
	
	
	
	
	/**
	 * Metodo de prueba para obtener un argumento de actividad
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testGetArgumentoActividad() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		ConsultaArgActividadVO act = new ConsultaArgActividadVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			act.setUsuarioVO(usuarioVO);
			act.setIdInstanciaProceso("123456");
			act.setIdInstanciaActividad("Act1");
			act.setNombreVble("msg");
			String value=iTareaService.getArgumentoActividad(act);
			LOG.info(value);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para obtener un argumento de actividad
	 * con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testGetArgumentoActividad_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		ConsultaArgActividadVO act = new ConsultaArgActividadVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			act.setUsuarioVO(usuarioVO);
			act.setIdInstanciaProceso("123456");
			act.setIdInstanciaActividad("Act1");
			act.setNombreVble(null);
			iTareaService.getArgumentoActividad(act);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	
	
	
	/**
	 * Metodo de prueba para actualizar atributos de una actividad
	 * 
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateTarea() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			tareaVO.setIdTarea("Task1");
			boolean res=iTareaService.updateTarea(usuarioVO, tareaVO);
			Assert.assertTrue(res);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para actualizar atributos de una actividad
	 * con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testUpdateTarea_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = null;
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			iTareaService.updateTarea(usuarioVO, tareaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}		
	
	
	/**
	 * Metodo de prueba para ejecutar una tarea
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteTarea() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		String idInstancia = "123456";
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			iTareaService.executeTarea(usuarioVO, idInstancia);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para ejecutar una tarea con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteTarea_error() {

		UsuarioVO usuarioVO=new UsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			String idInstancia = null;	
			iTareaService.executeTarea(usuarioVO, idInstancia);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}	
	
	
	
	
	
	/**
	 * Metodo de prueba para consultar tareas remitentes
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasRemitentes() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();		
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			tareaVO.setIdTarea("Task1");
			List<TareaVO>tareas=iTareaService.findTareasRemitentes(usuarioVO, tareaVO);
			for(TareaVO tarea:tareas){
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
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar tareas remitentes con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindTareasRemitentes_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = null;		
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			
			iTareaService.findTareasRemitentes(usuarioVO, tareaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	
	
	
	/**
	 * Metodo de prueba para consultar primera tarea de proceso
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindFirstInstanceTaskForProcess() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		InstanciaVO instanciaVO = new InstanciaVO();		
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			instanciaVO.setIdInstancia("idInstancia1");
			TareaVO tarea=iTareaService.findFirstInstanceTaskForProcess(usuarioVO, instanciaVO);

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
			
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	

	/**
	 * Metodo de prueba para consultar primera tarea de proceso con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindFirstInstanceTaskForProcess_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		InstanciaVO instanciaVO = null;		
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");			
			iTareaService.findFirstInstanceTaskForProcess(usuarioVO, instanciaVO);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}

}
