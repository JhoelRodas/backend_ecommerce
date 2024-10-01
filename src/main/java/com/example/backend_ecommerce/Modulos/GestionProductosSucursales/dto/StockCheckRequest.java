package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.dto;

import lombok.Data;

@Data
public class StockCheckRequest {
    private Integer sucursalId;
    private Integer productoId;
    private String talla;
}
