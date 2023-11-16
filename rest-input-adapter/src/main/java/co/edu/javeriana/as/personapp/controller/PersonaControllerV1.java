package co.edu.javeriana.as.personapp.controller;

import co.edu.javeriana.as.personapp.adapter.PersonaInputAdapterRest;
import co.edu.javeriana.as.personapp.model.request.persona.DeletePersonaRequest;
import co.edu.javeriana.as.personapp.model.request.persona.GetPersonaRequest;
import co.edu.javeriana.as.personapp.model.request.persona.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.persona.PersonaResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/persona")
@Validated
public class PersonaControllerV1 {
	
	@Autowired
	private PersonaInputAdapterRest personaInputAdapterRest;
	
	@ResponseBody
	@GetMapping(path = "/{database}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PersonaResponse> personas(@PathVariable String database) {
		log.info("Into personas REST API");
			return personaInputAdapterRest.historial(database.toUpperCase());
	}

	@ResponseBody
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public PersonaResponse crearPersona(@Valid @RequestBody PersonaRequest request) {
		log.info("esta en el metodo crearTarea en el controller del api");
		return personaInputAdapterRest.crearPersona(request);
	}

	@PutMapping
	public PersonaResponse editPersona(@Valid @RequestBody PersonaRequest request){
		log.info("Editando persona en el controller del api");
		return personaInputAdapterRest.editPerson(request);
	}

	@DeleteMapping
	public ResponseEntity<Object> deletePersona(@Valid @RequestBody DeletePersonaRequest request){
		log.info("Eliminando persona con id {} del controller del api", request.getId());
		return personaInputAdapterRest.deletePerson(request) ? ResponseEntity.ok().build(): ResponseEntity.badRequest().build();
	}

	@GetMapping
	public PersonaResponse getOnePersona(@Valid @RequestBody GetPersonaRequest request){
		log.info("Obteniendo la persona con id {} en el controller del api", request.getId());
		return personaInputAdapterRest.getOnePerson(request);
	}

}
