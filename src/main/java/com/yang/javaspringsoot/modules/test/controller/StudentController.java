package com.yang.javaspringsoot.modules.test.controller;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.modules.test.entity.Student;
import com.yang.javaspringsoot.modules.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 127.0.0.1/api/selectStudent/1      -----get
     * @param studentId
     * @return
     */
    @GetMapping("/selectStudent/{studentId}")
    public Student findStudentById(@PathVariable int studentId){

        return studentService.findStudentById(studentId);
    }

    /**
     * 127.0.0.1/api/selectStudentDesc    -----post
     * {"currentPage":"1","pageSize":"5","keyWord":"白","orderBy":"studentName","sort":"desc"}
     * @param searchVo
     * @return
     */
    @PostMapping(value = "/selectStudentDesc", consumes = "application/json")
    public Page<Student> findStudentBySearchVo(@RequestBody SearchVo searchVo){
        return studentService.findStudentBySearchVo(searchVo);
    }

    /**
     * 127.0.0.1/api/findall   ----get
     * @return
     */
    @GetMapping("findall")
    public List<Student> findall(){

        return studentService.findall();
    }

    /**
     * 127.0.0.1/api/findByparams?studentName=yangyao
     * @param studentName
     * @param cardId
     * @return
     */
    @GetMapping("/findByparams")
    public List<Student> findStudentByParams(
            @RequestParam String studentName,
            @RequestParam (required = false,defaultValue = "0")int cardId){

        return studentService.findStudentByParams(studentName,cardId);
    }



}
