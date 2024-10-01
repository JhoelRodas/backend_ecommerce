package com.example.backend_ecommerce.Auth;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.Permisos;
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
    // try {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getUsername(),
    // request.getPassword())
    // );
    // } catch (Exception e) {
    // // Capturar más detalles del error de autenticación
    // System.out.println("Error de autenticación: " + e.getMessage());
    // throw new RuntimeException("Invalid credentials");
    // }
    // UserDetails user =
    // userRepository.findByUsername(request.getUsername()).orElseThrow();
    // User user2 = (User) userRepository.findByUsername(request.getUsername())
    // .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    // String token = jwtService.getToken(user);
    // return AuthResponse.builder().token(token).userId(user2.getId()).build();
    // }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (request.getIsWebAccess().equals("true") && user.getRole().getNombre().equals("CLIENTE")) {
            throw new RuntimeException("Acceso denegado para clientes en la parte web");
        }
        String token = jwtService.getToken(user);

        // Incluye los detalles del usuario, como roles y permisos en la respuesta
        List<String> permissions = user.getRole().getPermisos()
                .stream()
                .map(Permisos::getNombre)
                .collect(Collectors.toList());
        System.out.println("Permisos del usuario: " + permissions);
        return AuthResponse.builder()
                .token(token)
                .userId(user.getId())
                .username(user.getUsername())
                .role(user.getRole().getNombre())
                .permissions(permissions)
                .build();
    }

    // public AuthResponse login(LoginRequest request) {
    // try {
    // authenticationManager.authenticate(
    // new UsernamePasswordAuthenticationToken(request.getUsername(),
    // request.getPassword()));

    // } catch (Exception e) {
    // throw new RuntimeException("Credenciales inválidas");
    // }

    // // Encontrar el usuario por su nombre de usuario
    // User user = userRepository.findByUsername(request.getUsername())
    // .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // System.out.println("Is Web Access: " + request.getIsWebAccess());
    // System.out.println("User Role: " + user.getRole().getNombre());
    // // Verificar si el acceso es desde la web y si el usuario es un cliente
    // if (request.getIsWebAccess().equals("true") &&
    // user.getRole().getNombre().equals("CLIENTE")) {
    // throw new RuntimeException("Acceso denegado para clientes en la parte web");
    // }

    // String token = jwtService.getToken(user);
    // List<String> permissions = user.getRole().getPermisos()
    // .stream()
    // .map(Permisos::getNombre)
    // .collect(Collectors.toList());

    // return AuthResponse.builder()
    // .token(token)
    // .userId(user.getId())
    // .username(user.getUsername())
    // .role(user.getRole().getNombre())
    // .permissions(permissions)
    // .build();
    // }

    public AuthResponse register(RegisterRequest request) {
        // Busca el rol en la base de datos, en este caso "ROLE_USER"
        Roles userRole = rolesRepository.findByNombre("CLIENTE")
                .orElseThrow(() -> new RuntimeException("El rol CLIENTE no está configurado en la base de datos"));

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstname(request.getFirstname())
                .lastname(request.getLastname()) // Cambiado a request.getLastname()
                .telefono(request.getTelefono())
                .role(userRole) // Asigna el rol encontrado
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public UserResponse getUserByUsername(String username) {
        // Buscar al usuario en la base de datos usando el username
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Retornar los datos del usuario en un objeto UserResponse
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .telefono(user.getTelefono())
                .role(user.getRole().getNombre()) // Asumiendo que el rol tiene un método getNombre()
                .build();
    }

}
