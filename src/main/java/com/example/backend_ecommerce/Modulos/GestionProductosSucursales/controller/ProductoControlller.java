package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.controller;

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

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Productos;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services.ProductoServices;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/producto")
@RequiredArgsConstructor
// @CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173/"})
public class ProductoControlller {

    private final ProductoServices productosService;

     // Obtener todos los productos
    @GetMapping
    public List<Productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Productos> getProductoById(@PathVariable int id) {
        Optional<Productos> producto = productosService.getProductoById(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public Productos createProducto(@RequestBody Productos producto) {
        return productosService.createProducto(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Productos> updateProducto(@PathVariable int id, @RequestBody Productos productoDetails) {
        Productos updatedProducto = productosService.updateProducto(id, productoDetails);
        if (updatedProducto != null) {
            return ResponseEntity.ok(updatedProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable int id) {
        productosService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}
