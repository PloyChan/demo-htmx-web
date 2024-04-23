package org.example.demohtmlweb.controller;

import org.example.demohtmlweb.domain.Major;
import org.example.demohtmlweb.repo.MajorRepo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/major")
public class MajorController {
    private final MajorRepo majorRepo;

    public MajorController(MajorRepo majorRepo) {
        this.majorRepo = majorRepo;
    }

    @GetMapping()
    String get(Model model) {
        model.addAttribute("majors", this.majorRepo.findAll());
        return "major";
    }
    @GetMapping(value = "/add")
    String editUrl(Model model) {
        return "major-add";
    }
    @PostMapping()
    String save(@RequestParam("name") String name, Model model) {
        Major major = new Major();
        major.setName(name);
        this.majorRepo.save(major);
        model.addAttribute("majors",this.majorRepo.findAll());
        return "major";
    }
    @ResponseBody
    @DeleteMapping(value = "{id}", produces = MediaType.TEXT_HTML_VALUE)
    void delete(@PathVariable Integer id) {
        this.majorRepo.deleteById(id);
    }
}
