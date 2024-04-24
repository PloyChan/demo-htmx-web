package org.example.demohtmlweb.controller;

import org.example.demohtmlweb.domain.Student;
import org.example.demohtmlweb.enums.Gender;
import org.example.demohtmlweb.repo.MajorRepo;
import org.example.demohtmlweb.repo.StudentRepo;
import org.example.demohtmlweb.spec.StudentSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentRepo studentRepo;
    private final MajorRepo majorRepo;

    public StudentController(StudentRepo studentRepo, MajorRepo majorRepo) {
        this.studentRepo = studentRepo;
        this.majorRepo = majorRepo;
    }

    @GetMapping()
    String getAll(Model model) {
        model.addAttribute("students", this.studentRepo.findAll());
        model.addAttribute("majors", this.majorRepo.findAll());
        return "student";
    }
    @GetMapping(value = "/add")
    String addStudent(Model model) {
        model.addAttribute("majors", this.majorRepo.findAll());
        model.addAttribute("gender",Gender.values());
        return "student-add";
    }

    @PostMapping("/add")
    String save(Student student, Model model) {
        Student save = this.studentRepo.save(student);
        model.addAttribute("students",this.studentRepo.findById(save.getId()).get());
        return "student :: detail";
    }

    @GetMapping(value = "edit/{id}")
    String editStudent(@PathVariable Integer id, Model model) {
        model.addAttribute("majors", this.majorRepo.findAll());
        model.addAttribute("student" ,this.studentRepo.findById(id).get());
        model.addAttribute("gender",Gender.values());
        return "student-add";
    }

    @PostMapping("/edit/{id}")
    String edit(Student student, Model model) {
        Student save = this.studentRepo.save(student);
        model.addAttribute("students",this.studentRepo.findById(save.getId()).get());
        return "student :: detail";
    }

    @GetMapping(value = "search")
    String searchStudent(Model model, @RequestParam(required = false) String name, @RequestParam(required = false) Integer major) {
        Specification<Student> specification = Specification.where(null);
        specification = specification.and(StudentSpec.nameLike(name));
        specification = specification.and(StudentSpec.majorEq(major));
        model.addAttribute("students", this.studentRepo.findAll(specification));
        return "student :: detail";
    }

    @ResponseBody
    @DeleteMapping(value = "{id}", produces = MediaType.TEXT_HTML_VALUE)
    void delete(@PathVariable Integer id) {
        this.studentRepo.deleteById(id);
    }

}
