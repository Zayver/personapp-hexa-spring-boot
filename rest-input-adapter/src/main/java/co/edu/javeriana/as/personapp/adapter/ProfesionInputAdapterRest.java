package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.configuration.ProfessionOutputPortFactory;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import co.edu.javeriana.as.personapp.model.request.profesion.DeleteProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.GetProfesionRequest;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import lombok.val;
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
    public ProfesionResponse getOne(GetProfesionRequest request){
        try {
            val res = professionOutputPortFactory.getProfessionOutputPort(request.getDatabase())
                    .findOne(request.getId());
            return professionMapperRest.fromDomainToAdapter(res);
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ProfesionResponse create(ProfesionRequest request){
        try{
            val res = professionOutputPortFactory.getProfessionOutputPort(request.getDatabase())
                    .create(professionMapperRest.fromAdapterToDomain(request));
            return professionMapperRest.fromDomainToAdapter(res);
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public ProfesionResponse edit(ProfesionRequest request){
        try{
            val res = professionOutputPortFactory.getProfessionOutputPort(request.getDatabase())
                    .edit(String.valueOf(request.getId()), professionMapperRest.fromAdapterToDomain(request));
            return professionMapperRest.fromDomainToAdapter(res);
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public Boolean drop(DeleteProfesionRequest request){
        try{
            return professionOutputPortFactory.getProfessionOutputPort(request.getDatabase())
                    .drop(request.getId());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
