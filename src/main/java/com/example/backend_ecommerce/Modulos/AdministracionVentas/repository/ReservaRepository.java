package com.example.backend_ecommerce.Modulos.AdministracionVentas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_ecommerce.Modulos.AdministracionVentas.entity.Reservas;
import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.User;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reservas,Integer>{
    List<Reservas> findByUser(User user);
}
