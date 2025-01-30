package klv.apu.repositorio;

import klv.apu.modelo.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ActividadRepositorio extends JpaRepository<Actividad, Integer> {

}
