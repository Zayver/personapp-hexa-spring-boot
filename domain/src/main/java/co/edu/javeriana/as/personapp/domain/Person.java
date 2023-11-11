package co.edu.javeriana.as.personapp.domain;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Person {
	@NonNull
	private Integer identification;
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
	@NonNull
	private Gender gender;
	private Integer age;
	@ToString.Exclude
	private List<Phone> phoneNumbers;
	@ToString.Exclude
	private List<Study> studies;

	public Boolean isValidAge() {
		return this.age >= 0;
	}
}
