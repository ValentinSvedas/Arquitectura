package main;

import entities.Carrera;
import entities.Estudiante;
import model.Genero;
import model.dto.CarreraInscriptos;
import repository.CarreraRepositoryImpl;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String args[]){
/*
1) Considere el diseño de un registro de estudiantes, con la siguiente información: nombres,
apellido, edad, género, número de documento, ciudad de residencia, número de libreta
universitaria, carrera(s) en la que está inscripto, antigüedad en cada una de esas carreras, y
si se graduó o no. Diseñar el diagrama de objetos y el diagrama DER correspondiente.

2) Implementar consultas para:
E) dar de alta un estudiante
EC) matricular un estudiante en una carrera
E) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
E) recuperar un estudiante, en base a su número de libreta universitaria.
E) recuperar todos los estudiantes, en base a su género.
C) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
E) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.

3) Generar un reporte de las carreras, que para cada carrera incluya información de los
inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
los años de manera cronológica.
 */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practico2");

        EstudianteRepositoryImpl estudianteRepository = new EstudianteRepositoryImpl(emf.createEntityManager());
        Estudiante e = new Estudiante("nombre", 10, Genero.FEMENINO, 10, "city");
//        estudianteRepository.add(e);


        CarreraRepositoryImpl carreraRepository = new CarreraRepositoryImpl(emf.createEntityManager());
        Carrera carrera = new Carrera();
//        carreraRepository.add(carrera);

        List<CarreraInscriptos> inscriptosPorCarrera = carreraRepository.getInscriptosPorCarrera();



//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
//        EntityManager em = emf.createEntityManager();
//
//        em.close();
//        emf.close();

    }
}
