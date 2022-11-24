package com.example.block7crudvalidation.subject.service;

import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.subject.infraestructure.dto.SubjectInPutDTO;

public interface SubjectService {
    Subject save(SubjectInPutDTO subjectInPutDTO);
    Subject update(SubjectInPutDTO subjectInPutDTO, Long subject_id);
}
