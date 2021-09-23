package entities;

import lombok.Data;
import utils.EstudianteCarreraPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class EstudianteCarrera {
    @EmbeddedId
    private EstudianteCarreraPK id;

//    @MapsId("estudianteId")
//    @JoinColumn(insertable = false, updatable = false)
//    private Estudiante estudiante;
//
//    @MapsId("carreraId")
//    @JoinColumn(insertable = false, updatable = false)
//    private Carrera carrera;

    @Column
    private Date inscripcion;
    @Column(nullable = true)
    private Date graduacion;



}
