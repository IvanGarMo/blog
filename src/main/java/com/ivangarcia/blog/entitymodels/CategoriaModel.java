/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.entitymodels;

import com.ivangarcia.blog.models.Categoria;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

/**
 *
 * @author IvanGarMo
 */
@Getter
public class CategoriaModel extends RepresentationModel<CategoriaModel> {
    private int id;
    private String descripcion;
    
    public CategoriaModel(Categoria categoria) {
        this.id = categoria.getId();
        this.descripcion = categoria.getDescripcion();
    }
}
