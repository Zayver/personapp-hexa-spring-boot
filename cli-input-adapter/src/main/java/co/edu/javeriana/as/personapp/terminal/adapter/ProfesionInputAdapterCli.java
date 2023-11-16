package co.edu.javeriana.as.personapp.terminal.adapter;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.ProfessionInputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.ProfessionUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.mapper.ProfesionMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class ProfesionInputAdapterCli {

    @Autowired
    @Qualifier("professionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    @Autowired
    @Qualifier("professionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private ProfesionMapperCli personaMapperCli;

    ProfessionInputPort professionInputPort;

    public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            professionInputPort = new ProfessionUseCase(professionOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial1() {
        log.info("Into historial PersonaEntity in Input Adapter");
        List<ProfesionModelCli> persona = professionInputPort.findAll().stream()
                .map(personaMapperCli::fromDomainToAdapterCli)
                .collect(Collectors.toList());
        persona.forEach(p -> System.out.println(p.toString()));
    }

    public void historial() {
        log.info("Into historial PersonaEntity in Input Adapter");
        professionInputPort.findAll().stream()
                .map(personaMapperCli::fromDomainToAdapterCli)
                .forEach(System.out::println);
    }

    public void crear(Scanner sc) {
        log.info("Into crear PersonaEntity in Input Adapter");
        sc = new Scanner(System.in);
        // System.out.println("La identificacion actual: ");
        // String id = sc.nextLine();
        System.out.println("La identificacion nueva: ");
        String identification = sc.nextLine();
        System.out.println("El nombre nuevo: ");
        String nombre = sc.nextLine();
        System.out.println("La descripcion nueva: ");
        String edad = sc.nextLine();
        Integer id = Integer.parseInt(identification);
        Profession person = new Profession(id, nombre, edad, null);
        person = professionInputPort.create(person);
        System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
    }

    public void editar(Scanner sc) {
        log.info("Into editar PersonaEntity in Input Adapter");
        try {
            sc = new Scanner(System.in);
            System.out.println("La identificacion actual: ");
            String old_identification = sc.nextLine();
            System.out.println("La identificacion nueva: ");
            String identification = sc.nextLine();
            System.out.println("El nombre nuevo: ");
            String nombre = sc.nextLine();
            System.out.println("La descripcion nueva: ");
            String edad = sc.nextLine();
            Integer id = Integer.parseInt(identification);
            Profession person = new Profession(id, nombre, edad, null);
            person = professionInputPort.edit(String.valueOf(Integer.parseInt(old_identification)), person);
            System.out.println(personaMapperCli.fromDomainToAdapterCli(person));
        } catch (NoExistException e) {
            log.warn(e.getMessage());
        }
    }

    public void eliminar(Scanner sc) {
        log.info("Into eliminar PersonaEntity in Input Adapter");
        try {
            sc = new Scanner(System.in);
            System.out.println("La identificacion actual: ");
            String old_identification = sc.nextLine();
            System.out.println(professionInputPort.drop(String.valueOf(Integer.parseInt(old_identification))));
        } catch (NoExistException e) {
            log.warn(e.getMessage());
        }
    }

}
