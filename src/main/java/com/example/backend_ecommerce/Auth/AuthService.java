package com.example.backend_ecommerce.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.Roles;
import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.User;
import com.example.backend_ecommerce.Modulos.GestionUsuarios.repository.RolesRepository;
import com.example.backend_ecommerce.Modulos.GestionUsuarios.repository.UserRepository;
import com.example.backend_ecommerce.jwt.JwtService;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RolesRepository rolesRepository; 

    private final AuthenticationManager authenticationManager;

    // public AuthResponse login(LoginRequest request) {
    //     //System.out.println(request);
    //     authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    //     UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
    //     String token=jwtService.getToken(user);
    //     System.out.println("este es el token"+token);
    //     return AuthResponse.builder()
    //         .token(token)
    //         .build();
    // }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            // Capturar más detalles del error de autenticación
            System.out.println("Error de autenticación: " + e.getMessage());
            throw new RuntimeException("Invalid credentials");
        }
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
    
    public AuthResponse register(RegisterRequest request) {
        // Busca el rol en la base de datos, en este caso "ROLE_USER"
        Roles userRole = rolesRepository.findByNombre("CLIENTE")
        .orElseThrow(() -> new RuntimeException("El rol CLIENTE no está configurado en la base de datos"));

            User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())  // Cambiado a request.getLastname()
            .telefono(request.getTelefono())
            .role(userRole)  // Asigna el rol encontrado
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }

    

}
