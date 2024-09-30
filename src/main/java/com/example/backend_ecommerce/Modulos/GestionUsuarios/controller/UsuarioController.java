package com.example.backend_ecommerce.Modulos.GestionUsuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.User;

import com.example.backend_ecommerce.Modulos.GestionUsuarios.services.UsuarioServices;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/users")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173"})
public class UsuarioController {
    private final UsuarioServices usuarioServices;

     @GetMapping
    public ResponseEntity<List<User>> obtenerTodosLosRoles() {
        List<User> usuario = usuarioServices.obtenerTodosLosUsuario();
        return ResponseEntity.ok(usuario);
    }
}
