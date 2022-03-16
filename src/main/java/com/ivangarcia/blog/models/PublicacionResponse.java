/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author IvanGarMo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionResponse {
    private int id;
    private String titulo;
    private String texto;
    private List<Integer> categorias;
    private String creado;
    
    public void addCategoria(int idCategoria) {
        if(categorias == null) {
            categorias = new ArrayList<>();
        }
        categorias.add(id);
    }
    
    public static PublicacionResponse toPublicacionResponse(Publicacion p) {
        PublicacionResponse pr = new PublicacionResponse();
        pr.setId(p.getId());
        pr.setTitulo(p.getTitulo());
        pr.setTexto(p.getTexto());
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        pr.setCreado(sdf.format(p.getCreado()));
        p.getCategoria().forEach(c -> {
            pr.addCategoria(c.getId());
        });
        
        return pr;
    }
    
    @Override
    public String toString() {
        return "[ Id: "+this.id+" Titulo: "+this.titulo+" Texto: "+this.texto+" Creado: "+this.creado+" ]";
    }
}
