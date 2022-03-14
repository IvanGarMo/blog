/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String descripcion;
    
    @ManyToMany(mappedBy="categoria")
    private List<Publicacion> publicaciones;
    
    public Categoria(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.publicaciones = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "[ Id: "+this.id+" Descripcion: "+this.descripcion+" ]";
    }
}
