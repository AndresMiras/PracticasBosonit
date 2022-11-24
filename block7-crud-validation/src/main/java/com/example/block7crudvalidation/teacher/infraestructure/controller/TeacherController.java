package com.example.block7crudvalidation.teacher.infraestructure.controller;

import com.example.block7crudvalidation.base.infraestructure.controller.BaseControllerImpl;
import com.example.block7crudvalidation.teacher.entity.Teacher;
import com.example.block7crudvalidation.teacher.service.TeacherServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseControllerImpl<Teacher, TeacherServiceImpl> {
}
