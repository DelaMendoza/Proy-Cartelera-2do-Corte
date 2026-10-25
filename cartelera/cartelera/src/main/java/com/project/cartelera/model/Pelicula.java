package com.project.cartelera.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @NotBlank(message = "El título es obligatorio")
    @Size(max = 120, message = "El título no puede superar los 120 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Column(nullable = false)
    private String descripcion;

    @NotBlank(message = "La duración es obligatoria")
    @Column(nullable = false)
    private String duracion;

    @NotNull(message = "La fecha de estreno es obligatoria")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(nullable = false, name = "fecha_estreno")
    private LocalDate fechaEstreno;

    @NotNull(message = "El género es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genero genero;

    @NotNull(message = "La clasificación es obligatoria")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Clasificacion clasificacion;

    // Constructor vacío para JPA
    public Pelicula() {
    }

    public Pelicula(String titulo, String descripcion, String duracion, LocalDate fechaEstreno, Genero genero, Clasificacion clasificacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.fechaEstreno = fechaEstreno;
        this.genero = genero;
        this.clasificacion = clasificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

}
