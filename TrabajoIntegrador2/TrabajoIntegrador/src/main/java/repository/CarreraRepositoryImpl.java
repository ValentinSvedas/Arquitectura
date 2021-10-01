package repository;

import entities.Carrera;
import entities.Estudiante;
import model.Genero;
import model.dto.CarreraInscriptos;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarreraRepositoryImpl extends AbstractRepository<Carrera> implements CarreraRepository {

    public CarreraRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<CarreraInscriptos> getInscriptosPorCarrera() {
        String query2 = "SELECT c.carreraId, c.nombre, c.duracion, e.estudianteId, "
                + "e.ciudad, e.edad, e.genero, e.nombre, e.numDocumento from Carrera c "
                + "JOIN c.estudianteCarrera ec "
                + "JOIN ec.estudiante e "
                + "ORDER BY c.carreraId";
        Query nativeQuery = entityManager.createQuery(query2);
        List<Object[]> nativeQueryResultList = nativeQuery.getResultList();
        List<CarreraInscriptos> carreras = new ArrayList<>();
        int currentCarreraId = -1;
        CarreraInscriptos carreraInscriptos = new CarreraInscriptos();
        for (int i = 0; i < nativeQueryResultList.size(); i++) {
            int newCarreraId = (Integer) nativeQueryResultList.get(i)[0];

            if(currentCarreraId != newCarreraId) {
                currentCarreraId = newCarreraId;
                carreraInscriptos = new CarreraInscriptos();
                Carrera carrera = new Carrera(
                        currentCarreraId,
                        (String) nativeQueryResultList.get(i)[1],
                        (Integer) nativeQueryResultList.get(i)[2]
                );
                carreraInscriptos.setCarrera(carrera);
                carreras.add(carreraInscriptos);
            }
            Estudiante estudiante = new Estudiante(
                    (Integer) nativeQueryResultList.get(i)[3],
                    (String) nativeQueryResultList.get(i)[4],
                    (Integer) nativeQueryResultList.get(i)[5],
                    (Genero) nativeQueryResultList.get(i)[6],
                    (String) nativeQueryResultList.get(i)[7],
                    (Integer) nativeQueryResultList.get(i)[8]
            );
            carreraInscriptos.addEstudiante(estudiante);
        }

        Comparator<CarreraInscriptos> comparator = Comparator.comparingInt(carrera -> carrera.getInscriptos().size());
        carreras.sort(comparator.reversed());
        return carreras;
    }
}
