package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.configuration.PersonOutputPortFactory;
import co.edu.javeriana.as.personapp.configuration.PhoneOutputPortFactory;
import co.edu.javeriana.as.personapp.mapper.PhoneMapperRest;
import co.edu.javeriana.as.personapp.model.request.telefono.DeleteTelefonoRequest;
import co.edu.javeriana.as.personapp.model.request.telefono.GetTelefonoRequest;
import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.telefono.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Adapter
@Slf4j
public class TelefonoInputAdapterRest {

    @Autowired
    private PhoneOutputPortFactory outputPhonePortFactory;

    @Autowired
    private PersonOutputPortFactory outputPersonPortFactory;

    @Autowired
    private PhoneMapperRest phoneMapperRest;

    public List<TelefonoResponse> historial(String db) {
        try {
            val outputPhone = outputPhonePortFactory.getPhoneOutputPort(db);
            return outputPhone.findAll().stream().map(e -> phoneMapperRest.fromDomainToAdapterRest(e, db))
                    .collect(Collectors.toList());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public TelefonoResponse crearTelefono(TelefonoRequest request){
        try{
            val outputPhone = outputPhonePortFactory.getPhoneOutputPort(request.getDatabase());
            val outputPerson = outputPersonPortFactory.getPersonOutputPort(request.getDatabase());
            val person = outputPerson.findOne(request.getIdOwner());
            val create = outputPhone.create(phoneMapperRest.fromAdapterToDomain(request, person));
            return phoneMapperRest.fromDomainToAdapterRest(create, request.getDatabase());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public TelefonoResponse editTelefono(TelefonoRequest request){
        try{
            val outputPhone = outputPhonePortFactory.getPhoneOutputPort(request.getDatabase());
            val outputPerson = outputPersonPortFactory.getPersonOutputPort(request.getDatabase());
            val person = outputPerson.findOne(request.getIdOwner());
            val res = outputPhone.edit(request.getNumber(), phoneMapperRest.fromAdapterToDomain(request, person));
            return phoneMapperRest.fromDomainToAdapterRest(res, request.getDatabase());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    public Boolean deleteTelefono(DeleteTelefonoRequest request){
        try{
            val outputPhone = outputPhonePortFactory.getPhoneOutputPort(request.getDatabase());
            return outputPhone.drop(request.getId());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    public TelefonoResponse getOnePerson(GetTelefonoRequest request) {
        try{
            val outputPhone = outputPhonePortFactory.getPhoneOutputPort(request.getDatabase());
            val res = outputPhone.findOne(request.getId());
            return phoneMapperRest.fromDomainToAdapterRest(res, request.getDatabase());
        } catch (InvalidOptionException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
