package com.project.cartelera.controller.dto;

import java.time.LocalDate;

import com.project.cartelera.model.Clasificacion;
import com.project.cartelera.model.Genero;

public record PeliculaEntrada(String titulo, String descripcion, String duracion, LocalDate fechaEstreno, Genero genero, Clasificacion clasificacion) {
    
}
