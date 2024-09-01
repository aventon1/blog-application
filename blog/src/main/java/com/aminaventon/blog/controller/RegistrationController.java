package com.aminaventon.blog.controller;

import com.aminaventon.blog.model.User;
import com.aminaventon.blog.service.UserService;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *  RegistrationController for handling incoming HTTP requests and returning an appropriate responses
 *  for registration pages
 */
@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * This method receives HTTP GET request for /registration
     * @param model
     * @return registration
     */
    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {

        // create new user
        User user = new User();

        // add user to model
        model.addAttribute("user", user);

        return "registration";
    }

    /**
     * This method processes HTTP POST request for /registration
     * @param user
     * @return registration
     */
    @PostMapping("/registration")
    public String registerUserAccount(@Valid @ModelAttribute("user") User user) {

        // save user
        userService.save(user);

        return "redirect:/";
    }


}
