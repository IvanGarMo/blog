/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog;

import com.ivangarcia.blog.jpa.JpaCategoria;
import com.ivangarcia.blog.jpa.JpaUsuario;
import com.ivangarcia.blog.models.Categoria;
import com.ivangarcia.blog.models.Publicacion;
import com.ivangarcia.blog.models.Usuario;
import java.util.Date;
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
public class JpaTest {
    @Autowired
    private JpaCategoria jpaCategoria;
    @Autowired
    private JpaUsuario jpaUsuario;
    
    @Test
    public void loadAllCategorias() {
        Iterable<Categoria> categorias = jpaCategoria.findAll();
        log.info("Categorías encontradas");
        log.info("-----------------------------");
        categorias.forEach(c -> {
            log.info(c.toString());
        });
    }
    
    @Test
    public void salvaPublicacion() {
        Usuario u = jpaUsuario.findById(1).get();
        Publicacion p = new Publicacion();
        p.setTitulo("Prueba");
        p.setTexto("Texto de prueba");
        p.setCreado(new Date());
        
        Categoria c1 = new Categoria(3, "Deportes");
        u.addPublicacion(p);
        p.addCategoria(c1);
        jpaUsuario.save(u);
    }
}
