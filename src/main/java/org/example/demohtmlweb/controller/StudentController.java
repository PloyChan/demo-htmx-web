package org.example.demohtmlweb.controller;

import org.example.demohtmlweb.domain.Major;
import org.example.demohtmlweb.domain.Student;
import org.example.demohtmlweb.enums.Gender;
import org.example.demohtmlweb.repo.MajorRepo;
import org.example.demohtmlweb.repo.StudentRepo;
import org.example.demohtmlweb.spec.StudentSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping(value = "add/{id}", produces = MediaType.TEXT_HTML_VALUE)
    String editUrl(@PathVariable Integer id, Model model) {
        model.addAttribute("majors", this.majorRepo.findAll());
        model.addAttribute("student" ,this.studentRepo.findById(id).get());
        model.addAttribute("gender",Gender.values());
        return "student-add";
    }

    @PostMapping(value = "/save-student")
    String add(@RequestParam("name") String name, @RequestParam("gpa") Float gpa, @RequestParam("gender")Gender gender,
               @RequestParam("major")Integer majorId, Model model) {
        Optional<Major> major = this.majorRepo.findById(majorId);
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setGpa(gpa);
        major.ifPresent(student::setMajor);
        this.studentRepo.save(student);
        model.addAttribute("students",this.studentRepo.findAll());
        return  "student";
    }
    @PostMapping(value = "/save-student/{id}")
    String edit(@PathVariable Integer id, @RequestParam("name") String name, @RequestParam("gpa") Float gpa, @RequestParam("gender")Gender gender,
               @RequestParam("major")Integer majorId, Model model) {
        Student student  = studentRepo.findById(id).get();
        Optional<Major> major = this.majorRepo.findById(majorId);
        student.setName(name);
        student.setGender(gender);
        student.setGpa(gpa);
        major.ifPresent(student::setMajor);
        this.studentRepo.save(student);
        model.addAttribute("students",this.studentRepo.findAll());
        return  "student";
    }

    @PostMapping(value = "search")
    String search(Model model, @RequestParam("name") String name, @RequestParam("major") Integer majorId) {
        Specification<Student> specification = Specification.where(null);
        specification = specification.and(StudentSpec.nameLike(name));
        specification = specification.and(StudentSpec.majorEq(majorId));
        model.addAttribute("students", this.studentRepo.findAll(specification));
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
