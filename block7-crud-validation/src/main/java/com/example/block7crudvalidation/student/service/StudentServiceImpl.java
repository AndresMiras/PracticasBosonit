package com.example.block7crudvalidation.student.service;

import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.base.service.BaseServiceImpl;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutFullDTO;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.subject.infraestructure.repository.SubjectRepository;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutDTO;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, Long> implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    public StudentServiceImpl(BaseRepository<Student, Long> baseRepository) {
        super(baseRepository);
    }

    public StudentOutPutDTO save(StudentInPutDTO studentInPutDTO, Long person_id, Long teacher_id) {

        // If person exist, can continue.
        Person person = personRepository.findById(person_id).orElseThrow(() -> new EntityNotFoundException("Student can't be created, Person Id:" + person_id + ", don't found..."));

        // If teacher exist, can continue.
        Teacher teacher = teacherRepository.findById(teacher_id).orElseThrow(() -> new EntityNotFoundException("Student can't be created, Teacher Id:" + teacher_id + ", don't found..."));

        // Now we can add a new student.
        Student student = studentRepository.save(studentInPutDTO.entity(person, teacher));

        log.info("Student created success: " + student.convertToStudentOutPutDTO().toString());

        return student.convertToStudentOutPutDTO();
    }

    public StudentOutPutDTO update(StudentInPutDTO studentInPutDTO, Long student_id) {

        // If student exist, can continue.
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, student Id:" + student_id + ", don't found..."));

        // Now we can add a new student.
        student = studentRepository.save(student);

        log.info("Student update success: " + student.convertToStudentOutPutDTO().toString());

        return student.convertToStudentOutPutDTO();
    }

    public TeacherOutPutDTO setTeacher(Long student_id, Long teacher_id) {

        // If student exist, can continue.
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, Student Id:" + student_id + ", don't found..."));


        // If teacher exist, can continue.
        Teacher teacher = teacherRepository.findById(teacher_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, Teacher Id:" + teacher_id + ", don't found..."));

        // Update teacher.
        student.setTeacher(teacher);

        log.info("Student update success: " + student.convertToStudentOutPutDTO().toString());
        studentRepository.save(student);
        return teacher.convertToTeacherOutPutDTO();
    }

    public StudentOutPutFullDTO addSubjects(List<Long> subject_ids, Long student_id) {

        // If student exist, can continue.
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, Student Id:" + student_id + ", don't found..."));

        // Read the subjects ids one by one.
        for (Long subject_id : subject_ids) {

            // If the subject is not in the list, throws an error.
            Subject subject = subjectRepository.findById(subject_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, Subject Id:" + subject_id + ", don't found..."));

            // get the students to one subject.
            List<Student> studentsSubject = subject.getStudentsSubject();

            boolean studentExist = false;

            if (!studentsSubject.isEmpty()) {
                for (Student studentSubject : studentsSubject) {

                    // Need a unique student_id list!
                    if (studentSubject.getId().equals(student.getId())) {
                        studentExist = true;
                    };
                }
            }

            // If student don't exist in the array list.
            if(!studentExist) studentsSubject.add(student);

            // Save the students array in repository.
            subject.setStudentsSubject(studentsSubject);

            // Save one by one all students in the subject repository.
            subjectRepository.save(subject);
        }

        return student.convertToStudentOutPutDTOFull();
    }

    public StudentOutPutFullDTO deleteSubject(Long student_id, Long subject_id) {

        // If subject exist, can continue.
        Subject subjectRepo = subjectRepository.findById(subject_id).orElseThrow(() -> new EntityNotFoundException("Student can't be update, Subject Id:" + subject_id + ", don't found..."));

        // Save actual List of subjects.
        List<Student> studentsSubject = subjectRepo.getStudentsSubject();

        // Create one variable to save de index of subject, if exist them is saved.
        Student studentToRemove = studentsSubject.stream()
                .filter(student -> student.getId().equals(student_id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student_subject in subject can't be deleted, because student_id:" + student_id + ", don't found..."));

        // Now delete de subject.
        studentsSubject.remove(studentToRemove);

        // Set the new list of subjects and save it.
        subjectRepo.setStudentsSubject(studentsSubject);

        subjectRepository.save(subjectRepo);
        log.info("Student_subject was deleted!:" + studentToRemove);
        return null;
    }

}
