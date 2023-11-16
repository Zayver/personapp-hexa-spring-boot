package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.model.request.profesion.ProfesionRequest;
import co.edu.javeriana.as.personapp.model.response.profesion.ProfesionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProfessionMapperRest {
    ProfesionResponse fromDomainToAdapter(Profession profession);
    Profession fromAdapterToDomain(ProfesionRequest profesionRequest);

}
