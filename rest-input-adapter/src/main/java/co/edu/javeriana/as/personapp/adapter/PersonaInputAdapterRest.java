package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.configuration.PersonOutputPortFactory;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mapper.PersonaMapperRest;
import co.edu.javeriana.as.personapp.model.request.persona.DeletePersonaRequest;
import co.edu.javeriana.as.personapp.model.request.persona.GetPersonaRequest;
import co.edu.javeriana.as.personapp.model.request.persona.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.persona.PersonaResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter
public class PersonaInputAdapterRest {

	@Autowired
	private PersonOutputPortFactory outputPortFactory;

	@Autowired
	private PersonaMapperRest personaMapperRest;


	public List<PersonaResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			return outputPortFactory.getPersonOutputPort(database).findAll().stream().map(personaMapperRest::fromDomainToAdapterRestMaria)
					.collect(Collectors.toList());
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			//return new ArrayList<PersonaResponse>();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	public PersonaResponse crearPersona(PersonaRequest request) {
		try {
			val outPort = outputPortFactory.getPersonOutputPort(request.getDatabase());
			Person person = outPort.create(personaMapperRest.fromAdapterToDomain(request));
			return personaMapperRest.fromDomainToAdapterRestMaria(person);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	public PersonaResponse editPerson(PersonaRequest request){
		try{
			val outPort = outputPortFactory.getPersonOutputPort(request.getDatabase());
			val personEdited = personaMapperRest.fromAdapterToDomain(request);
			val res = outPort.edit(personEdited.getIdentification(), personEdited);
			return personaMapperRest.fromDomainToAdapterRest(res, request.getDatabase());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

	public Boolean deletePerson(DeletePersonaRequest request){
		try{
			val outPort = outputPortFactory.getPersonOutputPort(request.getDatabase());
            return outPort.drop(request.getId());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

	public PersonaResponse getOnePerson(GetPersonaRequest request){
		try{
			val outPort = outputPortFactory.getPersonOutputPort(request.getDatabase());
			val res = outPort.findOne(request.getId());
			return personaMapperRest.fromDomainToAdapterRest(res, request.getDatabase());
		} catch (InvalidOptionException | NoExistException e) {
			log.warn(e.getMessage());
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }



}
