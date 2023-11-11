package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.domain.Phone;

import java.util.List;

public interface PhoneOutputPort {
    Phone save(Phone person);
    Boolean delete(Integer identification);
    List<Phone> find();
    Phone findById(Integer identification);
}
