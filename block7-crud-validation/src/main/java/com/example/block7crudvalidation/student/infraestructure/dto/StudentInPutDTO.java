package com.example.block7crudvalidation.student.infraestructure.dto;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class StudentInPutDTO implements Serializable {

    private Integer hoursPerWeek;
    private String comments;
    private String branch;

    public Student entity(Person person, Teacher teacher) {
        return new Student(person, teacher, hoursPerWeek, comments, branch);
    }
}
