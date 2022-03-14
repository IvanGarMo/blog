/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog;

import com.ivangarcia.blog.jdbc.JdbcCategoria;
import com.ivangarcia.blog.models.Categoria;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author IvanGarMo
 */
@SpringBootTest
@Slf4j
public class CategoriaTest {
    @Autowired
    private JdbcCategoria jbcdCategoria;
    
    @Test
    public void loadCategorias() {
        List<Categoria> categorias = this.jbcdCategoria.findAll();
        categorias.forEach(c -> log.info("Categoría: "+c.toString()));
    }
    
    @Test
    public void loadCategoria() {
        Categoria categoria = this.jbcdCategoria.findCategoriaById(1);
        log.info("Categoría encontrada: "+categoria.toString());
    }
    
    @Test
    public void saveCategoria() {
        Categoria c = new Categoria();
        c.setDescripcion("Política");
        this.jbcdCategoria.saveCategoria(c);
    }
}
