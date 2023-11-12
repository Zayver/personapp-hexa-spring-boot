package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.domain.Study;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@UseCase
public class PhoneUseCase implements PhoneInputPort {

    private PhoneOutputPort phonePersistence;

    public PhoneUseCase(@Qualifier("phoneOutputAdapterMaria") PhoneOutputPort phonePersistence){
        this.phonePersistence = phonePersistence;
    }

    @Override
    public void setPersistence(PhoneOutputPort phonePersistence) {
        this.phonePersistence = phonePersistence;
    }

    @Override
    public Phone create(Phone phone) {
        return phonePersistence.save(phone);
    }

    @Override
    public Phone edit(String identification, Phone phone) throws NoExistException {
        Phone oldPhone = phonePersistence.findById(identification);
        if (oldPhone != null)
            return phonePersistence.save(phone);
        throw new NoExistException(
                "The phone with id " + identification + " does not exist into db, cannot be edited");
    }

    @Override
    public Boolean drop(String identification) throws NoExistException {
        Phone old = phonePersistence.findById(identification);
        if (old != null)
            return phonePersistence.delete(identification);
        throw new NoExistException(
                "The phone with id " + identification + " does not exist into db, cannot be dropped");
    }

    @Override
    public List<Phone> findAll() {
        return phonePersistence.find();
    }

    @Override
    public Phone findOne(String identification) throws NoExistException {
        return phonePersistence.findById(identification);
    }

    @Override
    public Integer count() {
        return phonePersistence.find().size();
    }

    @Override
    public Person getPerson(String identification) throws NoExistException {
        Phone phone = phonePersistence.findById(identification);
        if(phone != null)
            return phone.getOwner();
        throw new NoExistException(
                "The phone with id " + identification + " does not exist into db, cannot be dropped");
    }
}
