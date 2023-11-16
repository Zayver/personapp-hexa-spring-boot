package co.edu.javeriana.as.personapp.mariadb.adapter;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.mariadb.entity.ProfesionEntity;
import co.edu.javeriana.as.personapp.mariadb.mapper.ProfesionMapper;
import co.edu.javeriana.as.personapp.mariadb.repository.ProfesionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Adapter("professionOutputAdapterMaria")
@Transactional
public class ProfessionOutputAdapterMaria implements ProfessionOutputPort {
    @Autowired
    private ProfesionRepository profesionRepository;

    @Autowired
    private ProfesionMapper profesionMapper;

    @Override
    public Profession save(Profession profession) {
        val entity = profesionMapper.fromDomainToAdapter(profession);
        ProfesionEntity dom = profesionRepository.save(entity);
        return profesionMapper.fromAdapterToDomain(dom);
    }

    @Override
    public Boolean delete(Integer identification) {
        profesionRepository.deleteById(identification);
        return profesionRepository.findById(identification).isEmpty();
    }

    @Override
    public List<Profession> find() {
        return profesionRepository.findAll().stream()
                .map(profesionMapper::fromAdapterToDomain).collect(Collectors.toList());
    }

    @Override
    public Profession findById(Integer identification) {
        if (profesionRepository.findById(identification).isEmpty()) {
            return null;
        } else {
            return profesionMapper.fromAdapterToDomain(profesionRepository.findById(identification).get());
        }
    }
}
