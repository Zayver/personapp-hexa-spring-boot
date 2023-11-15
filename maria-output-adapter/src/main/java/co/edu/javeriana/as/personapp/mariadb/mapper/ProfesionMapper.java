package co.edu.javeriana.as.personapp.mariadb.mapper;

import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {PersonaMapper.class})
public interface ProfesionMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "nom")
    @Mapping(target = "description", source = "des")
    @Mapping(target = "studies", source = "estudios")
    Profession fromAdapterToDomain(ProfesionEntity entity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default Profession fromAdapterToDomain(ProfesionEntity entity){
        return fromAdapterToDomain(entity, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    ProfesionEntity fromDomainToAdapter(Profession profession, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default ProfesionEntity fromDomainToAdapter(Profession entity){
        return fromDomainToAdapter(entity, new CycleAvoidingMappingContext());
    }

}
