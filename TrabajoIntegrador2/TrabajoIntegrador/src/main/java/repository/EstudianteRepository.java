package repository;

import entities.Estudiante;
import model.TipoOrdenamiento;

import java.util.List;

public interface EstudianteRepository {
    /**
     * Agrega estudiante
     */
    void add(Estudiante e);//Añadir estudiantes a la BD

    /**
     * recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
     */
    List<Estudiante> estudiantesOrdenados(TipoOrdenamiento tipoOrdenamiento);

    /**
     * Recuperar un estudiante, en base a su número de libreta universitaria.
     */
    Estudiante getEstudiante(int numLibreta);

    /**
     * Recuperar todos los estudiantes, en base a su género.
     */
    List<Estudiante> estudiantesGenero(boolean g);

    /**
     * Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
     */
    List<Estudiante> estudiantesResidencia();

}
