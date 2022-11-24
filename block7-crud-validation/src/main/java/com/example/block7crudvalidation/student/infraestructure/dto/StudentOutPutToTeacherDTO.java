package com.example.block7crudvalidation.student.infraestructure.dto;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTOResponse;
import com.example.block7crudvalidation.student.entity.Student;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentOutPutToTeacherDTO {
    private Long id;
    private PersonDTOResponse personData;
    private Integer hoursPerWeek;
    private String comments;
    private String branch;

    public StudentOutPutToTeacherDTO(Student student) {
        id = student.getId();
        personData = student.getPerson().convertToPersonDTOResponse();
        hoursPerWeek = student.getHoursPerWeek();
        comments = student.getComments();
        branch = student.getBranch();
    }
}
