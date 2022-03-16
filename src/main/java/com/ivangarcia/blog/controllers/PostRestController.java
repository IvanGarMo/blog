/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.controllers;

import com.ivangarcia.blog.entitymodels.PublicacionModel;
import com.ivangarcia.blog.entitymodels.PublicacionModelAssembler;
import com.ivangarcia.blog.jpa.JpaPublicacion;
import com.ivangarcia.blog.models.Publicacion;
import com.ivangarcia.blog.models.PublicacionResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
/**
 *
 * @author IvanGarMo
 */
@RestController
@Slf4j
@RequestMapping(path="/post",  produces="application/json")
@CrossOrigin(origins="*")
public class PostRestController {
    @Autowired
    private JpaPublicacion jpaPublicacion;
    
    private PublicacionModelAssembler publicacionModelAssembler = new PublicacionModelAssembler();
    
    @GetMapping("/recent")
    public CollectionModel<PublicacionModel> getRecent() {
        Order order = new Order(Sort.Direction.DESC, "creado");
        PageRequest page = PageRequest.of(0, 12, Sort.by(order));
        
        Iterable<Publicacion> publicaciones = jpaPublicacion.findAll();
        CollectionModel<PublicacionModel> collectionModel = publicacionModelAssembler.toCollectionModel(publicaciones);
        return collectionModel;
        
        
//        List<PublicacionResponse> listaPublicaciones = new ArrayList<>();
//        publicaciones.forEach(p -> {
//            listaPublicaciones.add(PublicacionResponse.toPublicacionResponse(p));
//        });
//        CollectionModel<EntityModel<PublicacionResponse>> recentLinks = CollectionModel.wrap(listaPublicaciones);
//        
//        recentLinks.add(
//                WebMvcLinkBuilder.linkTo(methodOn(PostRestController.class).getRecent()).withRel(IanaLinkRelations.SELF)
//        );
//        return recentLinks;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponse> getPublicacion(@PathVariable("id") int idPublicacion) {
        Optional<Publicacion> optPublicacion = jpaPublicacion.findById(idPublicacion);
        if (optPublicacion.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        PublicacionResponse pr = PublicacionResponse.toPublicacionResponse(optPublicacion.get());
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicacionResponse savePublicacion(@RequestBody PublicacionResponse publicacion) {
        log.info("Post: "+publicacion.toString());
        return publicacion;
//        Publicacion p = jpaPublicacion.save(publicacion);
//        return p;
    }
    
    @PutMapping("/update")
    public ResponseEntity<PublicacionResponse> updatePublicacion(@RequestBody PublicacionResponse publicacion) {
        log.info("Update: "+publicacion.toString());
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
//        Optional<Publicacion> optPublicacionAntigua = jpaPublicacion.findById(id);
//        if(optPublicacionAntigua.isEmpty()) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//        Publicacion publicacionAntigua = optPublicacionAntigua.get();
//        if(!publicacion.getTitulo().isBlank()) {
//            publicacionAntigua.setTitulo(publicacion.getTitulo());
//        }
//        if(!publicacion.getTexto().isBlank()) {
//            publicacionAntigua.setTexto(publicacion.getTexto());
//        }
//        if(publicacion.getCreado() != null) {
//            publicacionAntigua.setCreado(publicacion.getCreado());
//        }
//        publicacion = jpaPublicacion.save(publicacionAntigua);
//        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublicacion(@PathVariable("id") int id) {
        jpaPublicacion.deleteById(id);
    }
}
