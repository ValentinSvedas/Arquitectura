package entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carreraId",updatable=false,nullable=false)
    private Integer carreraId;
    @Column
    private String nombre;
    @Column
    private int duracion;

    @OneToMany(mappedBy = "carrera",cascade = CascadeType.PERSIST)
    private List<EstudianteCarrera> estudianteCarrera;

    public Carrera(String nombre, int duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
    }

    public Carrera(int carreraId, String nombre, int duracion) {
        this.carreraId = carreraId;
        this.nombre = nombre;
        this.duracion = duracion;
    }
}
