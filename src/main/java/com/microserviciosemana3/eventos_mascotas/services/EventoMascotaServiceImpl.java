package com.microserviciosemana3.eventos_mascotas.services;
import org.springframework.stereotype.Service;

import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.repository.EventoMascotaRepository;

import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventoMascotaServiceImpl implements EventoMascotaService {
    @Autowired
    private EventoMascotaRepository eventoMascotaRepository;

    @Override
    public List<EventoMascota> getEventosMascotas() {
        log.debug("Servicio : getEventosMascotas()"); 
        return eventoMascotaRepository.findAll(); // retorna todos los eventos de mascotas
    }
    
    @Override
    public Optional<EventoMascota> getEventoMascotaPorId(int idMascota) {
        log.debug("Servicio : getEventoMascotaPorId()"); 
        return eventoMascotaRepository.findById(idMascota); // retorna un evento de mascota por su ID
    }

    @Override
    public EventoMascota crearEventoMascota(EventoMascota eventoMascota) {
        log.debug("Servicio : crearEventoMascota()"); 
        if(eventoMascotaRepository.existsById(eventoMascota.getID_MASCOTA())) {
            throw new IllegalArgumentException("El evento de mascota ya existe con el ID:" + eventoMascota.getID_MASCOTA()); // lanza una excepción si el evento de mascota ya existe
        }
        return eventoMascotaRepository.save(eventoMascota); // guarda un nuevo evento de mascota
    }

    @Override
    public EventoMascota actualizarEventoMascota(EventoMascota eventoMascota, int idMascota) {
        log.debug("Servicio : actualizarEventoMascota()"); 
        if(!eventoMascotaRepository.existsById(idMascota)) {
            throw new IllegalArgumentException("El evento de mascota no existe con el ID:" + idMascota); // lanza una excepción si el evento de mascota no existe
        }
        return eventoMascotaRepository.save(eventoMascota); // actualiza un evento de mascota existente
    }

    @Override
    public EventoMascota eliminarEventoMascota(int idMascota){
        log.debug("Servicio : eliminarEventoMascota()"); 
        Optional<EventoMascota> eventoMascota = eventoMascotaRepository.findById(idMascota);
        if(eventoMascota.isPresent()){
            eventoMascotaRepository.deleteById(idMascota);
            return eventoMascota.get(); 
        } else {
            return null;
        }
    }

    @PostConstruct
    public void init() {
        log.debug("Servicio : init(), me indica cuantos registro hay en la base de datos"); 
        System.out.println("Cantidad de eventos: " + eventoMascotaRepository.count());
    }

    @Override
    public List<EventoMascota> buscarPorNombreMascota(String nombreMascota) {
        log.debug("Servicio : buscarPorNombreMascota()"); 
        return eventoMascotaRepository.findByNombreMascotaContainingIgnoreCase(nombreMascota);
    }
}
