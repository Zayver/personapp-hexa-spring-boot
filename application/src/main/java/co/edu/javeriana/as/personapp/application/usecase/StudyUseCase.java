package co.edu.javeriana.as.personapp.application.usecase;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.UseCase;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@UseCase
public class StudyUseCase implements StudyInputPort {
    private StudyOutputPort studyOutputPort;

    public StudyUseCase(@Qualifier("studyOutputAdapterMaria") StudyOutputPort studyOutputPort) {
        this.studyOutputPort = studyOutputPort;
    }


    @Override
    public Study create(Study study) {
        return studyOutputPort.create(study);
    }

    @Override
    public Study edit(Study study, Integer personId, Integer professionId) throws NoExistException {
        val editEntity = studyOutputPort.findOne( personId, professionId);
        if(editEntity == null){
            throw new NoExistException("Study with person id: "+ personId + " and profession id: "+ professionId +
                    "Not exists" );
        }
        return studyOutputPort.create(study);
    }

    @Override
    public Boolean drop(Integer personId, Integer professionId) throws NoExistException {
        val entity = studyOutputPort.findOne(personId, professionId);
        if(entity == null){
            throw new NoExistException("Study with person id: "+ personId + " and profession id: "+ professionId +
                    "Not exists" );
        }
        return studyOutputPort.drop(personId, professionId);
    }

    @Override
    public List<Study> findAll() {
        return studyOutputPort.findAll();
    }

    @Override
    public Study findOne(Integer personId, Integer professionId) throws NoExistException {
        val entity = studyOutputPort.findOne(personId, professionId);
        if(entity ==  null){
            throw new NoExistException("Study with person id: "+ personId + " and profession id: "+ professionId +
                    "Not exists" );
        }
        return entity;
    }
}
