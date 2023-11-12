package co.edu.javeriana.as.personapp.mariadb.mapper;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudiosMapper {

    @Mapping(target = "fecha", source = "graduationDate")
    @Mapping(target = "univer", source = "universityName")
    @Mapping(target = "persona", source = "person")
    @Mapping(target = "profesion", source = "profession")
    EstudiosEntity fromDomainToAdapter (Study study, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default EstudiosEntity fromDomainToAdapter (Study study){
        return fromDomainToAdapter(study, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    Study fromAdapterToDomain(EstudiosEntity estudiosEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default Study fromAdapterToDomain(EstudiosEntity estudiosEntity){
        return fromAdapterToDomain(estudiosEntity, new CycleAvoidingMappingContext());
    }
}
