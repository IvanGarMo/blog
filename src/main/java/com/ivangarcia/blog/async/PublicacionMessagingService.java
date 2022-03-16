/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.async;

import com.ivangarcia.blog.models.Publicacion;
import org.springframework.jms.core.MessagePostProcessor;


/**
 *
 * @author IvanGarMo
 */
public interface PublicacionMessagingService {
    void sendPublicacion(Publicacion publicacion);
    void convertAndSend(Publicacion publicacion);
    void convertAndSend(Publicacion publicacion, MessagePostProcessor postProcessor);
}
