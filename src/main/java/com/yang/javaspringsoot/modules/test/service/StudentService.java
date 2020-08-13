package com.yang.javaspringsoot.modules.test.service;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Result<Student> insertStudent(Student student);

    Student findStudentById(int studentId);

    Page<Student> findStudentBySearchVo(SearchVo searchVo);

    List<Student>  findall();

    List<Student> findStudentByParams(String studentName,int cardId);

}
