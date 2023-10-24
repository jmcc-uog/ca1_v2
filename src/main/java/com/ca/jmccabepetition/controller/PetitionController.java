package com.ca.jmccabepetition.controller;


import com.ca.jmccabepetition.model.Petition;
import com.ca.jmccabepetition.model.Signer;
import com.ca.jmccabepetition.model.User;
import com.ca.jmccabepetition.repository.PetitionRepository;
import com.ca.jmccabepetition.repository.UserRepository;
import com.ca.jmccabepetition.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;


@Controller
public class PetitionController {

    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final PetitionService petitionService;

    @Autowired
    public PetitionController(PetitionRepository petitionRepository, UserRepository userRepository, PetitionService petitionService) {
        this.petitionRepository = petitionRepository;
        this.userRepository = userRepository;
        this.petitionService = petitionService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/list-petitions")
    public String listPetitions(Model model, @ModelAttribute("user") User user) {



        // Retrieve the list of petitions from the database
        List<Petition> petitions = petitionRepository.findAll();

        // Add the list of petitions and the current user's username to the model
        model.addAttribute("petitions", petitions);
        model.addAttribute("user", user);


        return "list-petitions";
    }


    @GetMapping("/signup")
    public String signUpForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("email") String email, RedirectAttributes redirectAttributes){
        User user = new User(firstName, lastName, email);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("user", user);
        return "redirect:/list-petitions";
    }
    @GetMapping("/sign-petition-form")
    public String showPetitionSigningForm(@RequestParam("petitionId") Long petitionId, Model model) {
        // Retrieve the petition with the given petitionId from the database
        Petition petition = petitionRepository.findById(petitionId).orElse(null);

        // Check if the petition exists
        if (petition == null) {
            // Handle the case where the petition does not exist (e.g., show an error message)
            return "redirect:/list-petitions"; // Redirect to the list of petitions or handle appropriately
        }

        // Create a new Signer object to collect user info
        Signer signer = new Signer();

        // Add the petition and signer to the model
        model.addAttribute("petition", petition);
        model.addAttribute("signer", signer);

        // Return the view for the petition signing form (sign-petition-form.html)
        return "sign-petition-form";
    }

    @PostMapping("/sign-petition")
    public String signPetition(@ModelAttribute("signer") Signer signer, @RequestParam("petitionId") Long petitionId) {
        Optional<Petition> optionalPetition = petitionRepository.findById(petitionId);

        if (optionalPetition.isPresent()) {
            Petition petition = optionalPetition.get();
            petition.addSigner(signer);
            petitionRepository.save(petition);
            return "redirect:/thank-you";
        } else {
            // Handle the case where the petition does not exist (e.g., show an error message)
            return "redirect:/list-petitions"; // Redirect to the list of petitions or handle appropriately
        }
    }

    @GetMapping("/thank-you")
    public String thankYou() {
        return "thank-you";
    }
    @GetMapping("/search-petitions")
    public String searchPetitions(@RequestParam(name = "query", required = false) String query, Model model) {
        // Implement the search logic here
        List<Petition> searchResults = petitionService.searchPetitions(query);
        model.addAttribute("searchResults", searchResults);
        return "search-petitions";
    }

    @GetMapping("/create-petition")
    public String showCreatePetitionForm(Model model) {
        model.addAttribute("petition", new Petition());
        return "create-petition";
    }

    @PostMapping("/create-petition")
    public String createPetition(@ModelAttribute("petition") Petition petition) {
        // Save the new petition using your service
        petitionService.createPetition(petition);
        return "redirect:/list-petitions"; // Redirect to the list of petitions
    }

    @GetMapping("/view-signers/{petitionId}")
    public String viewSigners(@PathVariable Long petitionId, Model model) {
        Petition petition = petitionService.findPetitionWithSigners(petitionId);
        model.addAttribute("petition", petition);
        return "view-signers";
    }


}













