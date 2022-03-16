/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.amqp;

import com.ivangarcia.blog.models.Publicacion;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;

/**
 *
 * @author IvanGarMo
 */
public class RabbitPublicacionReceiverService implements PublicacionReceiverService {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
//    @Override
//    public Publicacion receivePublicacion() {
//        Message message = rabbitTemplate.receive("tacocloud.order.message");
//        return message != null ? (Publicacion) messageConverter().fromMessage(message) : null;
//    }
    
    @Override
    public Publicacion receivePublicacion() {
        Message message = rabbitTemplate.receive("tacocloud.order.message");
        return rabbitTemplate.convertSendAndReceiveAsType(message, 
                new ParameterizedTypeReference<Publicacion>() {});
    }
    
}
