package rest;


import entities.Carrera;
import entities.Estudiante;
import entities.EstudianteCarrera;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import repository.CarreraRepository;
import repository.EstudianteCarreraRepository;
import repository.EstudianteRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Path("/estudiantecarrera")
public class EstudianteCarreraController {

    public EstudianteCarreraController(){
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
    public List<EstudianteCarrera> getEstudianteCarrera() {
        return EstudianteCarreraRepository.getInstance().getAllEstudianteCarrera();
    }

    @POST
    @Path("/carrera/{carreraId}/estudiante/{estudianteId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(description = "Matricula un estudiante")
    public Response createEstudianteCarrera(@PathParam("carreraId") Integer carreraId, @PathParam("estudianteId") Integer estudianteId) {
        Estudiante estudiante = EstudianteRepository.getInstance().findById(estudianteId);
        Carrera carrera = CarreraRepository.getInstance().findById(carreraId);
        if(EstudianteCarreraRepository.getInstance().existsByCarreraEstudianteId(estudiante, carrera)) {
            EstudianteCarrera estudianteCarrera = new EstudianteCarrera(estudiante, carrera, null, new Date());
            EstudianteCarreraRepository.getInstance().add(estudianteCarrera);
            return Response.status(201).entity(estudianteCarrera).build();
        } else {
            return Response.status(404).entity("El usuario ya se encuentra matriculado").build();
        }
    }

}
