package co.edu.javeriana.as.personapp.terminal.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EstudioModelCli {
    private PersonaModelCli persona;
    private ProfesionModelCli profesion;
    private LocalDate graduationDate;
    private String universityName;
}
