package entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Genero;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int estudianteId;
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

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteCarrera> carreras;

}
