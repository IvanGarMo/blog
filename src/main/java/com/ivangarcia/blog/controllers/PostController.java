/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.controllers;

import com.ivangarcia.blog.jdbc.JdbcCategoria;
import com.ivangarcia.blog.jdbc.JdbcPublicacion;
import com.ivangarcia.blog.jpa.JpaCategoria;
import com.ivangarcia.blog.jpa.JpaPublicacion;
import com.ivangarcia.blog.jpa.JpaUsuario;
import com.ivangarcia.blog.models.Categoria;
import com.ivangarcia.blog.models.Publicacion;
import com.ivangarcia.blog.models.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
/**
 *
 * @author IvanGarMo
 */
@Controller
@RequestMapping("/publicacion")
@Slf4j
public class PostController {
//    @Autowired
//    private JdbcCategoria jdbcCategoria;
//    @Autowired
//    private JdbcPublicacion jdbcPublicacion;
    @Autowired
    private JpaCategoria categoriaRepo;
    @Autowired
    private JpaUsuario usuarioRepo;
    @Autowired
    private JpaPublicacion publicacionRepo;
    
    @GetMapping("/nueva")
    public String getNuevaPublicacion(Model model) {
        List<Categoria> categorias = cargaCategorias();
        model.addAttribute("listaCategorias", categorias);
        model.addAttribute("publicacion", new Publicacion());
        return "post";
    }
    
    @PostMapping("/nueva")
    public String postNuevaPublicacion(@Valid Publicacion publicacion, 
            Errors errors, Model model) {
        if(errors.hasErrors()) {
            List<Categoria> categorias = cargaCategorias();
            model.addAttribute("categorias", categorias);
            model.addAttribute("errors", errors);
            model.addAttribute("publicacion", publicacion);
            return "post";
        }
        
        
        Usuario u = usuarioRepo.findById(1).get();
        u.addPublicacion(publicacion);
        usuarioRepo.save(u);
//        jdbcPublicacion.save(1, publicacion);
        return "post";
    }
    
    private List<Categoria> cargaCategorias() {
        Iterable<Categoria> categorias = categoriaRepo.findAll();
        List<Categoria> listaCategorias = new ArrayList<>();
        categorias.forEach(c -> listaCategorias.add(c));
        return listaCategorias;
    }
}
