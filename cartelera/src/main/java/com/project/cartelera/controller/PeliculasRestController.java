package com.project.cartelera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.cartelera.model.Pelicula;
import com.project.cartelera.service.PeliculaService;
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
    public String listar() {
        return new String();
    }

    @GetMapping("/{id}")
    public String buscarId(@RequestParam Long id) {
        return new String();
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return pelicula;
    }

    @PutMapping("/{id}")
    public String actualizar(@RequestParam Long id, @RequestBody Pelicula pelicula) {
        return new String();
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable long id) {
    }

}
