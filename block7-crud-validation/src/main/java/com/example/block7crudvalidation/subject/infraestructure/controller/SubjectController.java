package com.example.block7crudvalidation.subject.infraestructure.controller;

import com.example.block7crudvalidation.base.infraestructure.controller.BaseControllerImpl;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.subject.infraestructure.dto.SubjectInPutDTO;
import com.example.block7crudvalidation.subject.service.SubjectServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Component
@RestController
@RequestMapping(value = "subject")
public class SubjectController extends BaseControllerImpl<Subject, SubjectServiceImp> {

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object save(@Valid @RequestBody SubjectInPutDTO subjectInPutDTO) {
        return CustomError.buildWithObject(201, "Entity:" + subjectInPutDTO + " was created!", service.save(subjectInPutDTO));
    }

    @PutMapping("/update/{subject_id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object update(@Valid @RequestBody SubjectInPutDTO subjectInPutDTO, @PathVariable Long subject_id) {
        return CustomError.buildWithObject(201, "Entity:" + subjectInPutDTO + " was created!", service.update(subjectInPutDTO, subject_id));
    }

}
