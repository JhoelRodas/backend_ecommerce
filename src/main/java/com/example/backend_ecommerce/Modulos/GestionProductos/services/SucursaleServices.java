package com.example.backend_ecommerce.Modulos.GestionProductos.services;

import org.springframework.stereotype.Service;

import com.example.backend_ecommerce.Modulos.GestionProductos.entity.Sucursales;
import com.example.backend_ecommerce.Modulos.GestionProductos.repository.SucursalesRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SucursaleServices {
    private final SucursalesRepository sucursalesRepository;


    // Obtener todas las sucursales
    public List<Sucursales> obtenerTodasLasSucursales() {
        return sucursalesRepository.findAll();
    }

    // Obtener una sucursal por ID
    public Optional<Sucursales> obtenerSucursalPorId(Integer id) {
        return sucursalesRepository.findById(id);
    }

    // Guardar una nueva sucursal
    public Sucursales guardarSucursal(Sucursales sucursal) {
        return sucursalesRepository.save(sucursal);
    }

    // Eliminar una sucursal por ID
    public void eliminarSucursalPorId(Integer id) {
        sucursalesRepository.deleteById(id);
    }

    // Actualizar una sucursal existente
    public Sucursales actualizarSucursal(Integer id, Sucursales sucursalActualizada) {
        return sucursalesRepository.findById(id)
                .map(sucursal -> {
                    sucursal.setNombre(sucursalActualizada.getNombre());
                    sucursal.setDireccion(sucursalActualizada.getDireccion());
                    sucursal.setTelefono(sucursalActualizada.getTelefono());
                    sucursal.setHoraAtencion(sucursalActualizada.getHoraAtencion());
                    return sucursalesRepository.save(sucursal);
                })
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada con el ID: " + id));
    }
}
