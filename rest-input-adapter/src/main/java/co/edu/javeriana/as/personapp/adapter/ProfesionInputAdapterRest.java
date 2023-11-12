package co.edu.javeriana.as.personapp.adapter;

import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.mapper.ProfessionMapperRest;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfesionInputAdapterRest {
    @Autowired
    private ProfessionOutputPort professionOutputPort;

    @Autowired
    private ProfessionMapperRest professionMapperRest;
}
