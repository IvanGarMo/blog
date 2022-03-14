/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Categoria;
import com.ivangarcia.blog.models.Publicacion;
import com.ivangarcia.blog.models.Usuario;
import java.util.List;

/**
 *
 * @author IvanGarMo
 */
public interface JdbcPublicacion {
    Publicacion save(int idUsuario, Publicacion p);
    Publicacion findById(int id);
    List<Publicacion> findByUser(int id);
    List<Integer> findCategoriasPublicacion(int idPublicacion);
    Usuario findAutorPublicacion(int idPublicacion);
}
