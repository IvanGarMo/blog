/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Categoria;
import com.ivangarcia.blog.models.Publicacion;
import com.ivangarcia.blog.models.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/**
 *
 * @author IvanGarMo
 */
@Repository
public class JdbcPublicacionImplementacion implements JdbcPublicacion {
    
    private SimpleJdbcInsert jdbcPublicacion;
    private SimpleJdbcInsert jdbcCategorias;
    private SimpleJdbcInsert jdbcUsuario;
    
    @Autowired
    private JdbcCategoria jdbcCategoria;
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcPublicacionImplementacion(JdbcTemplate jdbc) {
        this.jdbcTemplate = jdbc;
        this.jdbcPublicacion = new SimpleJdbcInsert(jdbc).withTableName("publicacion")
                .usingGeneratedKeyColumns("id");
        this.jdbcCategorias = new SimpleJdbcInsert(jdbc).withTableName("publicacion_categoria");
        this.jdbcUsuario = new SimpleJdbcInsert(jdbc).withTableName("usuario_publicacion");
    }
    
    @Override
    public Publicacion save(int idUsuario, Publicacion p) {
        p.setCreado(new Date());
        int idPublicacion = savePublicacion(p);
        p.setId(idPublicacion);
        savePublicacionCategoria(idPublicacion, p);
        savePublicacionUsuario(idUsuario, p);
        return p;
    }

    @Override
    public Publicacion findById(int id) {
        return jdbcTemplate.queryForObject("select * from publicacion where id=?", 
                this::rowMapperToPublicacion, id);
    }

    @Override
    public List<Publicacion> findByUser(int id) {
        return jdbcTemplate.query(
                "SELECT p.id, p.titulo, p.texto, p.creado FROM usuario_publicacion as up "
                        + "JOIN publicacion AS p ON up.id_publicacion=p.id"
                        +" WHERE up.id_usuario=?", 
                this::rowMapperToPublicacion, id);           
    }
    
    
    public Usuario findAutorPublicacion(int idPublicacion) {
        return jdbcTemplate.queryForObject(
                "SELECT u.id, u.nombre, u.apPaterno, u.apMaterno, u.correo, u.nombreUsuario, u.contrasena, u.edad " +
                "FROM usuario_publicacion AS up " +
                "JOIN usuario u ON up.id_usuario=u.id " +
                "WHERE up.id_usuario=?"
                , this::rowMapperToUsuario, idPublicacion);
    }
    
    @Override
    public List<Integer> findCategoriasPublicacion(int idPublicacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Publicacion rowMapperToPublicacion(ResultSet rs, int rowNumber) throws SQLException {
        return new Publicacion(
                rs.getInt("id"),
                rs.getString("titulo"),
                rs.getString("texto"),
                rs.getDate("creado")
        );
    }
    
    private int rowMapperToInteger(ResultSet rs, int rowNumber) throws SQLException {
        return rs.getInt("id_categoria");
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
    
    private int savePublicacion(Publicacion p) {
        Map<String, Object> values = new HashMap();
        values.put("titulo", p.getTitulo());
        values.put("texto", p.getTexto());
        values.put("creado", p.getCreado());
        return jdbcPublicacion.executeAndReturnKey(values).intValue();
    }
    
    private void savePublicacionCategoria(int idPublicacion, Publicacion p) {
        Map<String, Object> values;
        for(Categoria idCategoria : p.getCategoria()) {
            values = new HashMap<>();
            values.put("id_categoria", idCategoria.getId());
            values.put("id_publicacion", idPublicacion);
            this.jdbcCategorias.execute(values);
        }
    }
    
    private void savePublicacionUsuario(int idUsuario, Publicacion p) {
        Map<String, Object> values = new HashMap<>();
        values.put("id_usuario", idUsuario);
        values.put("id_publicacion", p.getId());
        this.jdbcUsuario.execute(values);
    }
}
