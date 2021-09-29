package repository;

import entities.Carrera;
import entities.Estudiante;
import model.Genero;
import model.TipoOrdenamiento;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositoryImpl extends AbstractRepository<Estudiante> implements EstudianteRepository {

    public EstudianteRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Estudiante> estudiantesOrdenados(TipoOrdenamiento tipoOrdenamiento) {
        List<Estudiante> estudiantes = new ArrayList<>();
        List<Estudiante> nativeQueryResultList = new ArrayList<>();
        if (tipoOrdenamiento == tipoOrdenamiento.ASCENDENTE){
        String query = "SELECT e"
                + " from Estudiante e "
                +"order by e.estudianteId ASC ";
       Query nativeQuery = entityManager.createQuery(query);
        nativeQueryResultList = nativeQuery.getResultList();
        
        }else if (tipoOrdenamiento == tipoOrdenamiento.DESCENDENTE){
            String query = "SELECT e"
                    + " from Estudiante e "
                    +"order by e.estudianteId DESC ";
            Query nativeQuery = entityManager.createQuery(query);
            nativeQueryResultList = nativeQuery.getResultList();

        }
        return getEstudiantes(estudiantes, nativeQueryResultList);
    }

    @Override
    public Estudiante getEstudiante(int numLibreta) {
    Estudiante e  = entityManager.find(Estudiante.class,numLibreta);
        return e;
    }

    @Override
    public List<Estudiante> estudiantesGenero(Genero g) {
        int genero;
        if (g.equals(Genero.FEMENINO)){
             genero = 1;
        }else{
            genero = 0;
        }
        List<Estudiante> estudiantes = new ArrayList<>();
        String query = "SELECT e"
                + " from Estudiante e "
                +"where e.genero = genero ";
        Query nativeQuery = entityManager.createQuery(query);
        List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
        return getEstudiantes(estudiantes, nativeQueryResultList);
    }

    @Override
    public List<Estudiante> estudiantesResidencia(Carrera c, String ciudad) {
        int id = c.getCarreraId();
        List<Estudiante> estudiantes = new ArrayList<>();
        Query nativeQuery = entityManager.createQuery("SELECT e"
                + " from Estudiante e "
                + "JOIN EstudianteCarrera ec on ec.estudiante = e "
                + "JOIN ec.carrera c on c.carreraId = :id "
                + "WHERE e.ciudad like :ciudad");

        List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();

        return getEstudiantes(estudiantes, nativeQueryResultList);
    }

    private List<Estudiante> getEstudiantes(List<Estudiante> estudiantes, List<Estudiante> nativeQueryResultList) {
        for (int i = 0; i < nativeQueryResultList.size(); i++) {
            Estudiante estudiante = new Estudiante(
                    nativeQueryResultList.get(i).getEstudianteId(),
                    nativeQueryResultList.get(i).getCiudad(),
                    nativeQueryResultList.get(i).getEdad(),
                    nativeQueryResultList.get(i).getGenero(),
                    nativeQueryResultList.get(i).getNombre(),
                    nativeQueryResultList.get(i).getNumDocumento()
            );
            estudiantes.add(estudiante);
            //String nombre, int edad, Genero genero, int numDocumento, String ciudad
        }
        return estudiantes;
    }
}
