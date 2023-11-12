package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.domain.Phone;

import java.util.List;

@Port
public interface PhoneOutputPort {
    Phone save(Phone phone);
    Boolean delete(String identification);
    List<Phone> find();
    Phone findById(String identification);
}
