package ru.batyrkhanov.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.batyrkhanov.models.Person;
import ru.batyrkhanov.services.PeopleService;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

        return "people/index";
    }


//    @GetMapping
//    public List<Person> findAll() {
//        return peopleService.findAll();
//    }


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());

        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person) {

//        personValidator.validate(person, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "people/edit";
//        }

        peopleService.save(person);
        return"redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));

        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id) {

//       personValidator.validate(person, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "people/edit";
//        }
        peopleService.update(id, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);

        return "redirect:/people";
    }
}
