package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Stock;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServices {

    private final StockRepository stockRepository;

    // Obtener todos los registros de stock
    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    // Obtener un registro de stock por ID
    public Optional<Stock> getStockById(int id) {
        return stockRepository.findById(id);
    }

    // Crear un nuevo registro de stock
    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    // Actualizar un registro de stock existente
    public Stock updateStock(int id, Stock stockDetails) {
        Optional<Stock> stock = stockRepository.findById(id);
        if (stock.isPresent()) {
            Stock stockToUpdate = stock.get();
            stockToUpdate.setCantidad(stockDetails.getCantidad());
            stockToUpdate.setTalla(stockDetails.getTalla());
            stockToUpdate.setProducto(stockDetails.getProducto());
            stockToUpdate.setSucursal(stockDetails.getSucursal());
            return stockRepository.save(stockToUpdate);
        } else {
            return null;
        }
    }

    // Eliminar un registro de stock por ID
    public void deleteStock(int id) {
        stockRepository.deleteById(id);
    }

}
