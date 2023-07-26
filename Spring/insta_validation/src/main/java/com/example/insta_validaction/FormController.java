package com.example.insta_validaction;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
    @GetMapping("/")
    public String getForm(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/validResult")
    public String getResult() {
        return "validResult";
    }

    @PostMapping("/submitItem")
    public String handleSubmit(@Valid User user, BindingResult result){
        if(result.hasErrors()) return "sign-up";
        return "redirect:validResult";
    }
}