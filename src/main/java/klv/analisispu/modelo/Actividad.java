package klv.analisispu.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Data
@Table(name = "actividad")
@AllArgsConstructor

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

    @Column(columnDefinition = "float default 1")
    float cantidad;

    @Column(length = 3)
    String unidad;

    Double precioUnitario;

    Double precioTotal;

    public Actividad() {
        this.cantidad = 1;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "actividad_elementos",
            joinColumns = @JoinColumn(name = "actividad_id"),
            inverseJoinColumns = @JoinColumn(name = "elemento_id"))
    private Set<Elemento> elementos= new HashSet<>();


    @NonNull
    public String getNombre() {
        return nombre;
    }

    public void setPrecioTotal() {

        this.precioTotal = this.cantidad * this.precioUnitario;
    }
    @Override
    public String toString() {

        return getNombre();
    }

}

