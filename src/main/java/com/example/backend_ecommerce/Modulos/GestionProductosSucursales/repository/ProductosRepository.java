package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos,Integer>{
    
}
