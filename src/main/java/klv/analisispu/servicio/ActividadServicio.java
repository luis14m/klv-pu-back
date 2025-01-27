package klv.analisispu.servicio;

import jakarta.transaction.Transactional;
import klv.analisispu.modelo.Actividad;
import klv.analisispu.modelo.Elemento;
import klv.analisispu.repositorio.ActividadRepositorio;
import klv.analisispu.repositorio.ElementoRepositorio;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadServicio implements IActividadServicio{

    @Autowired
    private ActividadRepositorio actividadRepositorio;

    @Autowired
    private ElementoRepositorio elementoRepositorio;

    @Override
    public List<Actividad> listarActividades() {
        return this.actividadRepositorio.findAll();

    }

    @Override
    public Actividad buscarActividadporId(Integer idActividad) {
        Actividad actividad =
                this.actividadRepositorio.findById(idActividad).
                        orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        return actividad;
    }

    @Override
    public Actividad guardarActividad(Actividad actividad) {

        return this.actividadRepositorio.save(actividad);
    }

    @Override
    public void eliminarActividadporId(Integer idActividad) {

        this.actividadRepositorio.deleteById(idActividad);

    }
    public Actividad asignarElemento(Integer idActividad,Integer idElemento) {
        Actividad actividad = actividadRepositorio.findById(idActividad)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        Elemento elemento = elementoRepositorio.findById(idElemento)
                .orElseThrow(() -> new RuntimeException("Elemento no encontrado"));

        actividad.getElementos().add(elemento);
        //elemento.getActividades().add(actividad);
        return actividadRepositorio.save(actividad);
    }
    @Transactional
    public Set<Elemento> obtenerElementosDeActividad(Integer actividadId) {
        Actividad actividad = actividadRepositorio.findById(actividadId)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));
        return actividad.getElementos(); // Acceso seguro dentro de una transacción
    }
}

