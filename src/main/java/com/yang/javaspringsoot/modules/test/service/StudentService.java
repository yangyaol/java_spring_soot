package com.yang.javaspringsoot.modules.test.service;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.test.entity.Student;

public interface StudentService {

    Result<Student> insertStudent(Student student);
}
