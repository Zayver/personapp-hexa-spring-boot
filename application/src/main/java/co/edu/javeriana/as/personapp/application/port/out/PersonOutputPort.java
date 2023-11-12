package co.edu.javeriana.as.personapp.application.port.out;

import java.util.List;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Person;

@Port
public interface PersonOutputPort {
	Person save(Person person);
	Boolean delete(Integer identification);
	List<Person> find();
	Person findById(Integer identification);
}
