package com.ca.jmccabepetition.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        // Get the error message from the request attribute
        Object errorMessage = request.getAttribute("javax.servlet.error.message");

        if (errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage.toString());
        } else {
            model.addAttribute("errorMessage", "An error occurred on this page.");
        }

        return "error";
    }


    public String getErrorPath() {
        return "/error";
    }
}
