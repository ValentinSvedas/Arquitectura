package repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import model.Genero;
import model.dto.CarreraInscriptos;
import model.dto.ReporteCarreras;
import utils.EstudianteCarreraPK;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstudianteCarreraRepositoryImpl extends AbstractRepository<EstudianteCarrera> implements EstudianteCarreraRepository {

    public EstudianteCarreraRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void addEstudiante(Estudiante e, Carrera carrera) {
        EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK();
        estudianteCarreraPK.setEstudianteId(e.getEstudianteId());
        estudianteCarreraPK.setCarreraId(carrera.getCarreraId());

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera(e,carrera,new Date(124,8,22) , new Date());

        add(estudianteCarrera);
    }

    @Override
    public List<CarreraInscriptos> getReporteCarreras() {
        String query = "SELECT distinct c.carreraId,c.nombre,c.duracion, e,ec.inscripcion from Carrera c"
                + " inner JOIN c.estudianteCarrera ec "
                + "JOIN ec.estudiante e";
        Query nativeQuery = entityManager.createQuery(query);
        List<Object[]> queryResultList = nativeQuery.getResultList();
        List<ReporteCarreras> carreras = new ArrayList<>();
        int currentCarreraId = -1;
        ReporteCarreras reporteCarreras = new ReporteCarreras();
        for (int i=0; i<queryResultList.size();i++){
            int newCarreraId = (Integer) queryResultList.get(i)[0];

            if(currentCarreraId != newCarreraId) {
                currentCarreraId = newCarreraId;
                reporteCarreras = new ReporteCarreras();
                Carrera carrera = new Carrera(
                        currentCarreraId,
                        (String) queryResultList.get(i)[1],
                        (Integer) queryResultList.get(i)[2]
                );
                reporteCarreras.setCarrera(carrera);
                carreras.add(reporteCarreras);
            }
            Estudiante estudiante = new Estudiante(
                    (Integer) queryResultList.get(i)[3],
                    (String) queryResultList.get(i)[4],
                    (Integer)queryResultList.get(i)[5],
                    (Genero) queryResultList.get(i)[6],
                    (String) queryResultList.get(i)[7],
                    (Integer) queryResultList.get(i)[8]
            );
           // ReporteCarreras.addEstudiante(estudiante);

        }
        return null;

    }
    }

    /*
    Generar un reporte de las carreras, que para cada carrera incluya información de los
    inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    los años de manera cronológica.
     */

