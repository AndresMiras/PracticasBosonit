package com.example.block7crudvalidation.student.entity;

import com.example.block7crudvalidation.base.entity.Base;
import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutFullDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutToTeacherDTO;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student")
@Entity
public class Student extends Base {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) // Mantiene los cambios de person actualizados.
    @JoinColumn(name = "id_person", nullable = false, unique = true) // Al ser único un estudiante no puede ser profesor y en viceversa.
    private Person person;

    @Column(name = "hours_peer_week", nullable = false)
    private Integer hoursPerWeek;

    @Column
    private String comments;

    // Se crea una clave foranea para un profesor, de esta forma el dueño de la propagación sería estudiante. Sacando una lista de estudiantes mapeada en la clase Teacher
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_teacher")
    @JsonManagedReference // Evito que JSON me cree una referencia circular al serializar la referencia.
    private Teacher teacher;

    @Column( nullable = false)
    private String branch;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "studentsSubject")
    private List<Subject> subjects;

    public Student(Person person, Integer hoursPerWeek, String comments, String branch) {
        this.person = person;
        this.hoursPerWeek = hoursPerWeek;
        this.comments = comments;
        this.branch = branch;
    }

    @JsonCreator
    public Student(Person person, Teacher teacher, Integer hoursPerWeek, String comments, String branch) {
        this.person = person;
        this.teacher = teacher;
        this.hoursPerWeek = hoursPerWeek;
        this.comments = comments;
        this.branch = branch;
    }

    public Student(Integer hoursPerWeek, String comments, String branch) {
        this.hoursPerWeek = hoursPerWeek;
        this.comments = comments;
        this.branch = branch;
    }

    public StudentOutPutFullDTO convertToStudentOutPutDTOFull() {
        return new StudentOutPutFullDTO(super.getId(), hoursPerWeek, comments, teacher.convertToTeacherOutPutToStudentDTO(), branch, subjects);
    }

    public StudentOutPutToTeacherDTO convertToStudentOutPutToTeacherDTO() {
        return new StudentOutPutToTeacherDTO(this);
    }

    public StudentOutPutDTO convertToStudentOutPutDTO() {
        return new StudentOutPutDTO(this);
    }

}


