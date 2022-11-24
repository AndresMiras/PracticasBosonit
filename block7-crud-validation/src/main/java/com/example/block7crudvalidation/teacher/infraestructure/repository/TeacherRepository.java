package com.example.block7crudvalidation.teacher.infraestructure.repository;

import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends BaseRepository<Teacher, Long> {
}
