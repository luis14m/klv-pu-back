package klv.apu.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "actividades")
@AllArgsConstructor
@NoArgsConstructor

public class Actividad {

    /**
     * Clave primaria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idActividad;

    @Column(unique = true, length = 5, nullable = false)
    String codigo;

    @Column(nullable = false)
    String nombre;

    @Column(length = 3)
    String unidad;

    @Column
    String descripcion;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "actividad_elementos",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id"))
    private Set<Elemento> elementos= new HashSet<>();

}

