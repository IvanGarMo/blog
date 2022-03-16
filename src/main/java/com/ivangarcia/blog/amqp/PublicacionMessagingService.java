/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.amqp;

import com.ivangarcia.blog.models.Publicacion;

/**
 *
 * @author IvanGarMo
 */
public interface PublicacionMessagingService {
    void sendPublicacion(Publicacion publicacion);
}
