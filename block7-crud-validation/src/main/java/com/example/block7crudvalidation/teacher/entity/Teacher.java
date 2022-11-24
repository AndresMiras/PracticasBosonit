package com.example.block7crudvalidation.teacher.entity;
import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutToTeacherDTO;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutDTO;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutFullDTO;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutToStudentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "teacher")
@Entity
public class Teacher extends Base {

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_person", nullable = false, unique = true)
    private Person person;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.REMOVE}) // Es necesario relacionar el tipo cascada remove para poder habilitar que se eliminen los profesores o personas.
    @JsonBackReference // Hace referencia a @JsonManagedReference evitando la recirculación.
    private List<Student> students;

    @Column
    private String comments;

    @Column(nullable = false)
    private String branch;


    public Teacher(Person person, String comments, String branch) {
        this.person = person;
        this.comments = comments;
        this.branch = branch;
    }

    public Teacher( String comments, String branch) {
        this.comments = comments;
        this.branch = branch;
    }

    public TeacherOutPutFullDTO convertToTeacherOutPutFullDTO() {

        // Convierto los datos de salida a un DTO de estudiante refinado para la entidad de profesor, que se verán cuando busquemos una persona que sea profesor y tenga alumnos.

        List<StudentOutPutToTeacherDTO> studentOutPutToTeacherDTOS = students
                .stream()
                .map(student -> student.convertToStudentOutPutToTeacherDTO())
                .collect(Collectors.toList());

        return new TeacherOutPutFullDTO(comments, branch, studentOutPutToTeacherDTOS);
    }

    public TeacherOutPutToStudentDTO convertToTeacherOutPutToStudentDTO() {
        return new TeacherOutPutToStudentDTO(person.convertToPersonDTOResponse(),comments, branch);
    }

    public TeacherOutPutDTO convertToTeacherOutPutDTO() {
        return new TeacherOutPutDTO(this);
    }
}
