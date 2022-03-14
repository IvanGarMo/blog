/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ivangarcia.blog.security;

import com.ivangarcia.blog.jpa.JpaUsuario;
import com.ivangarcia.blog.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author IvanGarMo
 */
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    
    @Autowired
    private JpaUsuario jpaUsuario;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = jpaUsuario.findByNombreUsuario(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Usuario: "+username+" no encontrado");
        }
        return usuario;
    }
    
}
