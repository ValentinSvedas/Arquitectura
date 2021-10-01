package main;

import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import model.Genero;
import model.TipoOrdenamiento;
import model.dto.CarreraInscriptos;
import repository.CarreraRepositoryImpl;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        CarreraRepositoryImpl carreraRepository = new CarreraRepositoryImpl(emf.createEntityManager());
        Estudiante e = new Estudiante("pepe", 10, Genero.FEMENINO, 10, "city");
        Carrera c = new Carrera("abogacia",10);


        estudianteRepository.add(e);
        carreraRepository.add(c);

        EstudianteCarreraRepositoryImpl estudianteCarreraRepository = new EstudianteCarreraRepositoryImpl(emf.createEntityManager());

        estudianteCarreraRepository.addEstudiante(estudianteRepository.getEstudiante(1),carreraRepository.getCarreraById(1));

       //System.out.println(estudianteRepository.estudiantesOrdenados(TipoOrdenamiento.DESCENDENTE));
        //System.out.println(estudianteRepository.getEstudiante(1));

      //  Carrera carrera = new Carrera();



//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Example");
//        EntityManager em = emf.createEntityManager();
//

        estudianteRepository.close();


    }
}
