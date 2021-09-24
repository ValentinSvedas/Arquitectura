package repository;

import entities.Estudiante;
import model.TipoOrdenamiento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EstudianteRepositoryImpl extends AbstractRepository<Estudiante> implements EstudianteRepository {

    public EstudianteRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Estudiante> estudiantesOrdenados(TipoOrdenamiento tipoOrdenamiento) {
        return null;
    }

    @Override
    public Estudiante getEstudiante(int numLibreta) {
        return null;
    }

    @Override
    public List<Estudiante> estudiantesGenero(boolean g) {
        return null;
    }

    @Override
    public List<Estudiante> estudiantesResidencia() {
        return null;
    }
}
