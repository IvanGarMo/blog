/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.async;

import com.ivangarcia.blog.models.Publicacion;
import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

/**
 *
 * @author IvanGarMo
 */
@Service
public class JmsPublicacionMessagingService implements PublicacionMessagingService {
    
    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("publicacion", Publicacion.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }
    
    
    @Autowired
    private JmsTemplate jms;
    
    @Override
    public void sendPublicacion(Publicacion publicacion) {
        jms.send(session -> session.createObjectMessage(publicacion));
//        jms.send(new MessageCreator() {
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                return session.createObjectMessage(publicacion);
//            }
//        });
    }
    
    @Override
    public void convertAndSend(Publicacion publicacion) {
        jms.convertAndSend(publicacion);
    }
    
    @Override
    public void convertAndSend(Publicacion publicacion, MessagePostProcessor postProcessor) {
        jms.convertAndSend(publicacion, this::addPublicacionSource);
    }
    
    private Message addPublicacionSource(Message message) throws JMSException {
        message.setStringProperty("X_PUBLICACION_AUTHOR", "REGISTERED");
        return message;
    }
}
