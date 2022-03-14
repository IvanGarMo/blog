/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Usuario;

/**
 *
 * @author IvanGarMo
 */
public interface JdbcUsuario {
    Usuario findAutorPublicacion(int idUsuario);
}
