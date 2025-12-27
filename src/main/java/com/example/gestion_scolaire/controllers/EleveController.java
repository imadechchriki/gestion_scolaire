package com.example.gestion_scolaire.controllers;



import com.example.gestion_scolaire.entities.Eleve;
import com.example.gestion_scolaire.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/views/eleves")
public class EleveController {

    @Autowired
    private EleveService eleveService;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("eleves", eleveService.getAll());
        return "views/eleves/eleves";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("eleve", new Eleve());
        model.addAttribute("filieres", filiereService.getAll());
        return "views/eleves/add-eleve";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Eleve eleve) {
        eleveService.save(eleve);
        return "redirect:/views/eleves";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eleveService.delete(id);
        return "redirect:/views/eleves";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("eleve", eleveService.getById(id));
        model.addAttribute("filieres", filiereService.getAll());
        return "views/eleves/edit-eleve";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Eleve eleve) {
        eleveService.update(eleve.getId(), eleve);
        return "redirect:/views/eleves";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Eleve eleve = eleveService.getById(id);
        model.addAttribute("eleve", eleve);

        // Cours disponibles (de la filière de l'élève)
        if (eleve.getFiliere() != null) {
            model.addAttribute("coursDisponibles",
                    coursService.getByFiliere(eleve.getFiliere().getId()));
        }

        return "views/eleves/details-eleve";
    }

    @GetMapping("/inscrire-cours/{eleveId}/{coursId}")
    public String inscrireCours(@PathVariable Long eleveId, @PathVariable Long coursId) {
        eleveService.inscrireCours(eleveId, coursId);
        return "redirect:/views/eleves/details/" + eleveId;
    }

    @GetMapping("/desinscrire-cours/{eleveId}/{coursId}")
    public String desinscrireCours(@PathVariable Long eleveId, @PathVariable Long coursId) {
        eleveService.desinscrireCours(eleveId, coursId);
        return "redirect:/views/eleves/details/" + eleveId;
    }

    @GetMapping("/filiere/{filiereId}")
    public String getByFiliere(@PathVariable Long filiereId, Model model) {
        model.addAttribute("eleves", eleveService.getByFiliere(filiereId));
        model.addAttribute("filiere", filiereService.getById(filiereId));
        return "views/eleves/eleves";
    }
}