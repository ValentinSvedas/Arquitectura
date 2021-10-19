package entities;

import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.Genero;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="estudianteId",updatable=false,nullable=false)
    private Integer estudianteId;
    @Column
    private String nombre;
    @Column
    private int edad;
    @Column
    private Genero genero;
    @Column
    private int numDocumento;
    @Column
    private String ciudad;

    @OneToMany(mappedBy = "estudiante",cascade = CascadeType.PERSIST)
    private List<EstudianteCarrera> carreras;

    public Estudiante(String nombre, int edad, Genero genero, int numDocumento, String ciudad) {
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.numDocumento = numDocumento;
        this.ciudad = ciudad;
    }

    public Estudiante(int estudianteId, String ciudad, int edad, Genero genero, String nombre, int numDocumento) {
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.numDocumento = numDocumento;
        this.ciudad = ciudad;
    }
}
