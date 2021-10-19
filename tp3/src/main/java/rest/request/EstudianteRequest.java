package rest.request;

import lombok.Data;
import model.Genero;

@Data
public class EstudianteRequest {
    private String nombre;
    private int edad;
    private Genero genero;
    private int numDocumento;
    private String ciudad;
}
