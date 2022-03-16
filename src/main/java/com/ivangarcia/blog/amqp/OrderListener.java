/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.amqp;

import com.ivangarcia.blog.models.Publicacion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author IvanGarMo
 */
@Component
@Slf4j
public class OrderListener {
    
    @RabbitListener(queues = "tacocloud.order.queue")
    public void receivePublicacion(Publicacion publicacion) {
        log.info("Publicacion: "+publicacion.toString());
    }
}
