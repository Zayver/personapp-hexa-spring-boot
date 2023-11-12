package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mariadb.entity.TelefonoEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.TelefonoMapper;
import co.edu.javeriana.as.personapp.mariadb.repository.TelefonoRepositoryMaria;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("phoneOutputAdapterMaria")
@Transactional
public class PhoneOutputAdapterMaria implements PhoneOutputPort {
    @Autowired
    private TelefonoRepositoryMaria telefonoRepositoryMaria;

    @Autowired
    private TelefonoMapper telefonoMapperMaria;

    @Override
    public Phone save(Phone phone) {
        TelefonoEntity persistedTelefono = telefonoRepositoryMaria.save(telefonoMapperMaria.fromDomainToAdapter(phone));
        return telefonoMapperMaria.fromAdapterToDomain(persistedTelefono);
    }

    @Override
    public Boolean delete(String identification) {
        telefonoRepositoryMaria.deleteById(identification);
        return telefonoRepositoryMaria.findById(identification).isEmpty();
    }

    @Override
    public List<Phone> find() {
        return telefonoRepositoryMaria.findAll().stream().map(telefonoMapperMaria::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Phone findById(String identification) {
        if (telefonoRepositoryMaria.findById(identification).isEmpty()) {
            return null;
        } else {
            return telefonoMapperMaria.fromAdapterToDomain(telefonoRepositoryMaria.findById(identification).get());
        }
    }
}
