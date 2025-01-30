package klv.apu.servicio;

import klv.apu.modelo.Actividad;
import java.util.List;

public interface IActividadServicio {

    public  List<Actividad> listarActividades();

    public Actividad buscarActividadporId(Integer idActividad);

    public Actividad guardarActividad(Actividad actividad);

    public void eliminarActividadporId(Integer idActividad);

    public Actividad asignarElemento(Integer idActividad,Integer idElemento);




}
