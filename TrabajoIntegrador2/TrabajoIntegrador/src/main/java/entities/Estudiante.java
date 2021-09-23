package entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int estudianteId;
    @Column
    private String nombre;
    @Column
    private int edad;
    @Column
    private boolean genero;
    @Column
    private int numDocumento;
    @Column
    private String ciudad;

//    @OneToMany
//    private List<EstudianteCarrera> carreras;

}
