/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author IvanGarMo
 */
@Data
public class RegistrationForm {
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String correo;
    private String nombreUsuario;
    private String contrasena;
    private int edad;
    
    public Usuario toUser(PasswordEncoder encoder) {
        Usuario u = new Usuario();
        u.setNombre(this.nombre);
        u.setApPaterno(this.apPaterno);
        u.setApMaterno(this.apMaterno);
        u.setCorreo(this.correo);
        u.setNombreUsuario(this.nombreUsuario);
        u.setContrasena(encoder.encode(this.contrasena));
        u.setEdad(this.edad);
        return u;
    }
    
    @Override
    public String toString() {
        return "[ Nombre: "+this.nombre+" ApPaternO: "+this.apPaterno+" ApMaterno: "+this.apMaterno+
                "Correo: "+this.correo+" NombreUsuario: "+this.nombreUsuario+" Edad: "+this.edad+"]";
    }
}
