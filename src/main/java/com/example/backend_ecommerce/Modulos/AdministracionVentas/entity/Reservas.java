package com.example.backend_ecommerce.Modulos.AdministracionVentas.entity;

import com.example.backend_ecommerce.Modulos.GestionUsuarios.entity.User;
import com.example.backend_ecommerce.Modulos.GestionProductosSucursales.entity.Productos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservas")
public class Reservas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación Muchos a Uno con User (muchas reservas pueden ser hechas por un usuario)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Relación Muchos a Uno con Productos (muchas reservas pueden ser para un mismo producto)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", nullable = false)
    private Productos producto;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva;

    @Column(name = "fecha_recogida", nullable = true)
    private LocalDateTime fechaRecogida;

    @Column(name = "estado", nullable = false)
    private String estado;

}