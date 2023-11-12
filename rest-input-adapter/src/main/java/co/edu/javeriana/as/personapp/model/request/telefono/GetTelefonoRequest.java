package co.edu.javeriana.as.personapp.model.request.telefono;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GetTelefonoRequest {
    @NotBlank
    private String Id;
    @NotBlank
    private String database;

}
