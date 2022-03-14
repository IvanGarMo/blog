/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jpa;

import com.ivangarcia.blog.models.Usuario;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author IvanGarMo
 */
public interface JpaUsuario extends CrudRepository<Usuario, Integer> {
    Usuario findByNombreUsuario(String nombreUsuario);
}
