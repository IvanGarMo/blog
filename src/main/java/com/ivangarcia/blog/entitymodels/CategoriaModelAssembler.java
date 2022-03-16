/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.entitymodels;

import com.ivangarcia.blog.controllers.CategoriaRestController;
import com.ivangarcia.blog.models.Categoria;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

/**
 *
 * @author IvanGarMo
 */
public class CategoriaModelAssembler extends RepresentationModelAssemblerSupport<Categoria, CategoriaModel> {

    public CategoriaModelAssembler() {
        super(CategoriaRestController.class, CategoriaModel.class);
    }
    
    
    @Override
    protected CategoriaModel instantiateModel(Categoria categoria) {
        return new CategoriaModel(categoria);
    }
    
    @Override
    public CategoriaModel toModel(Categoria categoria) {
        return createModelWithId(categoria.getId(), categoria);
    }
    
}
