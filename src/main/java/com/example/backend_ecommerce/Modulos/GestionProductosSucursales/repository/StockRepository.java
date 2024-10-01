package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Stock;

@Repository
public interface StockRepository  extends JpaRepository<Stock,Integer>{
    Optional<Stock> findBySucursalIdAndProductoIdAndTalla(Integer sucursalId, Integer productoId, String talla);

}
