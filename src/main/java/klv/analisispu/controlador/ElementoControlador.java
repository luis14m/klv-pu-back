package klv.analisispu.controlador;

import klv.analisispu.excepcion.RecursoNoEncontradoExcepcion;
import klv.analisispu.modelo.Elemento;
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
@RequestMapping("analisispu")
@CrossOrigin(value = "http://localhost:4200/")
public class ElementoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ElementoControlador.class);

    @Autowired
    private ElementoServicio elementoServicio;

    @GetMapping("/elementos")
    public List<Elemento> getElementos() {
        List<Elemento> elementos = this.elementoServicio.listarElementos();
        logger.info("Elementos registrados");
        elementos.forEach((elemento -> logger.info(elemento.getNombre())));
        return elementos;
    }

    @PostMapping("/elementos")
    public Elemento agregarElemento(@RequestBody Elemento elemento) {
        logger.info("Elemento agregar: " + elemento);
        elemento.setPrecioTotal();
        return this.elementoServicio.guardarElemento(elemento);
    }

    @GetMapping("/elementos/{id}")
    public ResponseEntity<Elemento> getElementoPorId(@PathVariable int id) {
        Elemento elemento = this.elementoServicio.buscarElementoporId(id);
        if (elemento != null)
            return ResponseEntity.ok(elemento);
        else
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
    }

    @PutMapping("elementos/{id}")
    public ResponseEntity<Elemento> actualizarElemento(@PathVariable int id,
                                                       @RequestBody Elemento elementoRecibido) {
        Elemento elemento = this.getElementoPorId(id).getBody();
        if (elemento != null) {
            elemento.setCodigo(elementoRecibido.getCodigo());
            elemento.setNombre(elementoRecibido.getNombre());
            elemento.setUnidad(elementoRecibido.getUnidad());
            elemento.setCantidad(elementoRecibido.getCantidad());
            elemento.setPrecioUnitario(elementoRecibido.getPrecioUnitario());
            elemento.setPrecioTotal();
            this.elementoServicio.guardarElemento(elemento);
            logger.info("Elemento actualizado: " + elemento);
            return ResponseEntity.ok(elemento);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
    }

    @DeleteMapping("elementos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarElemento(@PathVariable int id) {
        Elemento elemento = this.elementoServicio.buscarElementoporId(id);
        if (elemento != null) {
            this.elementoServicio.eliminarElementoporId(id);
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
        } else {
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        }
    }
}