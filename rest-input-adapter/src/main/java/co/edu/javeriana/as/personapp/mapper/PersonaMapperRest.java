package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.persona.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.persona.PersonaResponse;

@Mapper
public class PersonaMapperRest {

	public PersonaResponse fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}
	public PersonaResponse fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}

	public PersonaResponse fromDomainToAdapterRest(Person person, String database) {
		return new PersonaResponse(
				person.getIdentification()+"",
				person.getFirstName(),
				person.getLastName(),
				person.getAge()+"",
				person.getGender().toString(),
				database,
				"OK");
	}

	public Person fromAdapterToDomain(PersonaRequest request) {
		return Person.builder()
				.identification(Integer.valueOf(request.getDni()))
				.age(Integer.valueOf(request.getAge()))
				.firstName(request.getFirstName())
				.gender(Gender.valueOf(request.getSex()))
				.lastName(request.getLastName())
				.build();
	}

}
