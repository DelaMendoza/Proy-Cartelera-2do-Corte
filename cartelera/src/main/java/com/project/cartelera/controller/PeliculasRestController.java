package com.project.cartelera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;  

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.cartelera.model.Pelicula;
import com.project.cartelera.service.PeliculaService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculasRestController {

    private final PeliculaService servicio;

    public PeliculasRestController(PeliculaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<Pelicula> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));
    }

    @PostMapping
    public ResponseEntity<Pelicula> crear(@Valid @RequestBody Pelicula entrada) {
        Pelicula creado = servicio.crear(entrada);
        return ResponseEntity
                .created(URI.create("/api/peliculas/" + creado.getId()))
                .body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> editar(@PathVariable Long id, @Valid @RequestBody Pelicula entrada) {
        Pelicula actualizado = servicio.editar(id, entrada);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        servicio.borrar(id);
        return ResponseEntity.noContent().build();
    }

}
