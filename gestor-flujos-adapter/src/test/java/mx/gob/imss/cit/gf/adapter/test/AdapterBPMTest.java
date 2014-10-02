/**
 * 
 */
package mx.gob.imss.cit.gf.adapter.test;

import java.util.ArrayList;
import java.util.List;

import mx.gob.imss.cit.gf.adapter.impl.AdapterBPM;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase de prueba para el adapter
 * @author admin
 * 
 */
//@Ignore
public class AdapterBPMTest {

	/**
	 * adapter del bpm
	 */
	private AdapterBPM iAdapterBPM;
	
	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AdapterBPMTest.class);

	@Before
	public void init() {
		iAdapterBPM=new AdapterBPM();
	}

	/**
	 * probar sesion
	 */
	@Test
	public void testGetSesion() {
		try {
			UsuarioVO usuarioVO=new UsuarioVO();
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			SesionVO vo=iAdapterBPM.getSesion(usuarioVO);
			LOG.info(vo.getToken());
			
			iAdapterBPM.executeCerrarSesion("javier");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}

	}
	
	/**
	 * probar cerrar sesion
	 */
	@Test
	public void testExecuteCerrarSesion() {
		
		try {
			UsuarioVO usuarioVO=new UsuarioVO();
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
			iAdapterBPM.getSesion(usuarioVO);

			iAdapterBPM.executeCerrarSesion("javier");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 *  cerrar sesion
	 */
	@Test
	public void testExecuteCerrarBPMContext() {
		
		try {
			UsuarioVO usuarioVO=new UsuarioVO();
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
		
			iAdapterBPM.getSesion(usuarioVO);
			
			iAdapterBPM.executeCerrarBPMContext("javier");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * find instancia
	 */
	@Test
	public void testFindInstanciaDetalle() {
		
		try {
			UsuarioVO usuarioVO=new UsuarioVO();
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
			
			InstanciaVO instanciaVO=new InstanciaVO();
			instanciaVO.setIdInstancia("40051");
							
			List<InstanciaVO> lista=iAdapterBPM.findInstanciaDetalle(instanciaVO,usuarioVO);
			for (InstanciaVO instanciaVO2 : lista) {
				LOG.info(instanciaVO2.getEstado());
				LOG.info(instanciaVO2.getFechaCreacion());
				LOG.info(instanciaVO2.getIdInstancia());
				LOG.info(instanciaVO2.getNombreProceso());
				LOG.info(instanciaVO2.getProcesoDN());
				LOG.info(instanciaVO2.getRol());
				LOG.info(instanciaVO2.getUsuarioAsignado());
				LOG.info(instanciaVO2.getUsuarioCreador());
				for(TareaVO tarea:instanciaVO2.getListaTareas()){
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
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	
	/**
	 *  cerrar sesion
	 */
	@Test
	public void testExecuteCrearInstancia() {
		
		try {
			UsuarioVO usuarioVO=new UsuarioVO();
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");

			iAdapterBPM.executeCrearInstanciaProceso(usuarioVO,"default/ProyectoDesarrollo!1.0*/ProcesoLargo");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Metodo de prueba para cancelar una instancia de proceso con error
	 * 
	 */
	@Test
	public void testExecuteCancelarInstanciaProceso() {
		UsuarioVO usuarioVO=new UsuarioVO();
		String procesoId = null;
		try{						
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
			procesoId="4005";
			iAdapterBPM.executeCancelarInstanciaProceso(usuarioVO, procesoId);			

		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}	
	
	

	/**
	 * Metodo de prueba para cancelar una instancia de proceso con error
	 * 
	 */
	@Test
	public void testFindTareasIntancia() {
		UsuarioVO usuarioVO=new UsuarioVO();
		TareaVO tareaVO=new TareaVO();
		String idInstancia="40001";
		
		try{						
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
			
			tareaVO.setUsuarioCreador("javier");
			
			List<TareaVO> list=iAdapterBPM.findTareasInstancia(usuarioVO, tareaVO, idInstancia);
			for(TareaVO tarea:list){
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

		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}	
	
	
	/**
	 * Metodo de prueba para cancelar una instancia de proceso con error
	 * 
	 */
	@Test
	public void testFindRolTareasIntancia() {
		UsuarioVO usuarioVO=new UsuarioVO();
		TareaVO tareaVO=new TareaVO();
		
		try{						
			usuarioVO.setUsuario("javier");
			usuarioVO.setPassword("password1");
			
			tareaVO.setUsuarioCreador("javier");
			
			List<TareaVO> list=iAdapterBPM.findRolTareasInstancia(usuarioVO, tareaVO);
			for(TareaVO tarea:list){
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

		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
		}
	}	
	
	
	/**
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	public void testExecuteGuardarMetadatosUsuario() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setOwner("angelica");
			datosUsr.setName("name");			
			DatosUsuarioVO vo=iAdapterBPM.executeGuardarMetadatosUsuario(usuarioVO, datosUsr);
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
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	public void testDeleteMetadatosUsuario() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		try {
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			String idUsuario = "idUser";
			String datosAplicacion = "appData";
			iAdapterBPM.deleteMetadatosUsuario(usuarioVO, idUsuario, datosAplicacion);
			Assert.assertTrue(true);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}

	/**
	 * Metodo de prueba para guardar metadatos del usuario
	 */
	@Test
	public void testFindMetadatosUsuarios() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		DatosUsuarioVO datosUsr = new DatosUsuarioVO();
		try {
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			datosUsr.setApplicationDataType("appData");
			datosUsr.setOwner("angelica");
			datosUsr.setName("name");	
			List<DatosUsuarioVO> lista=iAdapterBPM.findMetadatosUsuarios(usuarioVO, datosUsr);
			Assert.assertNotNull(lista);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}	
	
	
	/**
	 * Metodo de prueba para consultar participantes asignables a actividades
	 */
	@Test
	public void testFindParticipantesAsignablesAActividad() {		
		ConsultaParticipantesVO participantes = new ConsultaParticipantesVO();
		participantes.setIdInstanciaActividad(1L);
		participantes.setIdInstanciaProceso(30015L);
		UsuarioVO usuarioVO=new UsuarioVO();
		usuarioVO.setUsuario("angelica");
		usuarioVO.setPassword("password1");
		participantes.setUsuario(usuarioVO);
		participantes.getIdInstanciaActividad();
		participantes.getIdInstanciaProceso();
		participantes.getUsuario();
		
		try {	
			List<ParticipanteVO> lista=iAdapterBPM.findParticipantesAsignablesAActividad(participantes);
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
	
	
	/**
	 * Metodo de prueba para actualizar el estado de una tarea
	 * 
	 */
	@Test
	public void testUpdateEstadoTarea() {
		
		UsuarioVO usuarioVO = new UsuarioVO();
		TareaVO tareaVO = new TareaVO();
		try {
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			tareaVO.setIdTarea("2c6ccc6d-a459-496f-888f-1a69dc73ce76");
			tareaVO.setEstado("SUSPENDED");
			iAdapterBPM.updateEstadoTarea(usuarioVO, tareaVO);
		} catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}
	
	
	/**
	 * Metodo de prueba para consultar responsables de instancia
	 */
	@Test
	public void testFindResponsablesInstancias() {
		UsuarioVO usuarioVO=new UsuarioVO();
		List<String> instanciasProcesos = null;
		try{						
			usuarioVO.setUsuario("angelica");
			usuarioVO.setPassword("password1");
			instanciasProcesos = new ArrayList<String>();
			String instanciaProceso1 = "30015";
			instanciasProcesos.add(instanciaProceso1);
			iAdapterBPM.findResponsablesInstancias(instanciasProcesos, usuarioVO);			
		}catch (Exception e) {
			LOG.error(e.getMessage(),e);
			Assert.fail("No debio fallar");
		}
	}

}
