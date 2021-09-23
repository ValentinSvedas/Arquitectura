package entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    private int carreraId;
    @Column
    private String nombre;
    @Column
    private int duracion;

//   @OneToMany
//   private List<EstudianteCarrera> estudiantes;


}
