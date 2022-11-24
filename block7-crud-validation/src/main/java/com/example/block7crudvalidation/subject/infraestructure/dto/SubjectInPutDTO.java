package com.example.block7crudvalidation.subject.infraestructure.dto;

import com.example.block7crudvalidation.subject.entity.Subject;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class SubjectInPutDTO implements Serializable {

    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "comment is mandatory")
    private String comment;
    private Date startDate;
    private Date endDate;

    public SubjectInPutDTO(String name, String comment, String startDate, String endDate) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        this.name = name;
        this.comment = comment;
        this.startDate = format.parse(startDate);
        this.endDate = format.parse(endDate);
    }

    public Subject entity(){
        return new Subject(name, comment, startDate, endDate);
    }
}
