package com.microserviciosemana3.eventos_mascotas.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;

public interface EventoMascotaRepository extends JpaRepository<EventoMascota, Integer> {
    List<EventoMascota> findByNombreMascotaContainingIgnoreCase(String nombreMascota);
} 
