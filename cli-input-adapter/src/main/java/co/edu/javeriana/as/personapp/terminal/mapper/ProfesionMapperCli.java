package co.edu.javeriana.as.personapp.terminal.mapper;

import java.util.List;
import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

@Mapper
public class ProfesionMapperCli {

    public Profession fromAdapterCliToDomain(ProfesionModelCli profesion, List<Study> studies){
        return Profession.builder()
                .id(profesion.getIdentification())
                .name(profesion.getNombre())
                .description(profesion.getDescripcion())
                .studies(studies)
                .build();
    }

    public ProfesionModelCli fromDomainToAdapterCli(Profession profession) {
        return ProfesionModelCli.builder()
                .identification(profession.getId())
                .nombre(profession.getName())
                .descripcion(profession.getDescription())
                .build();
    }
}
