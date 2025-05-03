package com.microserviciosemana3.eventos_mascotas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.microserviciosemana3.eventos_mascotas.model.ApiResult;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.services.EventoMascotaService;
//import org.springframework.data.jpa.repository.Query;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/eventos-mascotas")
//@CrossOrigin(origins = "*")

public class eventoMascotaController {
    
    @Autowired
    //private final EventoMascotaService eventoMascotaService; 
    private EventoMascotaService eventoMascotaService;

    //public eventoMascotaController(EventoMascotaService eventoMascotaService){
    //    this.eventoMascotaService = eventoMascotaService; 
    //}

    @GetMapping    
    public ResponseEntity<ApiResult<List<EventoMascota>>> retornaTodosLosEventosDeMascotas(){
        try{
            log.info("Get / eventos-mascotas - Se obtiene la lista de eventos de mascotas");
            List<EventoMascota> eventos = eventoMascotaService.getEventosMascotas();
            //Si la lista de eventos está vacía, se devuelve un mensaje de error
            if(eventos.isEmpty()){
                log.warn("No se encontraron eventos de mascotas");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResult<>("No se encontraron eventos de mascotas", null, HttpStatus.NOT_FOUND.value()));
            }
            return ResponseEntity.ok(new ApiResult<>("Lista de Eventos Mascotas encontrada es : ", eventos, HttpStatus.OK.value()));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResult<>("Error al obtener listado de Eventos Mascotas",  null , HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @GetMapping("/{idMascota}")
    public ResponseEntity<ApiResult<EventoMascota>> retornaEventoMascotaPorId(@PathVariable int idMascota){
        try{
            log.info("Get / eventos-mascotas - Se obtiene el evento de mascota con id: " + idMascota);
            Optional<EventoMascota> eventoMascota = eventoMascotaService.getEventoMascotaPorId(idMascota);
            if(eventoMascota.isPresent()){
                log.info("Evento Mascota encontrado con id: " + idMascota);
                
                //links HATEOAS
                List<Link> links = List.of(
                    linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(idMascota)).withSelfRel(),
                    linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("Lista de Eventos Mascotas"),
                    linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota.get())).withRel("Crear Evento Mascota"),
                    linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(idMascota, eventoMascota.get())).withRel("Actualizar Evento Mascota"),
                    linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(idMascota)).withRel("Eliminar Evento Mascota")
                );

                return ResponseEntity.ok(new ApiResult<>("Evento Mascota encontrado es : ", eventoMascota.get(), HttpStatus.OK.value(), links));}
            else{
                log.warn("No se encontro el evento de mascota con id: " + idMascota);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResult<>("No se encontro el evento de mascota con id: " + idMascota, null, HttpStatus.NOT_FOUND.value()));
            }
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResult<>("Error al obtener Evento Mascota por id", null, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResult<?>> crearEventoMascota(@Valid @RequestBody EventoMascota eventoMascota){
        try{
            log.info("Post / eventos-mascotas - Se crea un nuevo evento de mascota");
            EventoMascota nuevoEvento = eventoMascotaService.crearEventoMascota(eventoMascota);
            
             //links HATEOAS
             List<Link> links = List.of(
                linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(eventoMascota.getID_MASCOTA())).withSelfRel(),
                linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("Lista de Eventos Mascotas"),
                linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota)).withRel("Crear Evento Mascota"),
                linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(eventoMascota.getID_MASCOTA(), eventoMascota)).withRel("Actualizar Evento Mascota"),
                linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(eventoMascota.getID_MASCOTA())).withRel("Eliminar Evento Mascota")
            );

            ApiResult<List<EventoMascota>> respuesta = new ApiResult<>(
                "Evento Mascota creado con éxito",
                List.of(nuevoEvento), 
                HttpStatus.CREATED.value(), 
                links
            ); 

            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }
        catch(Exception e){
            ApiResult<Object> error = new ApiResult<>(
                "Error al insertar Evento Mascota :" + e.getMessage(),
                null, 
                HttpStatus.INTERNAL_SERVER_ERROR.value()
            );

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/{idMascota}")
    public ResponseEntity<ApiResult<List<EventoMascota>>> actualizarEventoMascota(@PathVariable int idMascota, @Valid @RequestBody EventoMascota eventoMascota ){
        try{
            log.info("Put / eventos-mascotas - Se actualiza el evento de mascota con id: " + idMascota);
            Optional<EventoMascota> buscarEventoMascota = eventoMascotaService.getEventoMascotaPorId(idMascota);

            if(!buscarEventoMascota.isPresent()){
                log.warn("No se encontro el evento de mascota con id: " + idMascota);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                       .body(new ApiResult<>("Evento Mascota no encontrado", null, HttpStatus.NOT_FOUND.value()));
            }

             //links HATEOAS
             List<Link> links = List.of(
                linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(idMascota)).withSelfRel(),
                linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("Lista de Eventos Mascotas"),
                linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota)).withRel("Crear Evento Mascota"),
                linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(idMascota, eventoMascota)).withRel("Actualizar Evento Mascota"),
                linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(idMascota)).withRel("Eliminar Evento Mascota")
            );

            //si el evento existe, se actualiza
            EventoMascota evento = eventoMascotaService.getEventoMascotaPorId(idMascota).get();
            evento.setNombreMascota(eventoMascota.getNombreMascota());
            evento.setRAZA_PERRO(eventoMascota.getRAZA_PERRO());
            evento.setNOMBRE_TUTOR(eventoMascota.getNOMBRE_TUTOR());
            evento.setEVENTO(eventoMascota.getEVENTO());
            evento.setFECHA_EVENTO(eventoMascota.getFECHA_EVENTO());  
            evento.setHORA_EVENTO(eventoMascota.getHORA_EVENTO());
            evento.setLUGAR_EVENTO(eventoMascota.getLUGAR_EVENTO());
            //guardar el evento actualizado
            log.info("Evento Mascota actualizado con id: " + idMascota);
            EventoMascota eventoActualizado = eventoMascotaService.actualizarEventoMascota(evento, idMascota);
            //retornar el resultado con ApiResult
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResult<>("Evento Mascota actualizado con éxito", List.of(eventoActualizado), HttpStatus.OK.value(), links));
        }
        catch(Exception e){
            //en caso de error
            log.error("Error al actualizar el evento de mascota con id: " + idMascota, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResult<>("Error al actualizar Evento Mascota", null, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }

    }

    @DeleteMapping("/{idMascota}")
    public ResponseEntity<ApiResult<String>> eliminarEventoMascota(@PathVariable int idMascota){
        try{
            log.info("Delete / eventos-mascotas - Se elimina el evento de mascota con id: " + idMascota);
            //retorna los datos del evento a eliminar
            Optional<EventoMascota> eventoMascotaExistente = eventoMascotaService.getEventoMascotaPorId(idMascota);
            if(!eventoMascotaExistente.isPresent()){
                log.warn("No se encontro el evento de mascota con id: " + idMascota);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResult<>("Evento Mascota no encontrado", null, HttpStatus.NOT_FOUND.value()));
            }
            
             //links HATEOAS
             List<Link> links = List.of(
                linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(idMascota)).withSelfRel(),
                linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("Lista de Eventos Mascotas"),
                //linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota)).withRel("Crear Evento Mascota"),
                //linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(idMascota, eventoMascota.get())).withRel("Actualizar Evento Mascota"),
                linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(idMascota)).withRel("Eliminar Evento Mascota")
            );

            //si encuentra el evento lo elimina
            log.info("Evento Mascota encontrado con id: " + idMascota);
            eventoMascotaService.eliminarEventoMascota(idMascota);
            return ResponseEntity.status(HttpStatus.OK)            
                    .body(new ApiResult<>("Evento Mascota eliminado con éxito", "ID Mascota eliminado: " + idMascota, HttpStatus.OK.value(), links));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResult<>("Error al eliminar Evento Mascota", null, HttpStatus.INTERNAL_SERVER_ERROR.value())); 
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResult<List<EventoMascota>>> buscarEventosPorNombre(@RequestParam String nombre) {
        try {
            log.info("Get / eventos-mascotas/buscar - Se busca el evento de mascota por nombre: " + nombre);
            List<EventoMascota> resultados = eventoMascotaService.buscarPorNombreMascota(nombre);

            if (resultados.isEmpty()) {
                log.warn("No se encontraron mascotas con el nombre: " + nombre);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResult<>("No se encontraron mascotas con ese nombre", null, HttpStatus.NOT_FOUND.value()));
            }

            
            return ResponseEntity.ok(new ApiResult<>("Mascotas encontradas", resultados, HttpStatus.OK.value()));
        } catch (Exception e) {
            log.error("Error al buscar eventos por nombre: " + nombre, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResult<>("Error al buscar eventos por nombre", null, HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }
}
