package com.boris.ppaw.controller;

import com.boris.ppaw.model.Grade;
import com.boris.ppaw.repository.CourseRepository;
import com.boris.ppaw.repository.StudentRepository;
import com.boris.ppaw.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CourseController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/getCourseGrades")
    public String showCourseGrades(Model model) {
        model.addAttribute("titlu", "Afişează notele");
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        return "pages/getCourseGrades";
    }

    @PostMapping(value = "/getCourseGrades")
    public String getCourseGrades(Model model, Grade grade, Errors errors) {

        model.addAttribute("titlu", "Afişează notele");
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        if (errors.hasErrors()) {
            model.addAttribute("rez", "<b><font color=\"red\">Nu sunt note la această disciplină</font></b>");
            return "pages/getCourseGrades";
        }

        String rezultat = "Notele obţinute la " + grade.getCourse().getName();
        model.addAttribute("Grades", gradeService.getAllGradesByCourse(grade.getCourse()));
        model.addAttribute("rez", rezultat);

        return "pages/getCourseGrades";
    }

}
