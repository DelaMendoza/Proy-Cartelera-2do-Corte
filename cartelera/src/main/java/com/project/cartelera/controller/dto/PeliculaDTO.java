package com.project.cartelera.controller.dto;

import java.time.LocalDate;

import com.project.cartelera.model.Clasificacion;
import com.project.cartelera.model.Genero;
import com.project.cartelera.model.Pelicula;

public record PeliculaDTO (Long id, String titulo, String descripcion, String duracion, LocalDate fechaEstreno, Genero genero, Clasificacion clasificacion) {

    public static PeliculaDTO de(Pelicula p) {
        return new PeliculaDTO(p.getId(), p.getTitulo(), p.getDescripcion(),
                            p.getDuracion(), p.getFechaEstreno(), p.getGenero(), p.getClasificacion());
    }
}
