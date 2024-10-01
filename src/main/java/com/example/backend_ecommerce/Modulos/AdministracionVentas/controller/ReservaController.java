package com.example.backend_ecommerce.Modulos.AdministracionVentas.controller;


import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services.StockServices;

@RestController
@RequestMapping("/auth/reservas")
@RequiredArgsConstructor
//@CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173/"})
public class ReservaController {
     
    private final ReservaServices reservaService;
    private final StockServices stockService;

    // Crear una nueva reserva
     @PostMapping
    public ResponseEntity<?> createReserva(@RequestBody Reservas reserva) {
        try {
            // Verificar stock disponible
            
            int stockDisponible = stockService.checkStock(
                reserva.getSucursales().getId(),
                reserva.getProducto().getId(),
                reserva.getTalla()
            );

            if (stockDisponible <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No hay stock disponible para este producto en la sucursal seleccionada.");
            }

            // Disminuir el stock en 1
            stockService.decreaseStock(
                reserva.getSucursales().getId(),
                reserva.getProducto().getId(),
                reserva.getTalla(),
                1
            );

            // Guardar la reserva
            Reservas nuevaReserva = reservaService.saveReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear la reserva: " + e.getMessage());
        }
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
