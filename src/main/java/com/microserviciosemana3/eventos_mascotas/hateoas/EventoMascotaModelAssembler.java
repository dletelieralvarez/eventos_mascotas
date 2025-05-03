package com.microserviciosemana3.eventos_mascotas.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*; // Importa funciones para generar enlaces
import com.microserviciosemana3.eventos_mascotas.controllers.eventoMascotaController;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class EventoMascotaModelAssembler implements RepresentationModelAssembler<EventoMascota, EntityModel<EventoMascota>> {

    @Override
    public @NonNull EntityModel<EventoMascota> toModel(@NonNull EventoMascota eventoMascota) {
        return EntityModel.of(eventoMascota,
                linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(eventoMascota.getID_MASCOTA())).withSelfRel(),
                linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("all"),
                linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota)).withRel("create"),
                linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(eventoMascota.getID_MASCOTA())).withRel("delete"),
                linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(eventoMascota.getID_MASCOTA(), eventoMascota)).withRel("update"));
    }
    
}
