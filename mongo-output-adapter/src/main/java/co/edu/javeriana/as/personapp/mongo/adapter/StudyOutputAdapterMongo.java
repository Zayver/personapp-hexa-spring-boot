package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Adapter("studyOutputAdapterMongo")
public class StudyOutputAdapterMongo  implements StudyOutputPort {
    @Override
    public Study create(Study study) {
        return null;
    }

    @Override
    public Boolean drop(Integer personId, Integer professionId) {
        return null;
    }

    @Override
    public List<Study> findAll() {
        return null;
    }

    @Override
    public Study findOne(Integer personId, Integer professionId) {
        return null;
    }
}
