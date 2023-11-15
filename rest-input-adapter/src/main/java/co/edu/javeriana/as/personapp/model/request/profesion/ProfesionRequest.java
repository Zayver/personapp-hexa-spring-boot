package co.edu.javeriana.as.personapp.model.request.profesion;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
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
    private String name;
    @NotBlank
    private String description;
    private List<Integer> studies;
}
