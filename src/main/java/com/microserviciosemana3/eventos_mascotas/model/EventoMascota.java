package com.microserviciosemana3.eventos_mascotas.model; 
import com.microserviciosemana3.eventos_mascotas.validation.FechaValida;
import com.microserviciosemana3.eventos_mascotas.validation.HoraValida;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column; 
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.Max;


@Data //genera los get y set 
@NoArgsConstructor //constructor vacio
@AllArgsConstructor //constructor con los atributos
@Entity //indica que es una entidad de la base de datos
@Table(name = "EVENTO_MASCOTA") //nombre de la tabla en la base de datos
public class EventoMascota {

     @Id //indica que es la llave primaria
     @GeneratedValue(strategy = GenerationType.IDENTITY) //se genera automaticamente el id
     @Column(name = "ID_MASCOTA") //nombre de la columna en la base de datos
     @JsonProperty("ID_MASCOTA") //nombre del atributo en el json
     private int ID_MASCOTA; //identificador de mascota
     
     @NotBlank(message = "Nombre de Mascota no puede estar vacío")
     @Size(min = 1, max = 200, message = "El Nombre de mascota debe tener entre 1 y 200 caracteres")
     @Column(name = "NOMBRE_MASCOTA") //nombre de la columna en la base de datos
     @JsonProperty("NOMBRE_MASCOTA")
     private String nombreMascota; //nombre mascota
     

     @NotBlank(message = "Raza del Perro no puede estar vacía")
     @Size(min = 1, max = 100, message = "Raza del Perro debe tener entre 1 y 100 caracteres")
     @Column(name = "RAZA_PERRO") //nombre de la columna en la base de datos
     @JsonProperty("RAZA_PERRO")
     private String RAZA_PERRO; //raza del perro
     
     @NotBlank(message = "Nombre de tutor no puede estar vacío")
     @Size(min = 1, max = 250, message = "Nombre de tutor debe tener entre 1 y 250 caracteres")
     @Column(name = "NOMBRE_TUTOR") //nombre de la columna en la base de datos
     @JsonProperty("NOMBRE_TUTOR")
     private String NOMBRE_TUTOR; //nombre tutor/dueño mascota
     
     @NotBlank(message = "Descripción del Evento no puede estar vacío")
     @Size(min = 1, max = 200, message = "Descripción del Evento debe tener entre 1 y 200 caracteres")
     @Column(name = "EVENTO") //nombre de la columna en la base de datos
     @JsonProperty("EVENTO")
     private String EVENTO; //tipo evento : ferias, competiciones
     
     @NotBlank(message = "Fecha del Evento no puede estar vacía")
     //@Size(min = 1, max = 10, message = "Fecha del Evento debe tener entre 1 y 10 caracteres")
     @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Fecha del Evento debe tener un formato dd/MM/yyyy")
     @FechaValida //validacion de la fecha 
     @Column(name = "FECHA_EVENTO") //nombre de la columna en la base de datos
     @JsonProperty("FECHA_EVENTO")
     private String FECHA_EVENTO; //fecha de realizacion del evento
     
     @NotBlank(message = "Hora del Evento no puede estar vacía")
     //@Size(min = 1, max = 5, message = "Hora del Evento debe tener entre 1 y 5 caracteres")
     @HoraValida //validacion de la hora
     @Column(name = "HORA_EVENTO") //nombre de la columna en la base de datos
     @JsonProperty("HORA_EVENTO")
     private String HORA_EVENTO; //hora de realizacion del evento
     
     @NotBlank(message = "Lugar del Evento no puede estar vacía")
     @Size(min = 1, max = 200, message = "Lugar del Evento debe tener entre 1 y 200 caracteres")
     @Column(name = "LUGAR_EVENTO") //nombre de la columna en la base de datos
     @JsonProperty("LUGAR_EVENTO")
     private String LUGAR_EVENTO; //lugar donde se realizara el evento     
}