package klv.analisispu.controlador;


import klv.analisispu.excepcion.RecursoNoEncontradoExcepcion;
import klv.analisispu.modelo.Actividad;
import klv.analisispu.modelo.Elemento;
import klv.analisispu.servicio.ActividadServicio;
import klv.analisispu.servicio.ElementoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

//localhost:8080/analisispu
@RequestMapping("analisispu")

@CrossOrigin(value = "http://localhost:4200/")

public class ActividadControlador {

    private static final Logger logger =
            LoggerFactory.getLogger(ActividadControlador.class);



    @Autowired
    private ActividadServicio actividadServicio;

    @Autowired
    private ElementoServicio elementoServicio;

    //http://localhost:8080/analisispu-app/actividades
    @GetMapping("/actividades")
    public List<Actividad> getActividades() {
        List<Actividad> actividades = this.actividadServicio.listarActividades();
        logger.info("Actividades registradas");
        actividades.forEach((actividad -> logger.info(actividad.getNombre())));

        return actividades;

    }

    @PostMapping("/actividades")
    public Actividad agregarActividad(@RequestBody Actividad actividad) {

        if (actividad.getElementos() != null) {
            actividad.getElementos().forEach(elemento -> {
                if (!elementoServicio.existeById(elemento.getIdElemento())) {
                    elementoServicio.guardarElemento(elemento);
                }
            });
        }
        actividad.setPrecioTotal();
        logger.info("Actividad agregada: " + actividad);

        return this.actividadServicio.guardarActividad(actividad);
    }

    @PostMapping("/actividades/{actividadId}/elementos/{elementoId}")
    public ResponseEntity<Actividad> asignarElemento(@PathVariable Integer actividadId,
                                     @PathVariable Integer elementoId) {
        Actividad actividad = actividadServicio.asignarElemento(actividadId, elementoId);

        return ResponseEntity.ok(actividad);
    }


    @GetMapping("/actividades/{id}")
    public ResponseEntity<Actividad> getActividadPorId(
            @PathVariable int id) {
        Actividad actividad =
                this.actividadServicio.buscarActividadporId(id);
        if(actividad != null)
            return ResponseEntity.ok(actividad);
        else
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " +id);
    }
    @PutMapping("/actividades/{id}")
    public ResponseEntity<Actividad> actualizarActividad(
            @PathVariable int id, @RequestBody Actividad actividadRecibida) {
        Actividad actividad = this.actividadServicio.buscarActividadporId(id);
        if(actividad != null) {
            actividad.setCodigo(actividadRecibida.getCodigo());
            actividad.setNombre(actividadRecibida.getNombre());
            actividad.setUnidad(actividadRecibida.getUnidad());
            actividad.setCantidad(actividadRecibida.getCantidad());
            actividad.setPrecioUnitario(actividadRecibida.getPrecioUnitario());
            //actividad.setPrecioTotal();
            this.actividadServicio.guardarActividad(actividad);
            return ResponseEntity.ok(actividad);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " +id);
        }
    }
    @DeleteMapping("/actividades/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarActividad(
            @PathVariable int id) {
        Actividad actividad = this.actividadServicio.buscarActividadporId(id);
        if(actividad != null) {
            this.actividadServicio.eliminarActividadporId(id);
            Map<String, Boolean> respuesta= new HashMap<>();
             respuesta.put("Eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " +id);
        }
    }


}
