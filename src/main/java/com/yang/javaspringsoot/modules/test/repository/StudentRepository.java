package com.yang.javaspringsoot.modules.test.repository;

import com.yang.javaspringsoot.modules.test.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:
 * @create: 2020-08-12 20:22
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
}
