package klv.analisispu.repositorio;

import klv.analisispu.modelo.Elemento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementoRepositorio extends JpaRepository<Elemento, Integer> {
}
