package com.microserviciosemana3.eventos_mascotas.services;
import org.springframework.stereotype.Service;

import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;

import java.util.List;
import java.util.Optional;

@Service
public interface EventoMascotaService {
    List<EventoMascota> getEventosMascotas(); // Método para obtener todos los eventos de mascotas
    Optional<EventoMascota> getEventoMascotaPorId(int idMascota); // Método para obtener un evento de mascota por su ID
    EventoMascota crearEventoMascota(EventoMascota eventoMascota); // Método para guardar un nuevo evento de mascota
    EventoMascota actualizarEventoMascota(EventoMascota eventoMascota, int idMascota); // Método para actualizar un evento de mascota existente
    EventoMascota eliminarEventoMascota(int idMascota); // Método para eliminar un evento de mascota por su ID        
    List<EventoMascota> buscarPorNombreMascota(String nombreMascota);
} 

