package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Employer;
import org.launchcode.javawebdevtechjobspersistent.models.data.EmployerRepository;
import org.launchcode.javawebdevtechjobspersistent.models.data.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public String displayAllEmployers(Model model) {
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "employers/index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute("name", "Add Employer");
        model.addAttribute(new Employer());

        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("name", "Add Employer");
            return "employers/add";
        }
        employerRepository.save(newEmployer);
        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable Integer employerId) {

        if (employerId == null) {
            model.addAttribute("title", "All Employers");
            model.addAttribute("employers", employerRepository.findAll());
        } else {
            Optional<Employer> optEmployer = employerRepository.findById(employerId);

            if(optEmployer.isPresent()) {
                model.addAttribute("title", "Employer: " + optEmployer.get().getName());
            } else {
                model.addAttribute("title", optEmployer.get().getId());
            }
            model.addAttribute("employers", optEmployer.get().getJobs());

        }
        return "redirect:../";
    }
}
