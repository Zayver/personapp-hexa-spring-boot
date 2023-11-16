package co.edu.javeriana.as.personapp.terminal.adapter;

import java.time.LocalDate;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.StudyInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.ProfessionOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.StudyUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.exceptions.NoExistException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.mapper.EstudioMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.PersonaModelCli;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class EstudioInputAdapterCli {

    @Autowired
    @Qualifier("studyOutputAdapterMaria")
    private StudyOutputPort studyOutputPortMaria;

    @Autowired
    @Qualifier("studyOutputAdapterMongo")
    private StudyOutputPort studyOutputPortMongo;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMongo")
    private PersonOutputPort personOutputPortMongo;

    @Autowired
    @Qualifier("professionOutputAdapterMaria")
    private ProfessionOutputPort professionOutputPortMaria;

    @Autowired
    @Qualifier("professionOutputAdapterMongo")
    private ProfessionOutputPort professionOutputPortMongo;

    @Autowired
    private EstudioMapperCli personaMapperCli;

    StudyInputPort studyInputPort;

    public void setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
        if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMaria);
        } else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            studyInputPort = new StudyUseCase(studyOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid database option: " + dbOption);
        }
    }

    public void historial() {
        log.info("Into historial PersonaEntity in Input Adapter");
        studyInputPort.findAll().stream()
                .map(persona -> personaMapperCli.fromDomainToAdapterCli(persona, new PersonaModelCli(persona.getPerson()), new ProfesionModelCli(persona.getProfession())))
                .forEach(System.out::println);
    }

    public void crear(Scanner sc) {
        log.info("Into crear PersonaEntity in Input Adapter");
        sc = new Scanner(System.in);
        // System.out.println("La identificacion actual: ");
        // String id = sc.nextLine();
        System.out.println("El nombre de la universidad nueva: ");
        String identification = sc.nextLine();
        System.out.println("La fecha de graduacion nueva: ");
        String nombre = sc.nextLine();
        System.out.println("La persona nueva: ");
        String apellido = sc.nextLine();
        System.out.println("La profesion nueva: ");
        String edad = sc.nextLine();
        Person person = null;
        Profession profession = null;
        LocalDate date = LocalDate.parse(nombre);
        Study phone = new Study(person, profession, date, identification);
        phone = studyInputPort.create(phone);
        System.out.println(personaMapperCli.fromDomainToAdapterCli(phone, null, null));
    }

    public void editar(Scanner sc) {
        log.info("Into editar PersonaEntity in Input Adapter");
        try {
            sc = new Scanner(System.in);
            System.out.println("La profesion actual: ");
            String old_identification = sc.nextLine();
            System.out.println("La persona actual: ");
            String old_nombre = sc.nextLine();
            System.out.println("El nombre de la universidad nueva: ");
            String identification = sc.nextLine();
            System.out.println("La fecha de graduacion nueva: ");
            String nombre = sc.nextLine();
            System.out.println("La persona nueva: ");
            String apellido = sc.nextLine();
            System.out.println("La profesion nueva: ");
            String edad = sc.nextLine();
            Person person = Person.builder().build();
            Profession profession = Profession.builder().build();
            LocalDate date = LocalDate.parse(nombre);
            Study study = new Study(person, profession, date, identification);
            study = studyInputPort.edit(Study.builder().build(), Integer.parseInt(old_nombre), 1);
            System.out.println(personaMapperCli.fromDomainToAdapterCli(study, new PersonaModelCli(person), new ProfesionModelCli(profession)));
        } catch (NoExistException e) {
            log.warn(e.getMessage());
        }
    }

    public void eliminar(Scanner sc) {
        log.info("Into eliminar PersonaEntity in Input Adapter");
        try {
            sc = new Scanner(System.in);
            System.out.println("La profesion actual: ");
            String old_identification = sc.nextLine();
            System.out.println("La persona actual: ");
            String old_nombre = sc.nextLine();
            System.out
                    .println(studyInputPort.drop(Integer.parseInt(old_identification), Integer.parseInt(old_nombre)));
        } catch (NoExistException e) {
            log.warn(e.getMessage());
        }
    }

}
