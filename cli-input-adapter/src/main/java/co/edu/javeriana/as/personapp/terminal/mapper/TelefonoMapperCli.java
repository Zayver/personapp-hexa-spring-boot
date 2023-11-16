package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

@Mapper
public class TelefonoMapperCli {
    public Phone fromAdapterCliToDomain(TelefonoModelCli telefono, Person person){
        return Phone.builder()
                .number(telefono.getNumero())
                .company(telefono.getCompania())
                .owner(person)
                .build();
    }

    public TelefonoModelCli fromDomainToAdapterCli(Phone phone, PersonaModelCli person) {
        return TelefonoModelCli.builder()
                .numero(phone.getNumber())
                .compania(phone.getCompany())
                .persona(person)
                .build();
    }
}
