/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.entitymodels;

import com.ivangarcia.blog.controllers.PostRestController;
import com.ivangarcia.blog.models.Publicacion;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 *
 * @author IvanGarMo
 */
public class PublicacionModelAssembler extends RepresentationModelAssemblerSupport<Publicacion, PublicacionModel> {

    public PublicacionModelAssembler()  {
        super(PostRestController.class, PublicacionModel.class);
    }
    
    @Override
    protected PublicacionModel instantiateModel(Publicacion publicacion) {
        return new PublicacionModel(publicacion);
    }
    
    
    @Override
    public PublicacionModel toModel(Publicacion publicacion) {
        return this.createModelWithId(publicacion.getId(), publicacion);
    }
    
}
