package co.edu.javeriana.as.personapp.mapper;


import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.estudio.EstudioRequest;
import co.edu.javeriana.as.personapp.model.response.estudio.EstudioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
            PersonaMapperRest.class, ProfessionMapperRest.class
        }
)
public interface EstudioMapper {

    Study fromAdapterToDomain(EstudioRequest request);

    EstudioResponse fromDomainToAdapter(Study study);
}
