package mx.gob.imss.cit.gf.adapter.impl;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import mx.gob.imss.cit.gf.adapter.IAdapterBPM;
import mx.gob.imss.cit.gf.adapter.exception.AdapterBPMException;
import mx.gob.imss.cit.gf.vo.ActividadVO;
import mx.gob.imss.cit.gf.vo.ConsultaArgActividadVO;
import mx.gob.imss.cit.gf.vo.ConsultaParticipantesVO;
import mx.gob.imss.cit.gf.vo.DatosUsuarioVO;
import mx.gob.imss.cit.gf.vo.InstanciaVO;
import mx.gob.imss.cit.gf.vo.ModeloProcesoVO;
import mx.gob.imss.cit.gf.vo.ParticipanteVO;
import mx.gob.imss.cit.gf.vo.RolVO;
import mx.gob.imss.cit.gf.vo.SesionVO;
import mx.gob.imss.cit.gf.vo.TareaVO;
import mx.gob.imss.cit.gf.vo.UsuarioVO;

@Remote(value = IAdapterBPM.class)
@Stateless
public class AdapterBPMMock implements IAdapterBPM {

	private static final Integer CODIGO_ERROR = 10; 
	
	@Override
	public SesionVO getSesion(UsuarioVO usuarioVO) throws AdapterBPMException {
		SesionVO sesion=new SesionVO();
		sesion.setToken("Algo");
		return sesion;
	}

	
	@Override
	public List<TareaVO> findTareasInstancia(UsuarioVO usuarioVO,
			TareaVO tareaVO, String idInstancia) throws AdapterBPMException {
		List<TareaVO> listaTareas = null;
		if(idInstancia!=null && idInstancia.equals("123456")){
			listaTareas = new ArrayList<TareaVO>();
			TareaVO tarea1 = new TareaVO();
			tarea1.setIdTarea("456789");
			listaTareas.add(tarea1);			
		}else if(tareaVO.getIdTarea()!=null && tareaVO.getIdTarea().equals("666")){
			listaTareas = new ArrayList<TareaVO>();
			TareaVO tarea1 = new TareaVO();
			tarea1.setIdTarea("456789");
			listaTareas.add(tarea1);		
		}
		return listaTareas;
	}



	@Override
	public List<ParticipanteVO> findParticipantesAsignablesAActividad(
			ConsultaParticipantesVO consultaParticipantes)
			throws AdapterBPMException {
		List<ParticipanteVO> list= new ArrayList<ParticipanteVO>();
		ParticipanteVO vo=new ParticipanteVO();
		vo.setApellidoPaterno("apellido");
		vo.setCorreoElectronico("email");
		vo.setNombre("nombre");
		vo.setRol("rol");
		vo.setUsuario("usuario");
		list.add(vo);
		return list;
	}


	@Override
	public Boolean executeCerrarBPMContext(String user) {
		
		return Boolean.TRUE;
	}

	@Override
	public List<InstanciaVO> findInstanciaDetalle(InstanciaVO instanciaVO,
			UsuarioVO usuarioVO) throws AdapterBPMException {
		List<InstanciaVO> listaInstancias = null;
		if(instanciaVO.getIdInstancia().equals("132456")){
			listaInstancias = new ArrayList<InstanciaVO>();
			InstanciaVO inst1 = new InstanciaVO();
			inst1.setIdInstancia("465789");
			inst1.setFechaCreacion("01/01/2001");
			inst1.setIdProceso(1L);
			inst1.setNombreProceso("proceso");
			inst1.setUsuarioAsignado("usuario1");
			inst1.setUsuarioCreador("creador");
			inst1.setProcesoDN("proceso dn");
			inst1.setRol("rol");
			
            List<TareaVO> list=new ArrayList<TareaVO>();
            TareaVO tareaVO=new TareaVO();
            tareaVO.setComentario("comentario");
            tareaVO.setEstado("estado");
            tareaVO.setFechaCompromiso("01/01/2012");
            tareaVO.setFechaFinEjecucion("01/01/2012");
            tareaVO.setFechaInicio("01/01/2012");
            tareaVO.setFechaInicioEjecucion("01/01/2012");
            tareaVO.setIdTarea("1");
            tareaVO.setNombreTarea("nombre");
            tareaVO.setNumeroTarea(1);
            tareaVO.setPrioridad(1);			
			tareaVO.setRol("rol");
			tareaVO.setUrlTarea("url");
			tareaVO.setUsuarioCreador("usuario");
			tareaVO.setResponsableActividad(new ArrayList<String>());
			list.add(tareaVO);
			
			inst1.setListaTareas(list);
			
			listaInstancias.add(inst1);		
			
		}
		return listaInstancias;
	}

	@Override
	public String executeCrearInstanciaProceso(UsuarioVO usuarioVO,
			String procesoDN) throws AdapterBPMException {
		
		String id="";
		if(procesoDN.equals("dndndn")){
			id="132456";
		}else{
           id="123456";
		}

		return id;
	}

	@Override
	public void executeCancelarInstanciaProceso(UsuarioVO usuarioVO,
			String procesoId) throws AdapterBPMException {
	
		
	}


	@Override
	public Boolean executeCerrarSesion(String user) throws AdapterBPMException {
		
		Boolean res=true;
		
		if("alex".equals(user)){
			AdapterBPMException e=new AdapterBPMException("fallo");
			e.setCode(CODIGO_ERROR);
			throw e;
			
		}
		
		return res;
	}


	@Override
	public DatosUsuarioVO executeGuardarMetadatosUsuario(UsuarioVO usuario,
			DatosUsuarioVO createUsuario) throws AdapterBPMException {		
		return new DatosUsuarioVO();
	}


	@Override
	public void deleteMetadatosUsuario(UsuarioVO usuario, String idUsuario,
			String datosAplicacion) throws AdapterBPMException {		
		
	}


	@Override
	public List<DatosUsuarioVO> findMetadatosUsuarios(UsuarioVO usuario,
			DatosUsuarioVO datosUsuario) throws AdapterBPMException {
		return new ArrayList<DatosUsuarioVO>();
	}


	@Override
	public boolean updateEstadoTarea(UsuarioVO usuarioVO, TareaVO tareaVO)
			throws AdapterBPMException {		
		return true;
	}


	@Override
	public List<String> findResponsablesInstancias(
			List<String> idInstanciasProcesos, UsuarioVO usuarioVO)
			throws AdapterBPMException {
		return new ArrayList<String>();
	}


	@Override
	public String getArgumentoActividad(
			ConsultaArgActividadVO consultaArgActividadVO)
			throws AdapterBPMException {
		return "";
	}



	@Override
	public ModeloProcesoVO findModeloProcesoInstancia(UsuarioVO usuarioVO,
			String idInstancia) throws AdapterBPMException {
		ModeloProcesoVO vo=new ModeloProcesoVO();
		vo.setProcesoDN("dn");
		vo.setNombreProceso("nombre");
		
		List<ActividadVO> acts=new ArrayList<ActividadVO>();
		ActividadVO actividadVO=new ActividadVO();
		actividadVO.setIdActividad("id");
		actividadVO.setNivelAuditoria("auditoria");
		actividadVO.setNombre("nombre");
		actividadVO.setRol("rol");
		actividadVO.setTipoElemento("elmento");
		acts.add(actividadVO);
		
		vo.setListaActividadIniciada(acts);
		vo.setListaActividadProxima(acts);
		return vo;
	}


	@Override
	public boolean updateInstancia(UsuarioVO usuarioVO, InstanciaVO instanciaVO)
			throws AdapterBPMException {
		return false;
	}


	@Override
	public List<RolVO> findRolesByInstaceProcess(UsuarioVO usuarioVO,
			InstanciaVO instanciaVO) throws AdapterBPMException {
		List<RolVO> roles = null;
		if(instanciaVO.getIdInstancia().equals("123456")){
			roles = new ArrayList<RolVO>();
			RolVO rol1 = new RolVO();
			rol1.setRolName("Rol1");
			roles.add(rol1);
		}		
		return roles;
	}


	@Override
	public List<TareaVO> findRolTareasInstancia(UsuarioVO usuarioVO,
			TareaVO tareaVO) throws AdapterBPMException {

		   List<TareaVO> list=new ArrayList<TareaVO>();
		   TareaVO tarea=new TareaVO();
		   tarea.setComentario("comentario");
		   tarea.setEstado("estado");
		   tarea.setFechaCompromiso("01/01/2012");
		   tarea.setFechaFinEjecucion("01/01/2012");
		   tarea.setFechaInicio("01/01/2012");
		   tarea.setFechaInicioEjecucion("01/01/2012");
		   tarea.setIdTarea("1");
		   tarea.setNombreTarea("nombre");
		   tarea.setNumeroTarea(1);
		   tarea.setPrioridad(1);			
		   tarea.setRol("rol");
		   tarea.setUrlTarea("url");
		   tarea.setUsuarioCreador("usuario");
		   tarea.setResponsableActividad(new ArrayList<String>());
		   list.add(tarea);
		   
		   return list;
	}


	@Override
	public boolean updateTarea(UsuarioVO usuarioVO, TareaVO tareaVO)
			throws AdapterBPMException {

		return true;
	}


	@Override
	public TareaVO findFirstInstanceTaskForProcess(UsuarioVO usuarioVO,
			InstanciaVO instanciaVO) throws AdapterBPMException {

		return new TareaVO();
	}


	@Override
	public List<TareaVO> findTareasRemitentes(UsuarioVO usuarioVO,
			TareaVO tareaVO) throws AdapterBPMException {
		
	   List<TareaVO> list=new ArrayList<TareaVO>();
	   TareaVO tarea=new TareaVO();
	   tarea.setComentario("comentario");
	   tarea.setEstado("estado");
	   tarea.setFechaCompromiso("01/01/2012");
	   tarea.setFechaFinEjecucion("01/01/2012");
	   tarea.setFechaInicio("01/01/2012");
	   tarea.setFechaInicioEjecucion("01/01/2012");
	   tarea.setIdTarea("1");
	   tarea.setNombreTarea("nombre");
	   tarea.setNumeroTarea(1);
	   tarea.setPrioridad(1);			
	   tarea.setRol("rol");
	   tarea.setUrlTarea("url");
	   tarea.setUsuarioCreador("usuario");
	   tarea.setResponsableActividad(new ArrayList<String>());
	   list.add(tarea);
		   
		return list;
	}


	@Override
	public TareaVO executeTarea(String usuario, String idTarea)
			throws AdapterBPMException {
		
		return new TareaVO();
	}


	@Override
	public List<ModeloProcesoVO> findModeloProcesoIdentificador(
			UsuarioVO usuarioVO, String procesoDN) throws AdapterBPMException {
	
		return new ArrayList<ModeloProcesoVO>();
	}


	@Override
	public Map<String, String> findModeloProcesoTipo(UsuarioVO usuarioVO,
			String tipo) throws AdapterBPMException {

		return new Hashtable<String, String>();
	}


	@Override
	public List<String> findInstanciasProceso(UsuarioVO usuarioVO,
			String procesoDN) throws AdapterBPMException {
		
		return new ArrayList<String>();
	}

}
