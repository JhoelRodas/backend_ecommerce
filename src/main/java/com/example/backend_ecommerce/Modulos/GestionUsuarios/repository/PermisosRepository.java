package com.example.backend_ecommerce.Modulos.GestionUsuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.Permisos;

@Repository
public interface PermisosRepository extends JpaRepository<Permisos,Integer> {
    
}
