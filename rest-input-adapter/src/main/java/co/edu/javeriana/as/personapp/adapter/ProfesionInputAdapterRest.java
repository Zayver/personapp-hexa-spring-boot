package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.configuration.ProfessionOutputPortFactory;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@Adapter
public class ProfesionInputAdapterRest {
    @Autowired
    private ProfessionOutputPortFactory professionOutputPortFactory;

    @Autowired
    private ProfessionMapperRest professionMapperRest;

    public Iterable<ProfesionResponse> getAll(String db){
        try {
            return this.professionOutputPortFactory.getProfessionOutputPort(db)
                    .findAll().stream().map(professionMapperRest::fromDomainToAdapter)
                    .collect(Collectors.toSet());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
