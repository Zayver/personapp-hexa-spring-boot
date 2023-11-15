package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mariadb.entity.EstudiosEntityPK;
import co.edu.javeriana.as.personapp.mariadb.mapper.EstudiosMapper;
import co.edu.javeriana.as.personapp.mariadb.repository.StudyRepositoryMaria;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("studyOutputAdapterMaria")
@Transactional
public class StudyOutputAdapterMaria implements StudyOutputPort {

    @Autowired
    private StudyRepositoryMaria studyRepositoryMaria;
    @Autowired
    private EstudiosMapper estudiosMapper;

    @Override
    public Study create(Study study) {
        val pk = EstudiosEntityPK.builder().ccPer(study.getPerson().getIdentification())
                .idProf(study.getProfession().getId()).build();
        val res = studyRepositoryMaria.save(estudiosMapper.fromDomainToAdapter(study, pk));
        return estudiosMapper.fromAdapterToDomain(res);
    }

    @Override
    public Boolean drop(Integer personId, Integer professionId) {
        val pk = EstudiosEntityPK.builder().ccPer(professionId)
                .idProf(professionId).build();
        studyRepositoryMaria.deleteById(pk);
        return studyRepositoryMaria.existsById(pk);
    }

    @Override
    public List<Study> findAll() {
        return studyRepositoryMaria.findAll().stream().map(
                estudiosMapper::fromAdapterToDomain
        ).collect(Collectors.toList());
    }

    @Override
    public Study findOne(Integer personId, Integer professionId) {
        val pk = EstudiosEntityPK.builder().ccPer(professionId)
                .idProf(professionId).build();
        val entity = studyRepositoryMaria.findById(pk);
        return entity.map(estudiosEntity -> estudiosMapper.fromAdapterToDomain(estudiosEntity)).orElse(null);
    }
}
