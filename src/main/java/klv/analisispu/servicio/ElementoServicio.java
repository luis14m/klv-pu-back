package klv.analisispu.servicio;

import klv.analisispu.modelo.Elemento;
import klv.analisispu.repositorio.ElementoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementoServicio implements IElementoServervicio {

    @Autowired
    private ElementoRepositorio elementoRepositorio;

    @Override
    public List<Elemento> listarElementos() {

        return this.elementoRepositorio.findAll();
    }

    @Override
    public Elemento buscarElementoporId(Integer idElemento) {
        return this.elementoRepositorio.findById(idElemento).
                orElseThrow(() -> new RuntimeException("Elemento no encontrado"));
    }

    @Override
    public Elemento guardarElemento(Elemento elemento) {

        return this.elementoRepositorio.save(elemento);
    }

    @Override
    public void eliminarElementoporId(Integer idElemento) {

        this.elementoRepositorio.deleteById(idElemento);
    }

    @Override
    public boolean existeById(Integer idElemento) {

        return this.elementoRepositorio.existsById(idElemento);
    }
}
