package co.edu.javeriana.as.personapp.model.response.telefono;

import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@EqualsAndHashCode(callSuper=true)
public class TelefonoResponse extends TelefonoRequest {
    private String status;
}
