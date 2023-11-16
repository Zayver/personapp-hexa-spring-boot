package co.edu.javeriana.as.personapp.model.request.estudio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class EstudioRequest {
    @NotNull
    private Integer personId;
    @NotNull
    private Integer professionId;
    @NotNull
    private LocalDate graduationDate;
    @NotBlank
    private String universityName;
    @NotBlank
    private String db;
}
