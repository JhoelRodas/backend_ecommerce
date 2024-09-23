package com.example.backend_ecommerce.Modulos.GestionProductos.controller;

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

import com.example.backend_ecommerce.Modulos.GestionProductos.entity.Sucursales;
import com.example.backend_ecommerce.Modulos.GestionProductos.services.SucursaleServices;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/sucursales")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://frontend-stylo-store.vercel.app/")
public class SucursalesController {
    private final SucursaleServices sucursalesService;



     // Obtener todas las sucursales
    @GetMapping
    public ResponseEntity<List<Sucursales>> obtenerTodasLasSucursales() {
        List<Sucursales> sucursales = sucursalesService.obtenerTodasLasSucursales();
        return ResponseEntity.ok(sucursales);
    }

    // Obtener una sucursal por ID
    @GetMapping("/{id}")
    public ResponseEntity<Sucursales> obtenerSucursalPorId(@PathVariable Integer id) {
        Optional<Sucursales> sucursalOpt = sucursalesService.obtenerSucursalPorId(id);
        return sucursalOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una nueva sucursal
    @PostMapping
    public ResponseEntity<Sucursales> crearSucursal(@RequestBody Sucursales sucursal) {
        Sucursales nuevaSucursal = sucursalesService.guardarSucursal(sucursal);
        return ResponseEntity.ok(nuevaSucursal);
    }

    // Actualizar una sucursal existente
    @PutMapping("/{id}")
    public ResponseEntity<Sucursales> actualizarSucursal(@PathVariable Integer id, @RequestBody Sucursales sucursalActualizada) {
        try {
            Sucursales sucursal = sucursalesService.actualizarSucursal(id, sucursalActualizada);
            return ResponseEntity.ok(sucursal);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Eliminar una sucursal por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSucursal(@PathVariable Integer id) {
        sucursalesService.eliminarSucursalPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
