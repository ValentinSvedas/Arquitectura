package rest;


import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import repository.CarreraRepository;
import repository.EstudianteCarreraRepository;
import repository.EstudianteRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/estudiantecarrera")
public class EstudianteCarreraREST {

    public EstudianteCarreraREST(){
        addEstudiantesCarrera();
    }

    public void addEstudiantesCarrera(){
      if (EstudianteCarreraRepository.getInstance().getAllEstudianteCarrera().isEmpty()){
            Estudiante e1 = EstudianteRepository.getInstance().findById(1);
            Estudiante e2 = EstudianteRepository.getInstance().findById(2);
            Estudiante e3 = EstudianteRepository.getInstance().findById(3);
            Estudiante e4 = EstudianteRepository.getInstance().findById(4);
            Estudiante e5 = EstudianteRepository.getInstance().findById(5);
            Estudiante e6 = EstudianteRepository.getInstance().findById(6);
            Carrera c1 = CarreraRepository.getInstance().findById(1);
            Carrera c2 = CarreraRepository.getInstance().findById(2);
            EstudianteCarreraRepository.getInstance().addEstudiante(e1,c1);
            EstudianteCarreraRepository.getInstance().addEstudiante(e4,c1);
            EstudianteCarreraRepository.getInstance().addEstudiante(e6,c1);
            EstudianteCarreraRepository.getInstance().addEstudiante(e2,c2);
            EstudianteCarreraRepository.getInstance().addEstudiante(e3,c2);
            EstudianteCarreraRepository.getInstance().addEstudiante(e5,c2);
       }
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(description = "Muestra todos los estudiantes")
    public List<EstudianteCarrera> getEstudianteCarrera(){
        return EstudianteCarreraRepository.getInstance().getAllEstudianteCarrera();
    }

}
