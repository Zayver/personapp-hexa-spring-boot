package co.edu.javeriana.as.personapp.model.request.persona;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonaRequest {
	@NotBlank(message = "DNI cannot be blank")
	private String dni;
	@NotBlank(message = "FirstName cannot be blank")
	private String firstName;
	@NotBlank(message = "LastName cannot be blank")
	private String lastName;
	@NotBlank(message = "Age cannot be blank")
	@Pattern(regexp = "\\d+", message = "Age must be a number")
	private String age;
	@NotBlank
	@Pattern(regexp = "^(MALE|FEMALE|OTHER)$", message = "Sex must be 'MALE', 'FEMALE' or 'OTHER'")
	private String sex;
	@NotBlank(message = "Database cannot be blank")
	private String database;
}
