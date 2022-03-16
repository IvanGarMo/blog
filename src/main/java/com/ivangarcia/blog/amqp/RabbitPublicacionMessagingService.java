/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.amqp;

import com.ivangarcia.blog.models.Publicacion;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 *
 * @author IvanGarMo
 */
@Service
public class RabbitPublicacionMessagingService implements PublicacionMessagingService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
//    @Override
//    public void sendPublicacion(Publicacion publicacion) {
//        MessageConverter converter = rabbitTemplate.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        props.setHeader("X_PUBLICACION_AUTHOR", "REGISTERED");
//        Message message = converter.toMessage(converter, props);
//        rabbitTemplate.send("com.tacocloud.app", message);
//    }
    
//    @Override
//    public void sendPublicacion(Publicacion publicacion) {
//        rabbitTemplate.convertAndSend("com.tacocloud.app", publicacion);
//    }
    
    @Override
    public void sendPublicacion(Publicacion publicacion) {
        rabbitTemplate.convertAndSend("com.tacoclodu.app", publicacion, 
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        MessageProperties prop = message.getMessageProperties();
                        prop.setHeader("X_PUBLICACION_AUTHOR", "REGISTERED");
                        return message;
                    }
                });
    }
}
