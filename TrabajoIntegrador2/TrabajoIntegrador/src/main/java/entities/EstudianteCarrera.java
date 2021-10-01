package entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import utils.EstudianteCarreraPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteCarrera {

   @EmbeddedId
   private EstudianteCarreraPK id;

   @MapsId("estudianteId")
   @JoinColumn(insertable = false, updatable = false)
   @ManyToOne(cascade = CascadeType.PERSIST)
   private Estudiante estudiante;

   @MapsId("carreraId")
   @JoinColumn(insertable = false, updatable = false)
   @ManyToOne(cascade = CascadeType.PERSIST)
   private Carrera carrera;

   @Column
   private Date inscripcion;

   @Column(nullable = true)
   private Date graduacion;



}
