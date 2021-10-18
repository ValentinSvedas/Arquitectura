package rest;

import entities.Estudiante;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.EstudianteRepository;

@Path("/estudiante")
public class EstudianteREST {
	
	@GET @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEstudiante(Estudiante e) {
		EstudianteRepository.getInstance().add(e);
		return Response.status(201).entity(e).build();
	}

}
