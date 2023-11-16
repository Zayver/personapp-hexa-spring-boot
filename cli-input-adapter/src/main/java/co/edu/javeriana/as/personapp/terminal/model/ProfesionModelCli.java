package co.edu.javeriana.as.personapp.terminal.model;

import co.edu.javeriana.as.personapp.domain.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfesionModelCli {
    private Integer identification;
    private String nombre;
    private String descripcion;

    public ProfesionModelCli(Profession profession) {
        this.identification = profession.getId();
        this.nombre = profession.getName();
        this.descripcion = profession.getDescription();
    }
}
