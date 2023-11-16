package co.edu.javeriana.as.personapp.model.request.profesion;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfesionRequest {
    @NotBlank
    String id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @NotBlank
    private String database;
}
