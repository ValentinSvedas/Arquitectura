package repository;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import utils.EntMF;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class EstudianteCarreraRepository extends AbstractRepository<EstudianteCarrera>{

    private static EstudianteCarreraRepository estudianteCarreraRepository;

    private EstudianteCarreraRepository() {
        super(EntMF.getEntityManager());
    }

    public static EstudianteCarreraRepository getInstance() {
        if(Objects.equals(estudianteCarreraRepository, null)) {
            estudianteCarreraRepository = new EstudianteCarreraRepository();
        }
        return estudianteCarreraRepository;
    }

    public void addEstudiante(Estudiante e, Carrera carrera) {
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(e,carrera,new Date(124,8,22) , new Date());
        add(estudianteCarrera);
    }

    public List<EstudianteCarrera> getAllEstudianteCarrera(){
            String query = "SELECT ec "
                    + "from EstudianteCarrera ec ";
            Query queryR = entityManager.createQuery(query);
            List<Object[]> queryResultList = queryR.getResultList();
            List<EstudianteCarrera> Ecarreras = new ArrayList<>();
            for (int i = 0; i < queryResultList.size(); i++) {
                EstudianteCarrera ec = new EstudianteCarrera(
                        (Estudiante) queryResultList.get(i)[1],
                        (Carrera) queryResultList.get(i)[2],
                        (Date) queryResultList.get(i)[3],
                        (Date) queryResultList.get(i)[4]
                );
                Ecarreras.add(ec);
            }
            return Ecarreras;
    }

    public boolean existsByCarreraEstudianteId(Estudiante estudiante, Carrera carrera) {
        String query = "SELECT ec "
                + "from EstudianteCarrera ec where ec.estudiante = ?1 AND ec.carrera = ?2";
        Query queryR = entityManager.createQuery(query)
                .setParameter(1, estudiante)
                .setParameter(2, carrera);

        List<Object[]> queryResultList = queryR.getResultList();
        return queryResultList.size() > 0;
    }
}

