package com.yang.javaspringsoot.modules.test.repository;

import com.yang.javaspringsoot.modules.test.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author:
 * @create: 2020-08-12 20:22
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

    List<Student> findByStudentName(String studentName);

//    @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard = ?2")
//    @Query(value = "select s from  Student s where s.studentName = :studentName" + " and s.studentCard.cardId = : cardId")
    @Query(nativeQuery = true,
            value = "select * from h_student where student_name = :studentName " +"and card_id = :cardId")
    List<Student> findStudentByParams(@Param("studentName") String studentName,
                                    @Param("cardId") int cardId);
}
