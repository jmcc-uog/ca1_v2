package com.ca.jmccabepetition.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetitionController {

    @GetMapping("/create-petition")
    public String showCreatePetitionForm() {
        return "create-petition";
    }

    @PostMapping("/create-petition")
    public String createPetition(String title, String description) {
        // Handle the creation of the petition (e.g., save to a database)
        // You can add your logic here.

        // Redirect to a success page or another appropriate page
        return "redirect:/petition-created";
    }
}
