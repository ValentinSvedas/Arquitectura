package utils;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class EstudianteCarreraPK implements Serializable {

    private Integer estudianteId;

    private Integer carreraId;

}
