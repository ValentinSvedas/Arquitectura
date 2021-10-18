package model.dto;

import entities.Estudiante;
import lombok.Data;

import java.util.List;

@Data
public class EgresadosPorAnio {
   private int anio;
   private List<Estudiante> egresados;
}
