package com.foxminded.universitycms.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AdminPanelController {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/adminpanel")
    public String getAdminPanel(Model model) {
        return "adminpanel";
    }
}
