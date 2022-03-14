/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author IvanGarMo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String contrasena;
    private String correo;
    private String nombreUsuario;
    private int edad;
    
    @OneToMany(mappedBy="usuarios", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @ElementCollection
    private List<Publicacion> publicaciones;
    
    public Usuario(int id, String nombre, String apPaterno, String apMaterno, String contrasena, 
            String correo, String nombreUsuario, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.contrasena = contrasena;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.edad = edad;
    }
    
    public void addPublicacion(Publicacion p) {
        if(this.publicaciones == null) {
            this.publicaciones = new ArrayList<>();
        }
        if(p != null) {
            this.publicaciones.add(p);
            this.publicaciones.forEach(pb -> p.setUsuarios(this));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.contrasena;
    }

    @Override
    public String getUsername() {
        return this.nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public String toString() {
        return "[ Id: "+this.id+" Nombre: "+this.nombre+" ApPaterno: "+this.apPaterno+
                " ApMaterno: "+this.apMaterno+" Contrasena: "+this.contrasena+" Correo: "+
                this.correo+" NombreUsuario: "+this.nombreUsuario+" Edad: "+this.edad+"]";
    }
}
