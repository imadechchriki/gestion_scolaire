package com.example.gestion_scolaire.controllers;


import com.example.gestion_scolaire.entities.Cours;
import com.example.gestion_scolaire.services.CoursService;
import com.example.gestion_scolaire.services.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/views/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @Autowired
    private FiliereService filiereService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("coursList", coursService.getAll());
        return "views/cours/cours";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("filieres", filiereService.getAll());
        return "views/cours/add-cours";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Cours cours) {
        coursService.save(cours);
        return "redirect:/views/cours";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        coursService.delete(id);
        return "redirect:/views/cours";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Cours cours = coursService.getById(id);
        model.addAttribute("cours", cours);
        model.addAttribute("filieres", filiereService.getAll());

        if (cours != null && cours.getEleves() != null) {
            model.addAttribute("nombreEleves", cours.getEleves().size());
        }

        return "views/cours/edit-cours";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Cours cours) {
        coursService.update(cours.getId(), cours);
        return "redirect:/views/cours";
    }
}