package com.example.block7crudvalidation.teacher.service;

import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.base.service.BaseServiceImpl;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long> implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public TeacherServiceImpl(BaseRepository<Teacher, Long> baseRepository) {
        super(baseRepository);
    }
}
