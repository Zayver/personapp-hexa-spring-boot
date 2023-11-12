package co.edu.javeriana.as.personapp.model.request.telefono;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {
    @NotBlank
    private String number;
    @NotBlank
    private String company;
    @NotNull
    private Integer idOwner;
    @NotBlank
    private String database;
}
