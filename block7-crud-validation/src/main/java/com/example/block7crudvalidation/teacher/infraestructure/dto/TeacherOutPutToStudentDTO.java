package com.example.block7crudvalidation.teacher.infraestructure.dto;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherOutPutToStudentDTO {

    private PersonDTOResponse personData;
    private String comments;
    private String branch;
}
