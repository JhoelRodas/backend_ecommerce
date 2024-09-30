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

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Stock;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services.StockServices;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth/stock")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://frontend-stylo-store.vercel.app/","http://localhost:5173/"})
public class StockController {
    
    private final StockServices stockService;

     // Obtener todos los registros de stock
    @GetMapping
    public List<Stock> getAllStock() {
        return stockService.getAllStock();
    }

    // Obtener un registro de stock por ID
    @GetMapping("/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable int id) {
        Optional<Stock> stock = stockService.getStockById(id);
        return stock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo registro de stock
    @PostMapping
    public Stock createStock(@RequestBody Stock stock) {
        return stockService.createStock(stock);
    }

    // Actualizar un registro de stock existente
    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable int id, @RequestBody Stock stockDetails) {
        Stock updatedStock = stockService.updateStock(id, stockDetails);
        if (updatedStock != null) {
            return ResponseEntity.ok(updatedStock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un registro de stock por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable int id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}
