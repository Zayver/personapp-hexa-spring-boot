package co.edu.javeriana.as.personapp.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Phone {
	@NonNull
	private String number;
	@NonNull
	private String company;
	@NonNull
	private Person owner;
}
