package repository;

import entities.Carrera;
import entities.Estudiante;
import model.dto.CarreraInscriptos;

import java.util.List;

public interface CarreraRepository {

    /**
     * Lista de carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
     * @return lista de carreras
     */
    List<CarreraInscriptos> getInscriptosPorCarrera();
}
