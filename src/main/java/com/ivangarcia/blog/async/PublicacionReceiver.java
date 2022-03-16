/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ivangarcia.blog.async;

import com.ivangarcia.blog.models.Publicacion;
import javax.jms.JMSException;

/**
 *
 * @author IvanGarMo
 */
public interface PublicacionReceiver {
    Publicacion receivePublicacion() throws JMSException;
}
