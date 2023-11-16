package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.configuration.StudyOutputPortFactory;
import co.edu.javeriana.as.personapp.mapper.EstudioMapper;
import co.edu.javeriana.as.personapp.model.request.estudio.DeleteEstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
import co.edu.javeriana.as.personapp.model.request.estudio.GetEstudioRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Adapter
public class StudyInputAdapterRest {

    @Autowired
    private StudyOutputPortFactory studyOutputPortFactory;

    @Autowired
    private EstudioMapper estudioMapper;


    public List<EstudioResponse> historical(String db){
        try {
            return studyOutputPortFactory.getStudyOutputPort(db)
                    .findAll()
                    .stream().map(estudioMapper::fromDomainToAdapter)
                    .collect(Collectors.toList());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public EstudioResponse create(EstudioRequest request){
        try{
            val entity = estudioMapper.fromAdapterToDomain(request);
            val res = studyOutputPortFactory.getStudyOutputPort(request.getDb())
                    .create(entity);
            return estudioMapper.fromDomainToAdapter(res);
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public EstudioResponse edit(EstudioRequest request){
        try{
            val entity = estudioMapper.fromAdapterToDomain(request);
            val res = studyOutputPortFactory.getStudyOutputPort(request.getDb())
                    .edit(entity, request.getPersonId(), request.getProfessionId());
            return estudioMapper.fromDomainToAdapter(res);
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public Boolean drop(DeleteEstudioRequest request){
        try{
            return studyOutputPortFactory.getStudyOutputPort(request.getDb())
                    .drop(request.getPersonaId(), request.getProfessionId());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public EstudioResponse getOne(GetEstudioRequest request){
        try{
            val res = studyOutputPortFactory.getStudyOutputPort(request.getDb())
                    .findOne(request.getPersonaId(), request.getProfessionId());
            return estudioMapper.fromDomainToAdapter(res);
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

    public List<EstudioResponse> getAll(String db){
        try{
            return studyOutputPortFactory.getStudyOutputPort(db)
                    .findAll().stream().map(estudioMapper::fromDomainToAdapter)
                    .collect(Collectors.toList());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
