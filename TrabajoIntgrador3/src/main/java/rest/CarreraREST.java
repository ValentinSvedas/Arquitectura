package rest;

import entities.Carrera;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import model.dto.CarreraInscriptos;
import repository.CarreraRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/carrera")
public class CarreraREST {

    public CarreraREST(){
        createCarreras();
    }

    public void createCarreras(){
        if(CarreraRepository.getInstance().getAllCarreras().isEmpty()){
          CarreraRepository.getInstance().add(new Carrera("Abogacia",5));
          CarreraRepository.getInstance().add(new Carrera("Programacion",2));
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(description = "Crea una carrera")
    public Response createCarrera(Carrera c) {
        CarreraRepository.getInstance().add(c);
        return Response.status(201).entity(c).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(description = "Muestra todos las carreras")
    public List<Carrera> getCarreras(){
        return CarreraRepository.getInstance().getAllCarreras();
    }

    @GET
    @Path("/{CarreraInscriptos}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponse(description = "Muestra todas la carreras con los incriptos")
    public List<CarreraInscriptos> getIncriptosCarrera(){
        return CarreraRepository.getInstance().getInscriptosPorCarrera();
    }

}
