/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog;

import com.ivangarcia.blog.jdbc.JdbcPublicacion;
import com.ivangarcia.blog.jdbc.JdbcPublicacionImplementacion;
import com.ivangarcia.blog.models.Publicacion;
import java.util.Arrays;
import java.util.Date;
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
public class PublicacionTest {
    @Autowired
    private JdbcPublicacion publicacionRepo;
    
//    @Test
//    public void testAddPublicacion() {
//        List<Integer> categorias = Arrays.asList(1);
//        Publicacion p = new Publicacion();
//        p.setTitulo("Prueba 3");
//        p.setTexto("Hola mundo, esta es tan solo una prueba del blog");
//        p.setCreado(new Date());
//        p.setCategoria(categorias);
//        publicacionRepo.save(1, p);
//    } 
    
    @Test
    public void testFindPublicacion() {
        Publicacion publicacion = publicacionRepo.findById(3);
        log.info("Publicacion: "+publicacion.toString());
    }
    
    @Test
    public void testFindPublicaciones() {
        List<Publicacion> publicacion = publicacionRepo.findByUser(1);
        log.info("Publicaciones del usuario 1: ");
        log.info("----------------------------------");
        publicacion.forEach(p -> {
            log.info("Publicacion encontrada: "+p.toString());
        });
    }
}
