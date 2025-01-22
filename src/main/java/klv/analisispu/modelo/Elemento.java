package klv.analisispu.modelo;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Entity
@Data
@Table(name="elemento")
@AllArgsConstructor


public class Elemento{

    /** Clave primaria */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idElemento;

    @Column(unique = true, length = 5)
    public String codigo;

    public String nombre;

    public String descripcion;



    @Column(length = 10)
    public String unidad;

    @Column(columnDefinition = "float default 1")
    public float cantidad;

    public Double precioUnitario;

    public Double precioTotal;

    public Elemento() {
        this.cantidad = 1;
    }

   /*  @ManyToMany(fetch = FetchType.LAZY)
    *private Set<Actividad> actividades;
*/
    public void setPrecioTotal() {

        this.precioTotal =this.cantidad * this.precioUnitario;
    }

    @Override
    public String toString() {

        return getNombre();
    }

}
