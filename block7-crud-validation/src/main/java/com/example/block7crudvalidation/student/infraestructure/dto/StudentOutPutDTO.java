package com.example.block7crudvalidation.student.infraestructure.dto;

import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class StudentOutPutDTO {
    private Long id;
    private Integer hoursPerWeek;
    private String comments;
    private TeacherOutPutDTO teacher;
    private String branch;
    private List<Subject> subjects;

    public StudentOutPutDTO(Student student) {
        this.id = student.getId();
        this.hoursPerWeek = student.getHoursPerWeek();
        this.comments = student.getComments();
        this.teacher = student.getTeacher().convertToTeacherOutPutDTO();
        this.branch = student.getBranch();
        this.subjects = student.getSubjects();
    }
}
