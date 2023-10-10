package com.foxminded.universitycms.controller;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.User;
import com.foxminded.universitycms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ProfileController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_TEACHER')")
    @GetMapping("/profile")
    public String getStudentSchedule(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());

        if (user instanceof Student) {
            model.addAttribute("profile", (Student) user);
        } else if (user instanceof Teacher) {
            model.addAttribute("profile", (Teacher) user);
        }

        log.info(user.getFirstName());

        return "profile";
    }
}
