package model.dto;

import java.util.List;

import entities.Carrera;
import entities.Estudiante;
import lombok.Data;

@Data
public class ReporteCarreras {
   Carrera carrera;
   List<Estudiante> estudiantesInscriptos;
   List<EgresadosPorAnio> egresadosPorAnioList;

   public void addEstudianteInscripto(Estudiante e) {
      estudiantesInscriptos.add(e);
   }
   public void addEstudianteEgresado(EgresadosPorAnio e) {
      egresadosPorAnioList.add(e);
   }
}
