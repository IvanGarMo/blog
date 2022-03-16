/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.async;

import com.ivangarcia.blog.models.Publicacion;
import javax.jms.JMSException;
import javax.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

/**
 *
 * @author IvanGarMo
 */
@Service
public class JmsPublicacionReceiver implements PublicacionReceiver {
    @Autowired
    private JmsTemplate jms;
    @Autowired
    private MessageConverter messageConverter;
    
    @Override
    public Publicacion receivePublicacion() throws JMSException {
        return (Publicacion) jms.receiveAndConvert("tacocloud.com.order.queue");
        
//        Message message = jms.receive("tacocloud.com.order.queue");
//        return (Publicacion) messageConverter.fromMessage(message);
    }
}
