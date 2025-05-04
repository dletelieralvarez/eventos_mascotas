package com.microserviciosemana3.eventos_mascotas;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.repository.EventoMascotaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EventoMascotaRepositoryTest {
    
    @Autowired
    private EventoMascotaRepository eventoMascotaRepository;
    
    // Prueba de la creación de un EventoMascota
    @Test
    public void testGuardarEventoMascota() {

        //EventoMascota eventoMascota = new EventoMascota(1, "Firulais", "Labrador", "Juan Perez", "Competencia", "02/05/2025", "10:00", "Parque Central");
        //eventoMascotaRepository.save(eventoMascota); 
        //Optional<EventoMascota> eventoEncontrado = eventoMascotaRepository.findById(1);
        //assertTrue(eventoEncontrado.isPresent());
        //assertEquals("Firulais", eventoEncontrado.get().getNombreMascota());

        
        /* 
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setID_MASCOTA(1);
        eventoMascota.setNombreMascota("Firulais");
        eventoMascota.setRAZA_PERRO("Labrador");
        eventoMascota.setNOMBRE_TUTOR("Juan Perez");
        eventoMascota.setEVENTO("Competencia");
        eventoMascota.setFECHA_EVENTO("02/05/2025");
        eventoMascota.setHORA_EVENTO("10:00");
        eventoMascota.setLUGAR_EVENTO("Parque Central");

        EventoMascota eventoGuardado = eventoMascotaRepository.save(eventoMascota);

        // Verificar que el evento se guardó correctamente
        assertNotNull(eventoGuardado);
        assertEquals(1, eventoGuardado.getID_MASCOTA());
        assertEquals("Firulais", eventoGuardado.getNombreMascota());
        */
    }
    /*
    // Prueba de la búsqueda de un EventoMascota por ID
    @Test
    public void testEncontrarEventoMascotaPorId() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setID_MASCOTA(2);
        eventoMascota.setNombreMascota("Bobby");
        eventoMascota.setRAZA_PERRO("Beagle");
        eventoMascota.setNOMBRE_TUTOR("Maria Lopez");
        eventoMascota.setEVENTO("Exposición");
        eventoMascota.setFECHA_EVENTO("10/06/2025");
        eventoMascota.setHORA_EVENTO("14:00");
        eventoMascota.setLUGAR_EVENTO("Centro de Convenciones");

        eventoMascotaRepository.save(eventoMascota);

        // Buscar el evento por ID
        Optional<EventoMascota> encontrado = eventoMascotaRepository.findById(2);

        // Verificar que se encontró el evento
        assertTrue(encontrado.isPresent());
        assertEquals("Bobby", encontrado.get().getNombreMascota());
    }

    // Prueba de la búsqueda por nombre de evento
    @Test
    public void testBuscarEventosPorNombreEvento() {
        EventoMascota eventoMascota1 = new EventoMascota();
        eventoMascota1.setID_MASCOTA(3);
        eventoMascota1.setNombreMascota("Max");
        eventoMascota1.setRAZA_PERRO("Golden Retriever");
        eventoMascota1.setNOMBRE_TUTOR("Carlos Ruiz");
        eventoMascota1.setEVENTO("Competencia");
        eventoMascota1.setFECHA_EVENTO("15/05/2025");
        eventoMascota1.setHORA_EVENTO("16:00");
        eventoMascota1.setLUGAR_EVENTO("Cancha de Perros");

        EventoMascota eventoMascota2 = new EventoMascota();
        eventoMascota2.setID_MASCOTA(4);
        eventoMascota2.setNombreMascota("Rocky");
        eventoMascota2.setRAZA_PERRO("Rottweiler");
        eventoMascota2.setNOMBRE_TUTOR("Ana Gómez");
        eventoMascota2.setEVENTO("Competencia");
        eventoMascota2.setFECHA_EVENTO("20/06/2025");
        eventoMascota2.setHORA_EVENTO("17:00");
        eventoMascota2.setLUGAR_EVENTO("Estadio Municipal");

        eventoMascotaRepository.save(eventoMascota1);
        eventoMascotaRepository.save(eventoMascota2);

        // Buscar eventos por nombre
        List<EventoMascota> eventos = eventoMascotaRepository.findByNombreMascotaContainingIgnoreCase("Rocky");

        // Verificar que se encontraron los eventos correctos
        assertNotNull(eventos);
        assertEquals(2, eventos.size());
        for (EventoMascota evento : eventos) {
            assertEquals("Rocky", evento.getEVENTO());
        }
    }

    // Prueba de la eliminación de un EventoMascota
    @Test
    public void testEliminarEventoMascota() {
        EventoMascota eventoMascota = new EventoMascota();
        eventoMascota.setID_MASCOTA(5);
        eventoMascota.setNombreMascota("Toby");
        eventoMascota.setRAZA_PERRO("Pitbull");
        eventoMascota.setNOMBRE_TUTOR("Luis Pérez");
        eventoMascota.setEVENTO("Concurso");
        eventoMascota.setFECHA_EVENTO("05/07/2025");
        eventoMascota.setHORA_EVENTO("11:00");
        eventoMascota.setLUGAR_EVENTO("Plaza Mayor");

        EventoMascota eventoGuardado = eventoMascotaRepository.save(eventoMascota);
        eventoMascotaRepository.delete(eventoGuardado);

        // Verificar que el evento ha sido eliminado
        Optional<EventoMascota> eliminado = eventoMascotaRepository.findById(5);
        assertFalse(eliminado.isPresent());
    }
       */ 

}