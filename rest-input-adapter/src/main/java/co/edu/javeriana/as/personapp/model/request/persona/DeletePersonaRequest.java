package co.edu.javeriana.as.personapp.model.request.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class DeletePersonaRequest {
    @NotNull
    private Integer id;
    @NotBlank(message = "Database must not be black")
    private String database;
}
