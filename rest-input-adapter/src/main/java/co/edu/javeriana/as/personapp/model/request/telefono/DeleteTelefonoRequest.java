package co.edu.javeriana.as.personapp.model.request.telefono;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteTelefonoRequest {
    @NotBlank
    private String id;
    @NotBlank
    private  String database;
}
