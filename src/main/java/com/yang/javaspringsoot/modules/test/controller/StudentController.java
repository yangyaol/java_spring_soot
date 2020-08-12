package com.yang.javaspringsoot.modules.test.controller;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.test.entity.Student;
import com.yang.javaspringsoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:
 * @create: 2020-08-12 20:33
 **/
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 127.0.0.1/api/insertStudent   ---post
     * {"studentName":"yangyao","studentCard":{"cardId":"1"}}
     * @param student
     * @return
     */
    @PostMapping(value = "insertStudent", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student){

       return studentService.insertStudent(student);
    }

}
