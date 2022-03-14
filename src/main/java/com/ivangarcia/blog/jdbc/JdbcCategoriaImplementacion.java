/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.jdbc;

import com.ivangarcia.blog.models.Categoria;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class JdbcCategoriaImplementacion implements JdbcCategoria {
    private JdbcTemplate jdbc;
    private SimpleJdbcInsert insert;
    
    @Autowired
    public JdbcCategoriaImplementacion(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.insert = new SimpleJdbcInsert(jdbc).withTableName("categoria").usingGeneratedKeyColumns("id");
    }
    
    @Override
    public List<Categoria> findAll() {
        return jdbc.query("select * from categoria", this::mapRowToCategoria);
    }
    
    @Override
    public Categoria findCategoriaById(int id) {
        return jdbc.queryForObject("select * from categoria where id=?", 
                this::mapRowToCategoria, id);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        Map<String, Object> values = new HashMap<>();
        values.put("descripcion", categoria.getDescripcion());
        int idCategoria = this.insert.executeAndReturnKey(values).intValue();
        Categoria savedCategoria = findCategoriaById(idCategoria);
        return savedCategoria;
    }
    
    private Categoria mapRowToCategoria(ResultSet rs, int rowNumber) throws SQLException {
        return new Categoria(
                rs.getInt("id"),
                rs.getString("descripcion")
        );
    }
}
