package com.project.cartelera.service;

import com.project.cartelera.controller.dto.PeliculaEntrada;
import com.project.cartelera.model.Pelicula;
import java.util.List;
import java.util.Optional;

public interface PeliculaService {
    List<Pelicula> listar();
    Pelicula crear (PeliculaEntrada datos);
    void borrar(Long id);
    Optional<Pelicula> buscarPorId(Long id);
    Pelicula editar(Long id, PeliculaEntrada datos);
}