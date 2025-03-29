package com.microserviciosemana3.eventos_mascotas.controllers;

import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.services.EventoMascotaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/eventos-mascotas")

public class eventoMascotaController {
    
    private final EventoMascotaService eventoMascotaService; 

    public eventoMascotaController(EventoMascotaService eventoMascotaService){
        this.eventoMascotaService = eventoMascotaService; 
    }

    @GetMapping
    public List<EventoMascota> retornaTodosLosEventosDeMascotas(){
        return eventoMascotaService.getListaEventoMascotas(); 
    }

    @GetMapping("/{idMascota}")
    public EventoMascota retornaEventoMascotaPorId(@PathVariable int idMascota){
        Optional<EventoMascota> evento = eventoMascotaService.getEventoMascotaPorId(idMascota);
        return evento.orElseThrow(() -> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Mascota con id: " + idMascota)
        );
    }

}
