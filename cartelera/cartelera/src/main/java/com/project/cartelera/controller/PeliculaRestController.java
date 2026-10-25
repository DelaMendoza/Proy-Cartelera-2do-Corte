package com.project.cartelera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cartelera.controller.dto.PeliculaDTO;
import com.project.cartelera.controller.dto.PeliculaEntrada;
import com.project.cartelera.exception.NoEncontradoException;
import com.project.cartelera.exception.ReglaNegocioException;
import com.project.cartelera.model.Pelicula;
import com.project.cartelera.service.PeliculaService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaRestController {

    private final PeliculaService servicio;

    public PeliculaRestController(PeliculaService peliculaService) {
        this.servicio = peliculaService;
    }
    
    @GetMapping
    public List<PeliculaDTO> listar() {
        return servicio.listar().stream().map(PeliculaDTO::de).toList();
    }

    @GetMapping("/{id}")
    public PeliculaDTO buscarPorId(@PathVariable Long id) {
        return PeliculaDTO.de(servicio.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> crear(@Valid @RequestBody PeliculaEntrada entrada) {
        Pelicula creada = servicio.crear(new Pelicula(entrada.titulo(), entrada.descripcion(), 
                                            entrada.duracion(), entrada.fechaEstreno(), entrada.genero(), entrada.clasificacion()));
        return ResponseEntity
                .created(URI.create("/api/peliculas/" + creada.getId()))   // 201 + Location
                .body(PeliculaDTO.de(creada));
    }

    @PutMapping("/{id}")
    public PeliculaDTO editar(@PathVariable Long id, @Valid @RequestBody PeliculaEntrada entrada) {
        Pelicula actualizada = servicio.editar(id, new Pelicula(entrada.titulo(), entrada.descripcion(), 
                                            entrada.duracion(), entrada.fechaEstreno(), entrada.genero(), entrada.clasificacion()));
        return PeliculaDTO.de(actualizada);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) {
        servicio.borrar(id);                                           // 204
    }

    @ExceptionHandler(NoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> noEncontrado(NoEncontradoException e) {
        return Map.of("error", e.getMessage());
    }


    @ExceptionHandler(ReglaNegocioException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> reglaRota(ReglaNegocioException e) {
        return Map.of("error", e.getMessage());                        // 409
    }
    
}
