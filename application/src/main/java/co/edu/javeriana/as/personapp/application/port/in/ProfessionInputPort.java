package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Profession;

import java.util.List;

@Port
public interface ProfessionInputPort {
    Profession create(Profession profession);

    Profession edit(String identification, Profession profession) throws NoExistException;

    Boolean drop(String identification) throws NoExistException;

    List<Profession> findAll();

    Profession findOne(String identification) throws NoExistException;
}
