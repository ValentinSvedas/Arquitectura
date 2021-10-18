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
        String query = "SELECT e "
              + "from Estudiante e "
              + "order by e.estudianteId ";

        if(tipoOrdenamiento == TipoOrdenamiento.ASCENDENTE) {
            query += "ASC";
        } else if (tipoOrdenamiento == TipoOrdenamiento.DESCENDENTE) {
            query += "DESC";
        }
        Query nativeQuery = entityManager.createQuery(query);
        List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
        return getEstudiantes(nativeQueryResultList);
    }
    
    @Override
    public Estudiante getEstudiante(int numLibreta) {
        return entityManager.find(Estudiante.class,numLibreta);
    }

    @Override
    public List<Estudiante> estudiantesGenero(Genero g) {
        String query = "SELECT e "
              + "from Estudiante e "
              + "where e.genero = ?1 ";
        Query nativeQuery = entityManager.createQuery(query).setParameter(1, g);
        List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();
        return getEstudiantes(nativeQueryResultList);
    }

    @Override
    public List<Estudiante> estudiantesResidencia(Carrera c, String ciudad) {
        int id = c.getCarreraId();

        Query nativeQuery = entityManager.createQuery("SELECT e"
              + " from Estudiante e "
              + "JOIN EstudianteCarrera ec on ec.estudiante = e "
              + "JOIN ec.carrera c on c.carreraId = ?1 "
              + "WHERE e.ciudad like ?2").setParameter(1,id).setParameter(2,ciudad);

        List<Estudiante> nativeQueryResultList = nativeQuery.getResultList();

        return getEstudiantes(nativeQueryResultList);
    }

    private List<Estudiante> getEstudiantes(List<Estudiante> nativeQueryResultList) {
        List<Estudiante> estudiantes = new ArrayList<>();
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
