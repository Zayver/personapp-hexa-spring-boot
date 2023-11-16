package co.edu.javeriana.as.personapp.mongo.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.EstudiosMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.EstudioRepositoryMongo;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("studyOutputAdapterMongo")
public class StudyOutputAdapterMongo  implements StudyOutputPort {
    @Autowired
    private EstudioRepositoryMongo estudioRepositoryMongo;

    @Autowired
    private EstudiosMapperMongo estudiosMapperMongo;
    @Override
    public Study create(Study study) {
        val res = estudioRepositoryMongo.save(estudiosMapperMongo.fromDomainToAdapter(study));
        return estudiosMapperMongo.fromAdapterToDomain(res);
    }

    @Override
    public Boolean drop(Integer personId, Integer professionId) {
        estudioRepositoryMongo.deleteByPrimaryPersonaAndPrimaryProfesion(personId, professionId);
        return estudioRepositoryMongo.findByPrimaryPersonaAndPrimaryProfesion(personId, professionId).isEmpty();
    }

    @Override
    public List<Study> findAll() {
        return estudioRepositoryMongo.findAll()
                .stream().map(estudiosMapperMongo::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Study findOne(Integer personId, Integer professionId) {
        return estudioRepositoryMongo.findByPrimaryPersonaAndPrimaryProfesion(personId, professionId)
                .map(estudiosMapperMongo::fromAdapterToDomain).orElse(null);
    }
}
