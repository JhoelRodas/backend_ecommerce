package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Categoria;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Sucursales;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServices {
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Create or update a category
    public Categoria saveCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // Get a list of all categories
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    // Get a category by ID
    public Optional<Categoria> getCategoriaById(int id) {
        return categoriaRepository.findById(id);
    }

    // Delete a category by ID
    public void deleteCategoriaById(int id) {
        categoriaRepository.deleteById(id);
    }

    // Actualizar una categoría existente
    public Categoria actualizaCategoria(Integer id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id)
                .map(categoria -> {
                    categoria.setNombre(categoriaActualizada.getNombre());
                    categoria.setDescripcion(categoriaActualizada.getDescripcion());
                    return categoriaRepository.save(categoria);
                })
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + id));
    }

}
