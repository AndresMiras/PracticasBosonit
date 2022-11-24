package com.example.block7crudvalidation.person.infraestructure.dto;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutFullDTO;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutPutFullStudentDTO extends PersonDTOResponse {

    private StudentOutPutFullDTO student;

    public PersonOutPutFullStudentDTO(Person person, StudentOutPutFullDTO studentOutPutFullDTO) {
        super(person);
        this.student = studentOutPutFullDTO;
    }
}
