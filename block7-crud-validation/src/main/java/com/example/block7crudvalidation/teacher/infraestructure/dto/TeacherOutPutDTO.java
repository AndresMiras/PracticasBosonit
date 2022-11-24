package com.example.block7crudvalidation.teacher.infraestructure.dto;

import com.example.block7crudvalidation.teacher.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TeacherOutPutDTO implements Serializable {
    private String comments;
    private String branch;

    public TeacherOutPutDTO(Teacher teacher) {
        this.comments = teacher.getComments();
        this.branch = teacher.getBranch();
    }

}
