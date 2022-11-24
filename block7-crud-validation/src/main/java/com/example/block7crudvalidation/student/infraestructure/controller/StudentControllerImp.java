package com.example.block7crudvalidation.student.infraestructure.controller;

import com.example.block7crudvalidation.base.infraestructure.controller.BaseControllerImpl;
import com.example.block7crudvalidation.exceptions.CustomError;
import com.example.block7crudvalidation.student.entity.Student;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInPutDTO;
import com.example.block7crudvalidation.student.service.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Component
@RestController
@RequestMapping(value = "student")
public class StudentControllerImp extends BaseControllerImpl<Student, StudentServiceImpl> {


    @PostMapping("/insert/person/{person_id}/teacher/{teacher_id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object save( @Valid @RequestBody StudentInPutDTO studentInPutDTO, @PathVariable Long person_id, @PathVariable Long teacher_id ) {
        return CustomError.buildWithObject(201, "Entity:" + studentInPutDTO + " was created!", service.save(studentInPutDTO, person_id, teacher_id));
    }

    @PutMapping("/{student_id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object update( @Valid @RequestBody StudentInPutDTO studentInPutDTO, @PathVariable Long student_id ) {
        return CustomError.buildWithObject(205, "Entity:" + studentInPutDTO + " was updated!", service.update(studentInPutDTO, student_id));
    }

    @PostMapping("/update/{student_id}/teacher/{teacher_id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object setTeacher( @PathVariable Long student_id, @PathVariable Long teacher_id ) {
        return CustomError.buildWithObject(201, "Entity: was updated! teacher was set", service.setTeacher(student_id, teacher_id));
    }

    @PostMapping("/subjects/add/{student_id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Object setSubjects(@Valid @RequestBody List<Long> subject_ids, @PathVariable Long student_id ) {
        return CustomError.buildWithObject(201, "Entity: was added! subjects was added", service.addSubjects(subject_ids, student_id));
    }

    @DeleteMapping("/{student_id}/subject/{subject_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Object deleteSubject(@PathVariable Long student_id, @PathVariable Long subject_id) {
        return CustomError.buildWithObject(204, "Entity: was deleted! subject was deleted", service.deleteSubject(student_id, subject_id));
    }

}
