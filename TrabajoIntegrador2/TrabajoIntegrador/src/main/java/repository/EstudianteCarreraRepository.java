package repository;

import entities.Carrera;
import entities.Estudiante;

public interface EstudianteCarreraRepository {
    /**
     * Matricular un estudiante en una carrera
     */
    void addEstudiante(Estudiante e, Carrera carrera);
}
