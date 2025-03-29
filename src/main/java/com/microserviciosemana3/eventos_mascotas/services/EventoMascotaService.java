package com.microserviciosemana3.eventos_mascotas.services;

import org.springframework.stereotype.Service;
import com.microserviciosemana3.eventos_mascotas.model.EventoMascota;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventoMascotaService {
   
    public final List<EventoMascota> eventos = Arrays.asList(
        new EventoMascota(1010,"Killer", "Pastor Aleman", "Sebastian Rojas", "Competición", "28-03-2025", "Parque Ohiggins"), 
        new EventoMascota(2101,"Blanca","Beagle", "Daniela Lira", "Competición", "28-03-2025", "Parque Ohiggins"), 
        new EventoMascota(3233,"Luna", "Fox Terrier", "Fox", "Competición", "28-03-2025", "Parque Ohiggins"),
        new EventoMascota(4242,"Piti", "Pitbull", "Luis Moya", "Competición", "28-03-2025", "Parque Ohiggins"),
        new EventoMascota(5252,"Mili", "Border Collie", "Eduardo Da Fonseca", "Competición", "28-03-2025", "Parque Ohiggins"),
        new EventoMascota(6363,"Boby", "Dogo del Tibet", "Francisco Ramirez", "Desfile", "29-03-2025", "Cerro San Cristobal"),
        new EventoMascota(7171,"Saly", "West Highland Terrier", "Alejandra Caro", "Desfile", "29-03-2025", "Cerro San Cristobal"),
        new EventoMascota(8283,"Zeus", "Siberian Husky", "Natalia Peña", "Desfile", "29-03-2025", "Cerro San Cristobal"),
        new EventoMascota(9564,"Niza", "Podenco Canario", "Javier Piña", "Desfile", "29-03-2025", "Cerro San Cristobal"),
        new EventoMascota(1155,"Draco", "Mastín Español", "Leonardo Diaz", "Desfile", "29-03-2025", "Cerro San Cristobal"),
        new EventoMascota(1313,"Firulais", "Boxer", "Ana Maria Alvarez", "Desfile", "29-03-2025", "Cerro San Cristobal")
    );

    public List<EventoMascota> getListaEventoMascotas(){
        return eventos;
    }

    public Optional<EventoMascota> getEventoMascotaPorId(int idMascota){
        return eventos.stream().filter(p-> p.getIdMascota() == idMascota).findFirst(); 

    }

}

