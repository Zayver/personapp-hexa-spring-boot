package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.mongo.document.TelefonoDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.TelefonoMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.PhoneRepositoryMongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Adapter("phoneOutputAdapterMongo")
public class PhoneOutputAdapterMongo implements PhoneOutputPort {
    @Autowired
    private PhoneRepositoryMongo phoneRepositoryMongo;

    @Autowired
    private TelefonoMapperMongo phoneMapperMongo;
    @Override
    public Phone save(Phone phone) {
        TelefonoDocument phoneDoc = phoneRepositoryMongo.save(phoneMapperMongo.fromDomainToAdapter(phone));
        return phoneMapperMongo.fromAdapterToDomain(phoneDoc);
    }

    @Override
    public Boolean delete(String identification) {
        phoneRepositoryMongo.deleteById(Integer.valueOf(identification));
        return phoneRepositoryMongo.findById(Integer.valueOf(identification)).isEmpty();
    }

    @Override
    public List<Phone> find() {
        return phoneRepositoryMongo.findAll().stream().map(phoneMapperMongo::fromAdapterToDomain).collect(Collectors.toList());
    }

    @Override
    public Phone findById(String identification) {
        Optional<TelefonoDocument> phoneDoc = phoneRepositoryMongo.findById(Integer.valueOf(identification));
        return phoneDoc.map(telefonoDocument -> phoneMapperMongo.fromAdapterToDomain(telefonoDocument)).orElse(null);
    }
}
