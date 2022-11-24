package com.example.block7crudvalidation.subject.service;

import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.base.service.BaseServiceImpl;
import com.example.block7crudvalidation.exceptions.EntityNotFoundException;
import com.example.block7crudvalidation.subject.entity.Subject;
import com.example.block7crudvalidation.subject.infraestructure.dto.SubjectInPutDTO;
import com.example.block7crudvalidation.subject.infraestructure.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImp extends BaseServiceImpl<Subject, Long> implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    public SubjectServiceImp(BaseRepository<Subject, Long> baseRepository) {
        super(baseRepository);
    }

    @Override
    public Subject save(SubjectInPutDTO subjectInPutDTO) {
        return subjectRepository.save(subjectInPutDTO.entity());
    }

    @Override
    public Subject update(SubjectInPutDTO subjectInPutDTO, Long subject_id) {
        subjectRepository.findById(subject_id).orElseThrow(() -> new EntityNotFoundException("Subject with Id:" + subject_id + ", don't found."));
        Subject subjectEntity = subjectInPutDTO.entity();
        return subjectRepository.save(new Subject(subject_id, subjectEntity.getName(), subjectEntity.getComment(), subjectEntity.getStartDate(), subjectEntity.getEndDate()));
    }
}
