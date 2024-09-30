package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.controller;

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

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Categoria;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Sucursales;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services.CategoriaServices;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth/categorias")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173/"})
public class CategoriaController {

    private final CategoriaServices categoriaService;


    // Create or update a category
    //Crear 
    @PostMapping
    public ResponseEntity<Categoria> createOrUpdateCategoria(@RequestBody Categoria categoria) {
        Categoria savedCategoria = categoriaService.saveCategoria(categoria);
        return ResponseEntity.ok(savedCategoria);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categorias = categoriaService.getAllCategorias();
        return ResponseEntity.ok(categorias);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        Optional<Categoria> categoria = categoriaService.getCategoriaById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //Actualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaActualizada) {
        Categoria updatedCategoria = categoriaService.actualizaCategoria(id, categoriaActualizada);
        return ResponseEntity.ok(updatedCategoria);
    }

    // Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoriaById(@PathVariable int id) {
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }
}
