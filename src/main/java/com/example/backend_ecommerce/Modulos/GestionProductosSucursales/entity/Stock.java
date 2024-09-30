package com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private String talla;

    // Relación con Productos
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    // Relación con Sucursales
    @ManyToOne
    @JoinColumn(name = "sucursal_id", nullable = false)
    private Sucursales sucursal;

    public Stock() {
    }

    public Stock(int id, int cantidad, String talla, Productos producto, Sucursales sucursal) {
        this.id = id;
        this.cantidad = cantidad;
        this.talla = talla;
        this.producto = producto;
        this.sucursal = sucursal;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }
}
