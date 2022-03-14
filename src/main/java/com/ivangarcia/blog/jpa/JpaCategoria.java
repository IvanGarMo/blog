/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jpa;

import com.ivangarcia.blog.models.Categoria;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IvanGarMo
 */
public interface JpaCategoria extends CrudRepository<Categoria, Integer> {
    
}
