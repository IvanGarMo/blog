/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author IvanGarMo
 */
@Data
@AllArgsConstructor
public class Comentario {
    private int id;
    private String texto;
    private Date creado;
    private int idUsuario;
    private int idPublicacion;
}
