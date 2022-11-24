package com.example.block7crudvalidation;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTORequest;
import com.example.block7crudvalidation.person.service.PersonService;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutDTO;
import com.example.block7crudvalidation.student.service.StudentService;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.subject.infraestructure.dto.SubjectInPutDTO;
import com.example.block7crudvalidation.subject.service.SubjectService;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class ApplicationDataStarter implements CommandLineRunner {

    @Autowired
    private PersonService personServiceImpl;

    @Autowired
    private TeacherService teacherServiceImpl;

    @Autowired
    private StudentService studentServiceImpl;

    @Autowired
    private SubjectService subjectServiceImpl;



    @Override
    public void run(String... args) throws Exception {

        // Cargo los datos para tenerlos disponibles en la aplicación.

        // Creo algunas personas.
        Person person1 = personServiceImpl.createPerson(new PersonDTORequest( "Alberto", "Martinez", "asdfasd6f546", "albmar@hotmail.com", "parroquia de rodís Nº5", "Soria", "http://www.lafsos.com"));
        Person person2 = personServiceImpl.createPerson(new PersonDTORequest( "José", "Sande", "asdfasd6f546", "josez@hotmail.com", "avenida, pamplona seca Nº5", "Madrid", "http://www.lagos.com"));
        Person person3 = personServiceImpl.createPerson(new PersonDTORequest( "Ana", "Gómez", "dfg456585", "anago@hotmail.com", "mato grande Nº5", "A Coruña", "http://www.discalia.com"));
        Person person4 = personServiceImpl.createPerson(new PersonDTORequest( "José", "García", "651fas215", "anagar@hotmail.com", "porriño N2", "A Coruña", "http://www.jasca.com"));
        Person person5 = personServiceImpl.createPerson(new PersonDTORequest( "Alba", "Zúñiga", "ouhsdjf65", "zuga@hotmail.com", "corredoira N6", "Lugo", "http://www.sdfsd56.com"));

        // Creo algunos profesores y los guardo.
        Teacher teacher1 = teacherServiceImpl.save(new Teacher(person1, "Por valorar", "DEVOPS"));
        Teacher teacher2 = teacherServiceImpl.save(new Teacher(person2, "Senior", "DEVOPS"));

        // Creo algunos estudiantes y les asigno su profesor. Métodos implementados en el controlador.
        StudentInPutDTO student1 = new StudentInPutDTO( 20, "Comenzando", "FRONT");
        StudentOutPutDTO student1Out =studentServiceImpl.save(student1, person3.getId(), teacher1.getId());

        StudentInPutDTO student2 = new StudentInPutDTO( 40, "Profesional .NET", "BACK");
        StudentOutPutDTO student2Out = studentServiceImpl.save(student2, person4.getId(), teacher1.getId());

        StudentInPutDTO student3 = new StudentInPutDTO( 40, "Profesional NODE", "BACK");
        StudentOutPutDTO student3Out =studentServiceImpl.save(student3, person5.getId(), teacher2.getId());

        // Creo las asignaturas.
        SubjectInPutDTO subject1 = new SubjectInPutDTO("Matemáticas Básicas", "1-4 Secundaria", "2022/01/01", "2023/12/01");
        SubjectInPutDTO subject2 = new SubjectInPutDTO("Biología", "3-4-1 Bach", "2022/01/01", "2023/12/01");

        // Guardo las asignaturas.
        Subject subject1Entity = subjectServiceImpl.save(subject1);
        Subject subject2Entity = subjectServiceImpl.save(subject2);

        // Creo una colección con las ids de las asignaturas para asignárselas a los estudiantes.
        List<Long> subjects = new ArrayList<>();
        subjects.add(subject1Entity.getId());
        subjects.add(subject2Entity.getId());

        studentServiceImpl.addSubjects(subjects, student1Out.getId());
        studentServiceImpl.addSubjects(subjects, student2Out.getId());


        // ------------------------------------------------ Probar con el postman. ----------------------------------------------------------------

        // SELECT * FROM PERSON ; SELECT * FROM STUDENT ; SELECT * FROM SUBJECT ; SELECT * FROM STUDENT_SUBJECTS ; SELECT * FROM TEACHER ;

        // Ver el controller de Base modelo para update, insert, remove...

        // http://localhost:8080/person/full/3  (Obtiene el perfil de la estudiante)
        // http://localhost:8080/person/full/1  (Obtiene el perfil completo del profesor)
        // http://localhost:8080
        // http://localhost:8080
        // http://localhost:8080

    }
}
