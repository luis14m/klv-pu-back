package klv.apu.modelo;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Data
@Table(name="elementos")
@AllArgsConstructor
@NoArgsConstructor

public class Elemento{

    /** Clave primaria */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer idElemento;

    @Column(unique = true, length = 5, nullable = false)
    public String codigo;

    public String nombre;

    @Column(length = 8)
    public String unidad;

    public String tipo;

    public Double costoUnitario;


   /*  @ManyToMany(fetch = FetchType.LAZY)
    *private Set<Actividad> actividades;
*/

}
