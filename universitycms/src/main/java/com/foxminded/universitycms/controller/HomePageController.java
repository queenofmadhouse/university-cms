package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.User;
import com.foxminded.universitycms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class HomePageController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER')")
    @GetMapping("/main")
    public String getHomePage(Model model, Principal principal) {

        String email = principal.getName();
        User user = userService.findByEmail(email);

        model.addAttribute("userName", user.getFirstName());
        return "home";
    }
}
