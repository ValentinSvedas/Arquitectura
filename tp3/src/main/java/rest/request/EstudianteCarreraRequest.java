package rest.request;

import lombok.Data;

@Data
public class EstudianteCarreraRequest {
    private Integer estudianteId;
    private Integer carreraId;
}
