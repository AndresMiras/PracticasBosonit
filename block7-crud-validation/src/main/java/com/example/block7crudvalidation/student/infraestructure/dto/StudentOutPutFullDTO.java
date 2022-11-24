package com.example.block7crudvalidation.student.infraestructure.dto;

import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutToStudentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class StudentOutPutFullDTO {
    private Long id;
    private Integer hoursPerWeek;
    private String comments;
    private TeacherOutPutToStudentDTO teacher;
    private String branch;
    private List<Subject> subjects;
}
