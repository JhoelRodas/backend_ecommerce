package com.example.backend_ecommerce.Modulos.AdministracionVentas.services;


import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.User;
import com.example.backend_ecommerce.Modulos.GestionUsuarios.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import com.example.backend_ecommerce.Modulos.AdministracionVentas.entity.Reservas;
import com.example.backend_ecommerce.Modulos.AdministracionVentas.repository.ReservaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservaServices {
      
    private final ReservaRepository reservaRepository;

     @Autowired
    private UserRepository userRepository;

    // Guardar una nueva reserva
    public Reservas saveReserva(Reservas reserva) {
        return reservaRepository.save(reserva);
    }

    // Obtener todas las reservas
    public List<Reservas> getAllReservas() {
        return reservaRepository.findAll();
    }

    // Obtener reservas por ID de usuario
    public List<Reservas> getReservasByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return reservaRepository.findByUser(user.get());
        }
        return List.of(); // Retornar lista vacía si el usuario no existe
    }

    // Obtener reserva por ID
    public Optional<Reservas> getReservaById(Integer id) {
        return reservaRepository.findById(id);
    }

    // Eliminar una reserva por ID
    public void deleteReservaById(Integer id) {
        reservaRepository.deleteById(id);
    }

    // Actualizar una reserva existente
    public Reservas updateReserva(Reservas reserva) {
        return reservaRepository.save(reserva);
    }
}
