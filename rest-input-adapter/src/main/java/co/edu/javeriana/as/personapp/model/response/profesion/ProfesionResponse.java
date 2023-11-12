package co.edu.javeriana.as.personapp.model.response.profesion;

import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ProfesionResponse extends ProfesionRequest {
    private Integer id;
    private String status;
}
