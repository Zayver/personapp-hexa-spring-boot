package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface PhoneInputPort {
    void setPersistence(PhoneOutputPort phonePersistence);

    Phone create(Phone phone);

    Phone edit(String identification, Phone phone) throws NoExistException;

    Boolean drop(String identification) throws NoExistException;

    List<Phone> findAll();

    Phone findOne(String identification) throws NoExistException;

    Integer count();

    Person getPerson(String identification) throws NoExistException;
}
