/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.entitymodels;

import com.ivangarcia.blog.models.Categoria;
import com.ivangarcia.blog.models.Publicacion;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

/**
 *
 * @author IvanGarMo
 */
@Relation(value="publicacion", collectionRelation="publicaciones")
@Getter
public class PublicacionModel extends RepresentationModel<PublicacionModel> {
    
    private static final CategoriaModelAssembler categoriaModelAssembler = 
            new CategoriaModelAssembler();
    
    
    private int id;
    private String titulo;
    private String texto;
    private List<CategoriaModel> categoria;
    private Date creado;
    
    public PublicacionModel(Publicacion p) {
        this.id = p.getId();
        this.titulo = p.getTitulo();
        this.texto = p.getTexto();
        this.categoria = new ArrayList<>();
        p.getCategoria().forEach(c -> {
            categoria.add(categoriaModelAssembler.toModel(c));
        });
        this.creado = p.getCreado();
    }
}
