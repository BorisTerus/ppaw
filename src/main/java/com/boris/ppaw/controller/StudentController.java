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
public class StudentController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/getStudentGrades")
    public String showStudentGrades(Model model) {
        model.addAttribute("titlu", "Afişează notele");
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        return "pages/getStudentGrades";
    }

    @PostMapping(value = "/getStudentGrades")
    public String getStudentGrades(Model model, Grade grade, Errors errors) {

        model.addAttribute("titlu", "Afişează notele");
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        if (errors.hasErrors()) {
            model.addAttribute("rez", "<b><font color=\"red\">Nu are note</font></b>");
            return "pages/getStudentGrades";
        }

        String rezultat = "Notele obţinute de studentul " + grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName();
        model.addAttribute("Grades", gradeService.getAllGradesByStudent(grade.getStudent()));
        model.addAttribute("rez", rezultat);

        return "pages/getStudentGrades";
    }

}
