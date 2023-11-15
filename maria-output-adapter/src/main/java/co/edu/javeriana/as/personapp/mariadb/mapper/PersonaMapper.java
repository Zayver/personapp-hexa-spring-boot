package co.edu.javeriana.as.personapp.mariadb.mapper;

import co.edu.javeriana.as.personapp.domain.Gender;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mariadb.entity.PersonaEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
            TelefonoMapper.class,
            EstudiosMapper.class
        }
)
public interface PersonaMapper {
    @Mapping(target = "identification", source = "cc")
    @Mapping(target = "firstName", source = "nombre")
    @Mapping(target = "lastName", source = "apellido")
    @Mapping(target = "gender", source = "genero")
    @Mapping(target = "age", source = "edad")
    @Mapping(target = "phoneNumbers", source = "telefonos")
    @Mapping(target = "studies", source = "estudios")
    Person fromAdapterToDomain(PersonaEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default Person fromAdapterToDomain(PersonaEntity persona) {
        return fromAdapterToDomain(persona, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    PersonaEntity fromDomainToAdapter(Person domain, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default PersonaEntity fromDomainToAdapter(Person persona) {
        return fromDomainToAdapter(persona, new CycleAvoidingMappingContext());
    }

    default Character mapGenderToCharacter(Gender gender) {
        return gender == Gender.FEMALE ? 'F' : gender == Gender.MALE ? 'M' : ' ';
    }

    default Gender mapCharacterToGender(Character gender) {
        switch (gender){
            case 'F': {
                return Gender.FEMALE;
            }
            case 'M':{
                return Gender.MALE;
            }
            case 'O':{
                return Gender.OTHER;
            }
        }
        throw new RuntimeException("Invalid option: "+gender);
    }
}
