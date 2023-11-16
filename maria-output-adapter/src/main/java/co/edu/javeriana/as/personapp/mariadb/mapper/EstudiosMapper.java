package co.edu.javeriana.as.personapp.mariadb.mapper;

import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntity;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
    uses = {
        PersonaMapper.class, ProfesionMapper.class
    }
)
public interface EstudiosMapper {

    @Mapping(target = "fecha", source = "study.graduationDate")
    @Mapping(target = "univer", source = "study.universityName")
    @Mapping(target = "persona", source = "study.person")
    @Mapping(target = "profesion", source = "study.profession")
    @Mapping(target = "estudiosPK", source = "pk")
    EstudiosEntity fromDomainToAdapter (Study study, EstudiosEntityPK pk, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default EstudiosEntity fromDomainToAdapter (Study study, EstudiosEntityPK estudiosEntityPK){
        return fromDomainToAdapter(study, estudiosEntityPK, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    Study fromAdapterToDomain(EstudiosEntity estudiosEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default Study fromAdapterToDomain(EstudiosEntity estudiosEntity){
        return fromAdapterToDomain(estudiosEntity, new CycleAvoidingMappingContext());
    }
}
