package com.example.block7crudvalidation.teacher.infraestructure.dto;

import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutToTeacherDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class TeacherOutPutFullDTO extends TeacherOutPutDTO {

    private List<StudentOutPutToTeacherDTO> students;

    public TeacherOutPutFullDTO(String comments, String branch, List<StudentOutPutToTeacherDTO> studentOutPutDTOs) {
        super(comments, branch);
        this.students = studentOutPutDTOs;
    }
}
