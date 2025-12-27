package com.example.gestion_scolaire.controllers;



import com.example.gestion_scolaire.entities.Filiere;
import com.example.gestion_scolaire.services.FiliereService;
import com.example.gestion_scolaire.services.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/views/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("filieres", filiereService.getAll());
        return "views/filieres";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "views/add-filiere";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Filiere filiere) {
        filiereService.save(filiere);
        return "redirect:/views/filieres";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        filiereService.delete(id);
        return "redirect:/views/filieres";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Filiere filiere = filiereService.getById(id);
        model.addAttribute("filiere", filiere);

        if (filiere != null) {
            model.addAttribute("nombreEleves",
                    filiere.getEleves() != null ? filiere.getEleves().size() : 0);
            model.addAttribute("nombreCours",
                    filiere.getCours() != null ? filiere.getCours().size() : 0);
        }

        return "views/edit-filiere";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Filiere filiere) {
        filiereService.save(filiere);
        return "redirect:/views/filieres";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Filiere filiere = filiereService.getById(id);
        model.addAttribute("filiere", filiere);
        model.addAttribute("cours", coursService.getByFiliere(id));
        return "views/details-filiere";
    }
}