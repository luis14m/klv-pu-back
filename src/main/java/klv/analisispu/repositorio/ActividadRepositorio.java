package klv.analisispu.repositorio;

import klv.analisispu.modelo.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActividadRepositorio extends JpaRepository<Actividad, Integer> {

}
