package com.foxminded.universitycms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {

    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfiguration(UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/studentschedule").hasRole("STUDENT")
                .antMatchers("/lesson/**").hasAnyRole("TEACHER", "STUDENT")
                .antMatchers("/teacherschedule").hasRole("TEACHER")
                .antMatchers("/adminpanel/**").hasRole("ADMIN")
                .antMatchers("/getGroups", "/getTimeSlots/**", "/getFreeClassrooms/**", "/schedule/lesson/**", "/schedule/deleteLesson/**", "/add").hasRole("TEACHER")
                .antMatchers("/deleteLesson/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/main", true)
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .permitAll();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }
}
