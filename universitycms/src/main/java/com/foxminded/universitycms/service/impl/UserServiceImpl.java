package com.foxminded.universitycms.service.impl;

import com.foxminded.universitycms.entity.Student;
import com.foxminded.universitycms.entity.Teacher;
import com.foxminded.universitycms.entity.User;
import com.foxminded.universitycms.repository.UserRepository;
import com.foxminded.universitycms.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {

        log.info("Trying to login by using email: " + email);

        User user = userRepository.findByEmail(email);

        log.info("Found user: " + user.toString());

        Set<GrantedAuthority> roles = new HashSet<>();

        if(user instanceof Teacher) {
            roles.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
            log.info("Teacher login");
        }
        if(user instanceof Student) {
            roles.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            log.info("Student login");
        }

        log.info("User have roles: " + roles.stream().toString());

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
