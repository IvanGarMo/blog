/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.controllers;

import com.ivangarcia.blog.entitymodels.PublicacionModel;
import com.ivangarcia.blog.entitymodels.PublicacionModelAssembler;
import com.ivangarcia.blog.jpa.JpaPublicacion;
import com.ivangarcia.blog.models.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.http.HttpStatus;
/**
 *
 * @author IvanGarMo
 */
@RepositoryRestController
public class RecentPostsRestController {
    
    @Autowired
    private JpaPublicacion jpaRepo;d
    
    @GetMapping("/posts/recent")
    public ResponseEntity<CollectionModel<PublicacionModel>> getRecent() {
        PageRequest page = PageRequest.of(0, 12);
        List<Publicacion> repoPosts = jpaRepo.findAll(page);
        CollectionModel<PublicacionModel> listaPublicaciones = new PublicacionModelAssembler().toCollectionModel(repoPosts);
        return new ResponseEntity<>(listaPublicaciones, HttpStatus.OK);
    }
}
