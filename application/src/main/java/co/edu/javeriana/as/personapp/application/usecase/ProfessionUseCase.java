package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@UseCase
public class ProfessionUseCase implements ProfessionInputPort {

    private ProfessionOutputPort professionPersistence;

    public ProfessionUseCase(@Qualifier("professionOutputAdapterMaria") ProfessionOutputPort professionOutputPort) {
        this.professionPersistence = professionOutputPort;
    }

    @Override
    public Profession create(Profession profession) {
        return professionPersistence.save(profession) ;
    }

    @Override
    public Profession edit(String identification, Profession profession) throws NoExistException {
        val oldPerson = professionPersistence.findById(Integer.valueOf(identification));
        if (oldPerson != null)
            return professionPersistence.save(profession);
        throw new NoExistException(
                "The profession with id " + identification + " does not exist into db, cannot be edited");

    }

    @Override
    public Boolean drop(String identification) throws NoExistException {
        val old = professionPersistence.findById(Integer.valueOf(identification));
        if (old != null)
            return professionPersistence.delete(Integer.valueOf(identification));
        throw new NoExistException(
                "The profession with id " + identification + " does not exist into db, cannot be dropped");
    }

    @Override
    public List<Profession> findAll() {
        return professionPersistence.find();
    }

    @Override
    public Profession findOne(String identification) throws NoExistException {
        val old = professionPersistence.findById(Integer.valueOf(identification));
        if (old != null)
            return old;
        throw new NoExistException("The person with id " + identification + " does not exist into db, cannot be found");
    }
}
