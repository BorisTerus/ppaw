package com.boris.ppaw.repository;

import com.boris.ppaw.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {


    List<Grade> findByStudentId(int id);

    List<Grade> findByCourseId(int id);
}
