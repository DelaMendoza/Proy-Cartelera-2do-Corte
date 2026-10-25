package com.project.cartelera.controller.dto;

import java.time.LocalDate;

import com.project.cartelera.model.Clasificacion;
import com.project.cartelera.model.Genero;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PeliculaEntrada(
    @NotBlank(message = "El título es obligatorio")
    String titulo,
    
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion, 

    @NotBlank(message = "La duración es obligatoria")
    String duracion, 

    @NotNull(message = "La fecha de estreno es obligatoria")
    LocalDate fechaEstreno, 

    @NotNull(message = "El género es obligatorio")
    Genero genero, 

    @NotNull(message = "La clasificación es obligatoria")
    Clasificacion clasificacion) {
    
}
