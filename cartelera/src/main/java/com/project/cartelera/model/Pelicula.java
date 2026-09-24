package com.project.cartelera.model;

import java.time.LocalDate;

public class Pelicula {
    String titulo;
    String descripcion;
    String duracion;
    LocalDate fechaEstreno;
    Genero genero;
    Clasificacion clasificacion;

    public Pelicula(String titulo, String descripcion, String duracion, LocalDate fechaEstreno, Genero genero, Clasificacion clasificacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.fechaEstreno = fechaEstreno;
        this.genero = genero;
        this.clasificacion = clasificacion;
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
