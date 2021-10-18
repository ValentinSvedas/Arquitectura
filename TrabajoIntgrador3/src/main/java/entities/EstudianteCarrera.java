package entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class EstudianteCarrera {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @JoinColumn(name ="estudianteId")
   @ManyToOne(cascade = CascadeType.MERGE)
   private Estudiante estudiante;

   @JoinColumn(name ="carreraId")
   @ManyToOne(cascade = CascadeType.MERGE)
   private Carrera carrera;

   @Column
   private Date inscripcion;

   @Column(nullable = true)
   private Date graduacion;


   public EstudianteCarrera(Estudiante e, Carrera carrera, Date graduacion, Date inscripcion) {
      this.estudiante = e;
      this.carrera = carrera;
      this.graduacion = graduacion;
      this.inscripcion = inscripcion;
   }
}
