package repository;

import entities.Estudiante;
import model.TipoOrdenamiento;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EstudianteRepositoryImpl implements EstudianteRepository {

    @Override
    public void add(Estudiante e) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practico2");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
        emf.close();
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
