package com.microserviciosemana3.eventos_mascotas;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;
import com.microserviciosemana3.eventos_mascotas.repository.EventoMascotaRepository;
//import com.microserviciosemana3.eventos_mascotas.services.EventoMascotaService;
import com.microserviciosemana3.eventos_mascotas.services.EventoMascotaServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EventoMascotaServiceTest {
 
    @Mock
    private EventoMascotaRepository eventoMascotaRepository;
    @InjectMocks
    private EventoMascotaServiceImpl eventoMascotaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); //inicializa los mocks
        //eventoMascotaRepository = mock(EventoMascotaRepository.class);
        //eventoMascotaService = new EventoMascotaServiceImpl(eventoMascotaRepository);
    }

    @Test
    public void testGetEventosMascotas() {
        //simulación de 2 eventos de mascotas como si vinieran de la base de datos
        EventoMascota evento1 = new EventoMascota(1, "Nombre 1", "Raza 1", "Tuto 1", "Evento 1", "02-05-2025", "21:30", "Lugar del evento 1"); 
        EventoMascota evento2 = new EventoMascota(2, "Nombre 2", "Raza 2", "Tutor 2", "Evento 2", "02-05-2025", "21:35", "Lugar del evento 2");

        //retorno de la lista de eventos de mascotas
        //when(eventoMascotaRepository.findAll(Sort.by(Sort.Direction.ASC, "ID_MASCOTA"))).thenReturn(Arrays.asList(evento1, evento2));
        when(eventoMascotaRepository.findAll()).thenReturn(Arrays.asList(evento1, evento2));
        //llamada al método a probar
        List<EventoMascota> eventosMascotas = eventoMascotaService.getEventosMascotas();

        //verificación de que el tamaño de la lista es 2
        assertEquals(2, eventosMascotas.size());
        //verificación de que el primer evento es el evento1
        assertEquals("Nombre 1", eventosMascotas.get(0).getNombreMascota());
    }

    @Test
    public void testGetEventoMascotaPorId() {
        EventoMascota evento = new EventoMascota(1, "Firulais", "Labrador", "Pedro", "Vacunación", "01-01-2025", "10:00", "Veterinaria San Juan");

        when(eventoMascotaRepository.findById(1)).thenReturn(Optional.of(evento));

        Optional<EventoMascota> resultado = eventoMascotaService.getEventoMascotaPorId(1);

        assertTrue(resultado.isPresent());
        assertEquals("Firulais", resultado.get().getNombreMascota());
    }

    @Test
    public void testCrearEventoMascota() {
        EventoMascota evento = new EventoMascota(0, "Luna", "Poodle", "Ana", "Desparasitación", "03-05-2025", "15:00", "Clínica Z");

        when(eventoMascotaRepository.save(evento)).thenReturn(evento);

        EventoMascota resultado = eventoMascotaService.crearEventoMascota(evento);

        assertNotNull(resultado);
        assertEquals("Luna", resultado.getNombreMascota());
    }

    @Test
    public void testActualizarEventoMascota() {
        EventoMascota eventoActualizado = new EventoMascota(1, "Toby", "Beagle", "Luis", "Cirugía", "06-05-2025", "14:00", "PetCare");

        when(eventoMascotaRepository.existsById(1)).thenReturn(true);
        when(eventoMascotaRepository.save(any(EventoMascota.class))).thenReturn(eventoActualizado);

        EventoMascota resultado = eventoMascotaService.actualizarEventoMascota(eventoActualizado, 1);

        assertEquals("Cirugía", resultado.getEVENTO());
        assertEquals("06-05-2025", resultado.getFECHA_EVENTO());
    }

    @Test
    public void testEliminarEventoMascota() {
        EventoMascota evento = new EventoMascota(1, "Max", "Golden", "Carla", "Desfile", "04-05-2025", "11:00", "Vet 123");

        when(eventoMascotaRepository.findById(1)).thenReturn(Optional.of(evento));
        doNothing().when(eventoMascotaRepository).delete(evento);

        EventoMascota eliminado = eventoMascotaService.eliminarEventoMascota(1);

        assertEquals("Max", eliminado.getNombreMascota());
        verify(eventoMascotaRepository, times(1)).deleteById(1);
    }

    @Test
    public void testBuscarPorNombreMascota() {
        EventoMascota evento = new EventoMascota(1, "Rocky", "Boxer", "Felipe", "Consulta", "07-05-2025", "12:00", "PetHome");

        when(eventoMascotaRepository.findByNombreMascotaContainingIgnoreCase("rocky"))
            .thenReturn(Arrays.asList(evento));

        List<EventoMascota> resultados = eventoMascotaService.buscarPorNombreMascota("rocky");

        assertEquals(1, resultados.size());
        assertEquals("Rocky", resultados.get(0).getNombreMascota());
    }
}
