package com.example.block7crudvalidation.student.service;

import com.example.block7crudvalidation.student.infraestructure.dto.StudentInPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutDTO;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutPutFullDTO;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutPutDTO;

import java.util.List;

public interface StudentService {
    StudentOutPutDTO save(StudentInPutDTO studentInPutDTO, Long person_id, Long teacher_id);
    StudentOutPutDTO update(StudentInPutDTO studentInPutDTO, Long student_id);
    TeacherOutPutDTO setTeacher(Long student_id, Long teacher_id);
    StudentOutPutFullDTO addSubjects(List<Long> subject_ids ,Long student_id);
}
