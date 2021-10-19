package rest;

import entities.Carrera;
import entities.Estudiante;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import model.Genero;
import model.TipoOrdenamiento;
import repository.CarreraRepository;
import repository.EstudianteRepository;
import rest.request.EstudianteRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/estudiante")
public class EstudianteController {

	public EstudianteController(){
	}

	public void createEstudiantes(){
		if(EstudianteRepository.getInstance().getAllEstudiantes().isEmpty()){
			EstudianteRepository.getInstance().add(new Estudiante("Juan",65, Genero.MASCULINO,1,"Cordoba" ));
			EstudianteRepository.getInstance().add(new Estudiante("Carlo",26, Genero.MASCULINO,2,"Tandil" ));
			EstudianteRepository.getInstance().add(new Estudiante("Roberto",18, Genero.MASCULINO,3,"CABA" ));
			EstudianteRepository.getInstance().add(new Estudiante("Claudia",54, Genero.FEMENINO,4,"CABA" ));
			EstudianteRepository.getInstance().add(new Estudiante("Mercedes",32, Genero.FEMENINO,5,"Azul" ));
			EstudianteRepository.getInstance().add(new Estudiante("Estefania",16, Genero.FEMENINO,6,"Tandil" ));
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Crea un estudiante")
	public Response createEstudiante(@RequestBody EstudianteRequest e) {
		Estudiante estudiante = new Estudiante();
		estudiante.setNombre(e.getNombre());
		estudiante.setCiudad(e.getCiudad());
		estudiante.setEdad(e.getEdad());
		estudiante.setGenero(e.getGenero());
		estudiante.setNumDocumento(e.getNumDocumento());
		EstudianteRepository.getInstance().add(estudiante);
		return Response.status(201).entity(e).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Muestra todos los estudiantes")
	public List<Estudiante> getEstudiantes(){
		return EstudianteRepository.getInstance().getAllEstudiantes();
	}


	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Muestra todos los estudiantes")
	public Estudiante getById(@PathParam("id") Integer id) {
		return EstudianteRepository.getInstance().findById(id);
	}

	@GET
	@Path("/genero/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Muestra todos los estudiantes segun el genero")
	public List<Estudiante> getEstudiantesGenero(@PathParam("genero") Genero genero){
		return EstudianteRepository.getInstance().estudiantesGenero(genero);
	}

	@GET
	@Path("/ordenado/{TipoOrdenamiento}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Muestra todos los estudiantes segun el criterio de ordenamiento")
	public List<Estudiante> getEstudiantesTipoOrdenamiento(@PathParam("TipoOrdenamiento") TipoOrdenamiento tipoordenamiento) {
		return EstudianteRepository.getInstance().estudiantesOrdenados(tipoordenamiento);
	}

	@GET
	@Path("/carrera/{carreraId}/ciudad/{ciudad}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiResponse(description = "Muestra todos los estudiantes")
	public List<Estudiante> getById(@PathParam("carreraId") Integer carreraId, @PathParam("ciudad") String ciudad) {
		Carrera byId = CarreraRepository.getInstance().findById(carreraId);
		return EstudianteRepository.getInstance().getEstudiantesByCarreraCiudad(byId, ciudad);
	}

}
