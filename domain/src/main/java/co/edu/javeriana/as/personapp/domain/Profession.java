package co.edu.javeriana.as.personapp.domain;

import java.util.List;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profession {
	@NonNull
	private Integer id;
	@NonNull
	private String name;
	private String description;
	@ToString.Exclude
	private List<Study> studies;
}
