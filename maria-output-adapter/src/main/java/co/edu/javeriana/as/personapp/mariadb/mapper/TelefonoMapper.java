package co.edu.javeriana.as.personapp.mariadb.mapper;

import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PersonaMapper.class})
public interface TelefonoMapper {

    @Mapping(target = "num", source = "number")
    @Mapping(target = "oper", source = "company")
    @Mapping(target = "duenio", source = "owner")
    TelefonoEntity fromDomainToAdapter(Phone phone, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default TelefonoEntity fromDomainToAdapter(Phone phone){
        return fromDomainToAdapter(phone, new CycleAvoidingMappingContext());
    }

    @InheritInverseConfiguration
    Phone fromAdapterToDomain(TelefonoEntity phone, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @CycleAvoidingMappingContext.DoIgnore
    default Phone fromAdapterToDomain(TelefonoEntity phone){
        return fromAdapterToDomain(phone, new CycleAvoidingMappingContext());
    }

}
