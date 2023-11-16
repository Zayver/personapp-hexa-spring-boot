package co.edu.javeriana.as.personapp.terminal.model;

import co.edu.javeriana.as.personapp.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaModelCli {
	public PersonaModelCli(@NonNull Person owner) {
		this.cc = owner.getIdentification();
		this.nombre = owner.getFirstName();
		this.apellido = owner.getLastName();
		this.genero = owner.getGender().toString();
		this.edad = owner.getAge();
	}
	private Integer cc;
	private String nombre;
	private String apellido;
	private String genero;
	private Integer edad;
}
