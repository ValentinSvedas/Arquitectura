package rest;

import entities.Estudiante;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import repository.EstudianteRepository;
import rest.request.EstudianteRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estudiante")
public class EstudianteREST {


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Crea un estudiante")
	public Response createEstudiante(EstudianteRequest e) {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre(e.getNombre());
		EstudianteRepository.getInstance().add(estudiante);
		return Response.status(201).entity(e).build();
	}

	@GET
	public String checkIfWorks() {
		return "Works";
	}

}
