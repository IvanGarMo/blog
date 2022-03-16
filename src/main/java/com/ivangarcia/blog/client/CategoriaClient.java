/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.client;

import com.ivangarcia.blog.models.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
/**
 *
 * @author IvanGarMo
 */
@Slf4j
public class CategoriaClient {
    @Autowired
    private RestTemplate restClient;
    
    public Categoria getCategoriaById(int idCategoria) {
        Map<String, Object> categoriasValues = new HashMap<>();
        categoriasValues.put("id", idCategoria);
        
        return restClient.getForObject("http://localhost:8080/categoria/{id}", 
                Categoria.class, categoriasValues);
    }
    
    public Categoria getHeaderCategoriaByid(int idCategoria) {
        Map<String, Object> categoriasValues = new HashMap<>();
        categoriasValues.put("id", idCategoria);
        
        ResponseEntity<Categoria> response;
        response = restClient.getForEntity("http://localhost:8080/categoria/{id}", 
                Categoria.class, idCategoria);
        
        log.info("Headers"+response.getHeaders().getDate());
        
        return response.getBody();
    }
    
    public void putCategoria(Categoria categoria) {
        restClient.put("http://localhost:8080/categoria/{id}", categoria);
    }
    
    public void deleteCategoria(int idCategoria) {
        Map<String, Object> categoriasValues = new HashMap<>();
        categoriasValues.put("id", idCategoria);
        
        restClient.delete("http://localhost:8080/categoria/{id}", categoriasValues);
    }
    
    public Categoria postCategoria(Categoria categoria) {
        return restClient.postForEntity("http://localhost:8080/categoria", categoria, categoria.getClass()).getBody();
    }
}
