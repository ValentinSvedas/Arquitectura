package repository;

import entities.Estudiante;

import java.util.List;

public interface EstudianteRepository {
    void add(Estudiante e);//Añadir estudiantes a la BD
    List<Estudiante> estudiantesOrdenados();//recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    Estudiante getEstudiante(int numLibreta);//recuperar un estudiante, en base a su número de libreta universitaria.
    List<Estudiante> estudiantesGenero(boolean g);//recuperar todos los estudiantes, en base a su género.
    List<Estudiante> estudiantesResidencia();//recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.

}
