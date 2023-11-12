package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.model.request.telefono.TelefonoRequest;
import co.edu.javeriana.as.personapp.model.response.telefono.TelefonoResponse;
import lombok.extern.slf4j.Slf4j;

@Mapper
@Slf4j
public class PhoneMapperRest {
    public TelefonoResponse fromDomainToAdapterRestMaria(Phone phone) {
        return fromDomainToAdapterRest(phone, "MariaDB");
    }
    public TelefonoResponse fromDomainToAdapterRestMongo(Phone phone) {
        return fromDomainToAdapterRest(phone, "MongoDB");
    }

    public TelefonoResponse fromDomainToAdapterRest(Phone phone, String database) {
        return TelefonoResponse.builder()
                .status("OK")
                .company(phone.getCompany())
                .number(phone.getNumber())
                .database(database)
                .idOwner(phone.getOwner().getIdentification())
                .build();
    }

    public Phone fromAdapterToDomain(TelefonoRequest request, Person owner) {
        return Phone.builder()
                .company(request.getCompany())
                .number(request.getNumber())
                .owner(owner)
                .build();
    }
}
