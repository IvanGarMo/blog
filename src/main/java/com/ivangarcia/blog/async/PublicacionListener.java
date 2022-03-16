/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.async;

import com.ivangarcia.blog.models.Publicacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author IvanGarMo
 */
@Component
@Slf4j
public class PublicacionListener {
    
    @JmsListener(destination = "tacocloud.order.queue")
    public void receivePublicacion(Publicacion p) {
        log.info(p.toString());
    }
}
