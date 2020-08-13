package com.yang.javaspringsoot.modules.test.service.impl;

import com.yang.javaspringsoot.modules.common.vo.Result;
import com.yang.javaspringsoot.modules.common.vo.SearchVo;
import com.yang.javaspringsoot.modules.test.entity.Student;
import com.yang.javaspringsoot.modules.test.repository.StudentRepository;
import com.yang.javaspringsoot.modules.test.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: 杨尧
 * @create: 2020-08-12 20:31
 **/
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Result<Student> insertStudent(Student student) {
        student.setCreateDate(LocalDateTime.now());
        studentRepository.saveAndFlush(student);
        return new Result<Student>(Result.ResultStatus.SUCCESS.status,
                "insert  success", student);
    }

    @Override
    @Transactional
    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    @Transactional
    public Page<Student> findStudentBySearchVo(SearchVo searchVo) {
        String orderBy= StringUtils.isBlank(searchVo.getOrderBy())?
                "studentId":searchVo.getOrderBy();
        Sort.Direction direction=StringUtils.isBlank(searchVo.getSort()) ||
                searchVo.getSort().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC: Sort.Direction.DESC;

        Sort sort=new Sort(direction, orderBy);

        Pageable pageable= PageRequest.of(searchVo.getCurrentPage()-1,searchVo.getPageSize(),sort);
        Student student=new Student();
        student.setStudentName(searchVo.getKeyWord());
        ExampleMatcher matcher=ExampleMatcher.matching()
                .withMatcher("studentName", match ->match.contains())
                .withIgnoreCase("studentId");
       Example<Student> example=Example.of(student,matcher);

        return studentRepository.findAll(example, pageable);
    }

    @Override
    public List<Student> findall() {
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = new Sort(direction,"studentName");
        return studentRepository.findAll(sort);
    }

    @Override
    @Transactional
    public List<Student> findStudentByParams(String studentName,int cardId) {
        if (cardId > 0){
            return studentRepository.findStudentByParams(studentName,cardId);
        }else {
            return null;
        }

    }
}
