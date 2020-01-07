package com.boris.ppaw.service;

import com.boris.ppaw.model.Course;
import com.boris.ppaw.model.Grade;
import com.boris.ppaw.model.Student;
import com.boris.ppaw.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    public List<Grade> getAllGradesByStudent(Student student) {
        return gradeRepository.findByStudentId(student.getId());
    }

    public List<Grade> getAllGradesByCourse(Course course) {
        return gradeRepository.findByCourseId(course.getId());
    }
}
