package co.edu.javeriana.as.personapp.application.port.out;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;

import java.util.List;

public interface StudyOutputPort {

    Study create (Study study);

    Boolean drop(Integer personId, Integer professionId);

    List<Study> findAll();

    Study findOne(Integer personId, Integer professionId);
}
