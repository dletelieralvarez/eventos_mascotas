package com.microserviciosemana3.eventos_mascotas.model; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //genera los get y set 
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con los atributos

public class EventoMascota {

     private int idMascota; //identificador de mascota
     private String nombreMascota; //nombre mascota
     private String razaPerro; //raza del perro
     private String nombreTutor; //nombre tutor/dueño mascota
     private String evento; //tipo evento : ferias, competiciones
     private String fechaEvento; //fecha de realizacion del evento
     private String lugarEvento; //lugar donde se realizara el evento     
}