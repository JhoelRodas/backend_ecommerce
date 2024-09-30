package com.example.backend_ecommerce.Modulos.AdministracionVentas.controller;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_ecommerce.Modulos.AdministracionVentas.entity.Reservas;
import com.example.backend_ecommerce.Modulos.AdministracionVentas.services.ReservaServices;

@RestController
@RequestMapping("/auth/reservas")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173/"})
public class ReservaController {
     @Autowired
    private ReservaServices reservaService;

    // Crear una nueva reserva
    @PostMapping
    public ResponseEntity<Reservas> createReserva(@RequestBody Reservas reserva) {
        Reservas nuevaReserva = reservaService.saveReserva(reserva);
        return ResponseEntity.ok(nuevaReserva);
    }

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<Reservas>> getAllReservas() {
        List<Reservas> reservas = reservaService.getAllReservas();
        return ResponseEntity.ok(reservas);
    }

    // Obtener una reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Reservas>> getReservaById(@PathVariable Integer id) {
        Optional<Reservas> reserva = reservaService.getReservaById(id);
        return reserva.isPresent() ? ResponseEntity.ok(reserva) : ResponseEntity.notFound().build();
    }

    // Obtener reservas por ID de usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<Reservas>> getReservasByUserId(@PathVariable Integer userId) {
        List<Reservas> reservas = reservaService.getReservasByUserId(userId);
        return reservas.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(reservas);
    }

    // Actualizar una reserva existente
    @PutMapping("/{id}")
    public ResponseEntity<Reservas> updateReserva(@PathVariable Integer id, @RequestBody Reservas reservaDetalles) {
        Optional<Reservas> reservaOpt = reservaService.getReservaById(id);
        if (reservaOpt.isPresent()) {
            Reservas reserva = reservaOpt.get();
            reserva.setFechaReserva(reservaDetalles.getFechaReserva());
            reserva.setFechaRecogida(reservaDetalles.getFechaRecogida());
            reserva.setEstado(reservaDetalles.getEstado());
            Reservas reservaActualizada = reservaService.updateReserva(reserva);
            return ResponseEntity.ok(reservaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una reserva por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservaById(@PathVariable Integer id) {
        Optional<Reservas> reservaOpt = reservaService.getReservaById(id);
        if (reservaOpt.isPresent()) {
            reservaService.deleteReservaById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
