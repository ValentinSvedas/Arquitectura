package repository;

import entities.Carrera;
import entities.Estudiante;

import java.util.List;

public interface CarreraRepository {
    void addEstudiante(Estudiante e);//matricular un estudiante en una carrera
    List<Carrera> carrerasOrdenadaEI();//Lista de carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
}
