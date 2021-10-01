package repository;

import java.util.List;

import entities.Carrera;
import entities.Estudiante;
import model.dto.ReporteCarreras;

public interface EstudianteCarreraRepository {
    /**
     * Matricular un estudiante en una carrera
     */
    void addEstudiante(Estudiante e, Carrera carrera);

    /**
     * Generar un reporte de las carreras, que para cada carrera incluya información de los
     * inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
     * los años de manera cronológica.
     */
    List<ReporteCarreras> getReporteCarreras();
}
