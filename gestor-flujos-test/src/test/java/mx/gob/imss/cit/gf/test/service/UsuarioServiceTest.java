package mx.gob.imss.cit.gf.test.service;

import java.util.List;

import javax.ejb.EJB;

import mx.gob.imss.cit.gf.exception.GestorFlujosException;
import mx.gob.imss.cit.gf.services.IUsuarioService;
import mx.gob.imss.cit.gf.test.BaseTest;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase para probar la capa de serivicio de Usuario
 * 
 * @author Admin
 */

@RunWith(Arquillian.class)
public class UsuarioServiceTest {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TareaServiceTest.class);
	
	/**
	 * codigo error parametro nulo
	 */
	private static final int ERROR_OBLIGATORIO_CODIGO = 101;	
	
	/**
	 * EJB Usuario
	 */
	@EJB	
	private IUsuarioService iUsuarioService;
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteGuardarMetadatosUsuario() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setOwner("owner");
			datosUsr.setName("name");			
			DatosUsuarioVO vo=iUsuarioService.executeGuardarMetadatosUsuario(usuarioVO, datosUsr);
			LOG.info(vo.getApplicationDataType());
			LOG.info(vo.getData());
			LOG.info(vo.getId());
			LOG.info(vo.getIdentityContext());
			LOG.info(vo.getName());
			LOG.info(vo.getOwner());
			Assert.assertNotNull(vo);
			
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testExecuteGuardarMetadatosUsuario_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setOwner("owner");
			datosUsr.setName(null);			
			iUsuarioService.executeGuardarMetadatosUsuario(usuarioVO, datosUsr);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testDeleteMetadatosUsuario() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			String idUsuario = "idUser";
			String datosAplicacion = "appData";
			iUsuarioService.deleteMetadatosUsuario(usuarioVO, idUsuario, datosAplicacion);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testDeleteMetadatosUsuario_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			String idUsuario = "idUser";
			String datosAplicacion = null;
			iUsuarioService.deleteMetadatosUsuario(usuarioVO, idUsuario, datosAplicacion);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());
		}
	}
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindMetadatosUsuarios() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setOwner("owner");
			datosUsr.setName("name");	
			List<DatosUsuarioVO> lista=iUsuarioService.findMetadatosUsuarios(usuarioVO, datosUsr);
			for (DatosUsuarioVO datosUsuarioVO : lista) {
				LOG.info(datosUsuarioVO.getApplicationDataType());
				LOG.info(datosUsuarioVO.getData());
				LOG.info(datosUsuarioVO.getId());
				LOG.info(datosUsuarioVO.getIdentityContext());
				LOG.info(datosUsuarioVO.getName());
				LOG.info(datosUsuarioVO.getOwner());
			}
			Assert.assertNotNull(lista);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}	

	/**
	 * Metodo de prueba para guardar metadatos del usuario con error
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindMetadatosUsuarios_error() {

		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("alex");
			usuarioVO.setPassword("alex01");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setName(null);	
			iUsuarioService.findMetadatosUsuarios(usuarioVO, datosUsr);
			Assert.fail("debio fallar");
		}catch (GestorFlujosException e) {			
			LOG.error(e.getMessage(),e);
			Assert.assertEquals(ERROR_OBLIGATORIO_CODIGO, e.getCode());			
		}
	}
	
	/**
	 * Metodo de prueba para consultar participantes asignables a actividades
	 */
	@Test
	@OperateOnDeployment(BaseTest.DEPLOY_GLASSFISH)
	public void testFindParticipantesAsignablesAActividad() {		
		ConsultaParticipantesVO participantes = new ConsultaParticipantesVO();
		participantes.setIdInstanciaActividad(1L);
		participantes.setIdInstanciaProceso(1L);
		UsuarioVO vo=new UsuarioVO();
		participantes.setUsuario(vo);
		participantes.getIdInstanciaActividad();
		participantes.getIdInstanciaProceso();
		participantes.getUsuario();
		
		try {	
			List<ParticipanteVO> lista=iUsuarioService.findParticipantesAsignablesAActividad(participantes);
			for (ParticipanteVO participanteVO : lista) {
				LOG.info(participanteVO.getApellidoPaterno());
				LOG.info(participanteVO.getCorreoElectronico());
				LOG.info(participanteVO.getNombre());
				LOG.info(participanteVO.getRol());
				LOG.info(participanteVO.getUsuario());
			}
			
			Assert.assertNotNull(lista);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
}
