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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(value = "/insertGrade")
    public String insertGrade(Model model) {
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        return "pages/insertGrade";
    }

    @PostMapping(value = "/insertGrade")
    public String addGrade(@Valid @ModelAttribute("newGrade") Grade grade, Errors errors, Model model) {
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("newGrade", new Grade());
        if (errors.hasErrors()) {
            model.addAttribute("rez", "<b><font color=\"red\">Nu sunt note/font></b>");
            return "pages/insertGrade";
        }
        String rezultat = "";
        try {
            gradeService.addGrade(grade);
            rezultat = String.format("Studentul %s are nota %d la disciplina %s",
                    grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName(), grade.getGrade(), grade.getCourse().getName());
        } catch (Exception e) {
            rezultat = "Studentul deja are nota la aceasta disciplina";
        }
        model.addAttribute("rez", rezultat);
        return "pages/insertGrade";
    }

    @GetMapping(value = "/showGrades")
    public String showGrades(Model model) {
        model.addAttribute("Title", "Afisare operatii");
        model.addAttribute("Courses", courseRepository.findAll());
        model.addAttribute("Students", studentRepository.findAll());
        model.addAttribute("Grades", gradeService.getAllGrades());
        return "pages/showGrades";
    }
}
