package com.project.cartelera.service;

import com.project.cartelera.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> listar();
    Pelicula crear(Pelicula pelicula);
    void borrar(Long id);
    Optional<Pelicula> obtenerPorId(Long id);
    Pelicula editar(Long id, Pelicula pelicula);
}