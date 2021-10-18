package rest;

import entities.Estudiante;
import repository.EstudianteRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/estudiante")
public class EstudianteREST {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEstudiante(Estudiante e) {
		EstudianteRepository.getInstance().add(e);
		return Response.status(201).entity(e).build();
	}

	@GET
	public String checkIfWorks() {
		return "Works";
	}

}
