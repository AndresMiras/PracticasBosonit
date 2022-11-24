package com.example.block7crudvalidation.student.infraestructure.repository;

import com.example.block7crudvalidation.base.infraestructure.repository.BaseRepository;
import com.example.block7crudvalidation.student.entity.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends BaseRepository<Student, Long> {
}