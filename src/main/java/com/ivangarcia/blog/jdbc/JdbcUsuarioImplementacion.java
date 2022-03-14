/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author IvanGarMo
 */
public class JdbcUsuarioImplementacion implements JdbcUsuario {
    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    public Usuario findAutorPublicacion(int idUsuario) {
        return jdbc.queryForObject("select * from usuario where id=?", 
                this::rowMapperToUsuario, idUsuario);
    }
    
    private Usuario rowMapperToUsuario(ResultSet rs, int rowNumber) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("apPaterno"),
                rs.getString("apMaterno"),
                rs.getString("contrasena"),
                rs.getString("correo"),
                rs.getString("nombreUsuario"),
                rs.getInt("edad")
        );
    }
    
}
