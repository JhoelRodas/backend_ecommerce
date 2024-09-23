package com.example.backend_ecommerce.Modulos.GestionProductos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_ecommerce.Modulos.GestionProductos.entity.Sucursales;

@Repository
public interface SucursalesRepository extends JpaRepository<Sucursales,Integer> {

}
