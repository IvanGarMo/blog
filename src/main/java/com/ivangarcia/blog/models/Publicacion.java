/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.FetchStrategy;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.server.core.Relation;
/**
 *
 * @author IvanGarMo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@RestResource(rel="posts", path="posts")
@Relation(value="post", collectionRelation="posts")
public class Publicacion implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    private String titulo;
    
    @NotNull(message="Debe incluir detalles de su publicación")
    @NotBlank(message="El texto de su publicación no puede estar en blanco")
    @Size(min=10, message="Su texto debe tener al menos 10 caracteres de longitud")
    private String texto;
    
    @Size(min=1, message="Debe seleccionar al menos una categoría")
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="publicacion_categoria", 
            joinColumns=@JoinColumn(name="id_publicacion", referencedColumnName="id"), 
            inverseJoinColumns=@JoinColumn(name="id_categoria", referencedColumnName="id"))
    private List<Categoria> categoria;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @ManyToOne
    @JoinColumn(name="idUsuario")
    private Usuario usuarios;
    
    public Publicacion(int id, String titulo, String texto, Date creado) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.creado = creado;
    }
    
    @Override
    public String toString() {
        return "[ Id: "+this.id+" Texto: "+this.texto+" Categoría: "+this.categoria+"]";
    }
    
    public void addCategoria(Categoria c) {
        if(this.categoria == null) {
            this.categoria = new ArrayList<>();
        } 
        if(c != null) {
            this.categoria.add(c);
            this.categoria.forEach(cat -> c.getPublicaciones().add(this));
        }
    }
    
    public static Publicacion toPublicacion(PublicacionResponse pr) throws ParseException {
        Publicacion p = new Publicacion();
        p.setTitulo(pr.getTitulo());
        p.setTexto(pr.getTexto());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        p.setCreado(sdf.parse(pr.getCreado()));
        
        List<Categoria> categorias = new ArrayList<>();
        pr.getCategorias().forEach(i -> {
            Categoria c = new Categoria();
            c.setId(i);
            categorias.add(c);
        });
        p.setCategoria(categorias);
        
        return p;
    }
}
