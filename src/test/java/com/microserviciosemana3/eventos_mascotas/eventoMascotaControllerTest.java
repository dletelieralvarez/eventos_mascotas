package com.microserviciosemana3.eventos_mascotas;

import com.microserviciosemana3.eventos_mascotas.config.SecurityConfig;
import com.microserviciosemana3.eventos_mascotas.controllers.eventoMascotaController; 
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.services.EventoMascotaService;
import com.microserviciosemana3.eventos_mascotas.hateoas.EventoMascotaModelAssembler;

import org.junit.jupiter.api.Test;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@WebMvcTest(eventoMascotaController.class)
@Import(SecurityConfig.class)
@WithMockUser(username = "admin", password = "admin123", roles = { "ADMIN" })
//@AutoConfigureMockMvc(addFilters = false) // Deshabilitar filtros de seguridad para pruebas
public class eventoMascotaControllerTest {
  
    @Autowired
    private MockMvc mockMvc; 
 
    @MockBean
    private EventoMascotaService eventoMascotaService;

    @MockBean
    private EventoMascotaModelAssembler eventoMascotaModelAssembler;    

    @Test
    public void testGetEventoMascotaById() throws Exception {
        EventoMascota eventoMascota = new EventoMascota(1, "Firulais", "Labrador", "Juan Perez", "Competencia", "02-05-2025", "10:00", "Parque Central");
        EntityModel<EventoMascota> eventoMascotaEntityModel = EntityModel.of(eventoMascota,
            linkTo(methodOn(eventoMascotaController.class).retornaEventoMascotaPorId(1)).withSelfRel(),
            linkTo(methodOn(eventoMascotaController.class).retornaTodosLosEventosDeMascotas()).withRel("Lista de Eventos Mascotas"),
            linkTo(methodOn(eventoMascotaController.class).crearEventoMascota(eventoMascota)).withRel("Crear Evento Mascota"),
            linkTo(methodOn(eventoMascotaController.class).actualizarEventoMascota(1, eventoMascota)).withRel("Actualizar Evento Mascota"),
            linkTo(methodOn(eventoMascotaController.class).eliminarEventoMascota(1)).withRel("Eliminar Evento Mascota")
        ); 

        when(eventoMascotaService.getEventoMascotaPorId(1)).thenReturn(java.util.Optional.of(eventoMascota));
        when(eventoMascotaModelAssembler.toModel(eventoMascota)).thenReturn(eventoMascotaEntityModel);

        mockMvc.perform(get("/eventos-mascotas/1").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.data.ID_MASCOTA").value(1))
        .andExpect(jsonPath("$.data.NOMBRE_MASCOTA").value("Firulais"))
        .andExpect(jsonPath("$.data.RAZA_PERRO").value("Labrador"))
        .andExpect(jsonPath("$.data.NOMBRE_TUTOR").value("Juan Perez"))
        .andExpect(jsonPath("$.data.EVENTO").value("Competencia"))
        .andExpect(jsonPath("$.data.FECHA_EVENTO").value("02-05-2025"))
        .andExpect(jsonPath("$.data.HORA_EVENTO").value("10:00"))
        .andExpect(jsonPath("$.data.LUGAR_EVENTO").value("Parque Central")) 
        //links es un arreglo, por lo que se accederé a los links por su indice, 
        //es como lo agregue en mi apiresult
        .andExpect(jsonPath("$.links[0].href").exists()) 
        .andExpect(jsonPath("$.links[1].href").exists()) 
        .andExpect(jsonPath("$.links[2].href").exists())
        .andExpect(jsonPath("$.links[3].href").exists())
        .andExpect(jsonPath("$.links[4].href").exists())
        .andDo(result -> {
            // Imprime la respuesta en la consola
            System.out.println(result.getResponse().getContentAsString());
        });


        //test para pelicula que no existe
        when(eventoMascotaService.getEventoMascotaPorId(11)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(get("/eventos-mascotas/11")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.mensaje").value("No se encontro el evento de mascota con id: 11"))
        .andExpect(jsonPath("$.codigoEstado").value(404))
        .andDo(result ->{
            System.out.println(result.getResponse().getContentAsString()); // Imprime la respuesta en consola
        });
    }

    /* 
    @Test
    public void testActualizarEventoMascota() throws Exception{
        EventoMascota eventoMascotaActualizada = new EventoMascota(); 
        eventoMascotaActualizada.setID_MASCOTA(1);
        eventoMascotaActualizada.setNombreMascota("Firulais");
        eventoMascotaActualizada.setRAZA_PERRO("Labrador");
        eventoMascotaActualizada.setNOMBRE_TUTOR("Juan Perez"); 
        eventoMascotaActualizada.setEVENTO("Competencia");
        eventoMascotaActualizada.setFECHA_EVENTO("02/05/2025");
        eventoMascotaActualizada.setHORA_EVENTO("10:00");
        eventoMascotaActualizada.setLUGAR_EVENTO("Parque Central");

        // Configuramos el comportamiento simulado del servicio para actualizar
        when(eventoMascotaService.actualizarEventoMascota(any(EventoMascota.class), eq(1)))
            .thenReturn(eventoMascotaActualizada);

        mockMvc.perform(put("/eventos-mascotas/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"ID_MASCOTA\":1,\"NOMBRE_MASCOTA\":\"Firulais\",\"RAZA_PERRO\":\"Labrador\",\"NOMBRE_TUTOR\":\"Juan Perez\",\"EVENTO\":\"Competencia\",\"FECHA_EVENTO\":\"02/05/2025\",\"HORA_EVENTO\":\"10:00\",\"LUGAR_EVENTO\":\"Parque Central\"}"))
            .andDo(print())
            .andExpect(status().isOk());
            
            //.andExpect(jsonPath("$.NOMBRE_MASCOTA").value("Nombre de Mascota actualizado"))
            //.andExpect(jsonPath("$.RAZA_PERRO").value("Raza del Perro actualizado"))
            //.andExpect(jsonPath("$.NOMBRE_TUTOR").value("Nombre de Tutor actualizado"))
            //.andExpect(jsonPath("$.EVENTO").value("Evento actualizado"))
            //.andExpect(jsonPath("$.FECHA_EVENTO").value("Fecha del Evento actualizado"))
            //.andExpect(jsonPath("$.HORA_EVENTO").value("Hora del Evento actualizado"))
            //.andExpect(jsonPath("$.LUGAR_EVENTO").value("Lugar del Evento actualizado"));  
    }
           */  
}
