package klv.analisispu.servicio;

import klv.analisispu.modelo.Elemento;

import java.util.List;

public interface IElementoServervicio {

    public List<Elemento> listarElementos();

    public Elemento buscarElementoporId(Integer idElemento);

    public Elemento guardarElemento(Elemento Elemento);

    public void eliminarElementoporId(Integer idElemento);

    public boolean existeById(Integer idElemento);



}
