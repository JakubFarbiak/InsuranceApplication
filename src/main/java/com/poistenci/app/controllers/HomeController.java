package com.poistenci.app.controllers;


import com.poistenci.app.data.repositories.novyPoistenecRepository;
import com.poistenci.app.models.NovyPoistenecDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private novyPoistenecRepository poistenecRepository;  // Inject the repository

    // Route for the home page (index.html)
    @GetMapping("/")
    public String renderIndex(Model model) {
        model.addAttribute("poistenecList", poistenecRepository.findAll());
        return "pages/home/index";
    }

    // Route for the form page (novypoistenec.html)
    @GetMapping("/novypoistenec")
    public String renderNovyPoistenec(Model model) {
        model.addAttribute("novyPoistenec", new NovyPoistenecDTO());
        return "pages/home/novypoistenec";
    }

    // Handle the form submission and save the novyPoistenec data to the database
    @PostMapping("/save-poistenec")
    public String savePoistenec(@ModelAttribute NovyPoistenecDTO npoistenec) {
        poistenecRepository.save(npoistenec);
        return "redirect:/";
    }


    @GetMapping("/poistenci/delete/{id}")
    public String deletePoistenec(@PathVariable Long id) {
        poistenecRepository.deleteById(id);
        return "redirect:/";
    }
}

























