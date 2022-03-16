/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jpa;

import com.ivangarcia.blog.models.Publicacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
/**
 *
 * @author IvanGarMo
 */
public interface JpaPublicacion extends CrudRepository<Publicacion, Integer> {
    List<Publicacion> findAll(Pageable page);
}
