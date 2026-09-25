package com.project.cartelera.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
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
    public List<Pelicula> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public Optional<Pelicula> buscarPorId(@RequestParam Long id) {
        return servicio.buscarPorId(id);
    }

    @PostMapping
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        Pelicula creada = servicio.crear(pelicula);
        return creada;
    }

    @PutMapping("/{id}")
    public Pelicula editar(@RequestParam Long id, @RequestBody Pelicula pelicula) {
        Pelicula edit = servicio.editar(id, pelicula);
        return edit;
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable long id) {
        servicio.borrar(id);
    }

}
