package co.edu.javeriana.as.personapp.model.request.estudio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class GetEstudioRequest {
    @NotNull
    private Integer personaId;
    @NotNull
    private Integer professionId;
    @NotBlank
    private String db;
}
