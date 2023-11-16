package co.edu.javeriana.as.personapp.model.request.profesion;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteProfesionRequest {
    @NotBlank
    private String id;
    @NotBlank
    private String database;
}
