package TrabajoIntegrador3.rest;

import entities.Estudiante;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.EstudianteRepository;

@Path("/Estudiantes")
public class EstudianteREST {
	
	@POST @Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEstudiante(Estudiante e) {
		EstudianteRepository.getInstance().add(e);
		return Response.status(201).entity(e).build();
	}

}
