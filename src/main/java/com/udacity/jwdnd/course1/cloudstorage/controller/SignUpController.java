package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignUpController {
    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage() {
        return "signup";
    }

    @PostMapping
    public String createUserAccount(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        String signUpError = null;

        if (!userService.isUserNameAvailable(user.getUsername())) {
            signUpError = "The Username Already Exists";
        }

        if (signUpError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signUpError = "There was an error signing you up. Please Try again";
            }
        }

        if (signUpError == null) {
            redirectAttributes.addFlashAttribute("SuccessMessage", "Sign Up Successfully");
            model.addAttribute("signupSuccess", true);
            return "redirect:/login";
        } else {
            model.addAttribute("signupError", signUpError);
        }

        return "signup";
    }
}
