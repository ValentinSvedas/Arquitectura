package model.dto;

import entities.Carrera;
import entities.Estudiante;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarreraInscriptos {
    private Carrera carrera;
    private List<Estudiante> inscriptos = new ArrayList<>();

    public void addEstudiante(Estudiante e) {
        inscriptos.add(e);
    }
}
