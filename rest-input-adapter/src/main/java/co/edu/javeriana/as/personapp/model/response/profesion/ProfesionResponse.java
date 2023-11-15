package co.edu.javeriana.as.personapp.model.response.profesion;

import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionResponse extends ProfesionRequest {
    private Integer id;
    private String status;
}
