package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.EstudioModelCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

@Mapper
public class EstudioMapperCli {

    public Study fromAdapterCliToDomain(EstudioModelCli estudio, Profession profession, Person person){
        return Study.builder()
                .graduationDate(estudio.getGraduationDate())
                .universityName(estudio.getUniversityName())
                .profession(profession)
                .person(person)
                .build();
    }

    public EstudioModelCli fromDomainToAdapterCli(Study person, PersonaModelCli personaModelCli, ProfesionModelCli profesionModelCli) {
        return EstudioModelCli.builder()
                .graduationDate(person.getGraduationDate())
                .universityName(person.getUniversityName())
                .persona(personaModelCli)
                .profesion(profesionModelCli)
                .build();
    }
}
