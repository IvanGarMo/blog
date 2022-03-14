/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Categoria;
import java.util.List;

/**
 *
 * @author IvanGarMo
 */
public interface JdbcCategoria {
   List<Categoria> findAll();
   Categoria findCategoriaById(int id);
   Categoria saveCategoria(Categoria categoria);
}
