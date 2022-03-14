/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.controllers;

import com.ivangarcia.blog.jpa.JpaUsuario;
import com.ivangarcia.blog.models.RegistrationForm;
import com.ivangarcia.blog.models.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author IvanGarMo
 */
@Controller
@RequestMapping("/registro")
@Slf4j
public class RegistrationController {
    @Autowired
    public JpaUsuario usuarioRepo;
    @Autowired
    public PasswordEncoder passEncoder;
    
    @GetMapping
    public String getRegister(Model model, @AuthenticationPrincipal Usuario usuario) {
        log.info("Usuario que ha niciado sesión: "+usuario.toString());
        model.addAttribute("registrationForm", new RegistrationForm());
        return "registration";
    }
    
    @PostMapping
    public String postRegister(Model model, RegistrationForm registrationForm, @AuthenticationPrincipal Usuario usuario) {
//        log.info("Form: "+registrationForm.toUser(passEncoder).toString());
        usuarioRepo.save(registrationForm.toUser(passEncoder));
        return "registration";
    }
}
