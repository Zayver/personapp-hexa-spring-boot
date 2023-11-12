package co.edu.javeriana.as.personapp.model.response.persona;

import co.edu.javeriana.as.personapp.model.request.persona.PersonaRequest;
import lombok.Getter;

@Getter
public class PersonaResponse extends PersonaRequest{
	
	private String status;
	
	public PersonaResponse(String dni, String firstName, String lastName, String age, String sex, String database, String status) {
		super(dni, firstName, lastName, age, sex, database);
		this.status = status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}
