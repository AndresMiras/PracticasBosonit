package com.example.block7crudvalidation.person.infraestructure.dto;

import com.example.block7crudvalidation.person.entity.Person;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutFullDTO;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutPutFullTeacherDTO extends PersonDTOResponse {

    private TeacherOutPutFullDTO teacher;

    public PersonOutPutFullTeacherDTO(Person person, TeacherOutPutFullDTO teacherOutPutFullDTO) {
        super(person);
        this.teacher = teacherOutPutFullDTO;
    }
}
