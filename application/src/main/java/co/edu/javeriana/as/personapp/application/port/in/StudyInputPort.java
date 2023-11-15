package co.edu.javeriana.as.personapp.application.port.in;

import co.edu.javeriana.as.personapp.common.annotations.Port;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

@Port
public interface StudyInputPort {

    Study create (Study study);

    Study edit(Study study, Integer personId, Integer professionId) throws NoExistException;

    Boolean drop(Integer personId, Integer professionId) throws NoExistException;

    List<Study> findAll();

    Study findOne(Integer personId, Integer professionId) throws NoExistException;

}
