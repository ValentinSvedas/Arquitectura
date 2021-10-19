package model.dto;

import entities.Carrera;
import entities.Estudiante;
import lombok.Data;

import java.util.List;

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
