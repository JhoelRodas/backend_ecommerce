package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Productos;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.repository.ProductosRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServices {
    private final ProductosRepository productosRepository;

    // Obtener todos los productos
    public List<Productos> getAllProductos() {
        return productosRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Productos> getProductoById(int id) {
        return productosRepository.findById(id);
    }

    // Crear un nuevo producto
    public Productos createProducto(Productos producto) {
        return productosRepository.save(producto);
    }

    // Actualizar un producto existente
    public Productos updateProducto(int id, Productos productoDetails) {
        Optional<Productos> producto = productosRepository.findById(id);
        if (producto.isPresent()) {
            Productos productoToUpdate = producto.get();
            productoToUpdate.setNombre(productoDetails.getNombre());
            productoToUpdate.setPrecio(productoDetails.getPrecio());
            productoToUpdate.setColor(productoDetails.getColor());
            productoToUpdate.setImagenUrl(productoDetails.getImagenUrl());
            productoToUpdate.setDescripcion(productoDetails.getDescripcion());
            productoToUpdate.setCategoria(productoDetails.getCategoria());
            return productosRepository.save(productoToUpdate);
        } else {
            return null;
        }
    }

    // Eliminar un producto por ID
    public void deleteProducto(int id) {
        productosRepository.deleteById(id);
    }

    
}
