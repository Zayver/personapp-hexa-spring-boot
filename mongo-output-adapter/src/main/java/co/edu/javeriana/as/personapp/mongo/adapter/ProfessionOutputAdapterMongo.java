package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Phone;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Adapter("professionOutputAdapterMongo")
public class ProfessionOutputAdapterMongo implements PhoneOutputPort {
    @Override
    public Phone save(Phone phone) {
        return null;
    }

    @Override
    public Boolean delete(String identification) {
        return null;
    }

    @Override
    public List<Phone> find() {
        return null;
    }

    @Override
    public Phone findById(String identification) {
        return null;
    }
}
