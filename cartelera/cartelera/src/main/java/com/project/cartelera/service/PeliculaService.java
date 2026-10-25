package com.project.cartelera.service;

import java.util.List;

import com.project.cartelera.model.Pelicula;

public interface PeliculaService {
    List<Pelicula> listar();
    List<Pelicula> buscarPorTitulo(String texto);
    Pelicula crear(Pelicula datos);
    void borrar(Long id);
    Pelicula buscarPorId(Long id);
    Pelicula editar(Long id, Pelicula datos);
}
