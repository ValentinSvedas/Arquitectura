package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import model.dto.ReporteCarreras;
import utils.EstudianteCarreraPK;

import javax.persistence.EntityManager;

public class EstudianteCarreraRepositoryImpl extends AbstractRepository<EstudianteCarrera> implements EstudianteCarreraRepository {

    public EstudianteCarreraRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void addEstudiante(Estudiante e, Carrera carrera) {
        EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK();
        estudianteCarreraPK.setEstudianteId(e.getEstudianteId());
        estudianteCarreraPK.setCarreraId(carrera.getCarreraId());

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudianteCarreraPK,e,carrera, null, null);

        add(estudianteCarrera);
    }

    @Override
    public List<ReporteCarreras> getReporteCarreras() {
        List<ReporteCarreras> reporteCarreras = new ArrayList<>();
        String query = "SELECT * from Carrera c "
              + "JOIN c.estudianteCarrera ec "
              + "JOIN ec.estudiante e ";

        return reporteCarreras;
    }
}
