package com.project.cartelera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.cartelera.model.Pelicula;
import com.project.cartelera.service.PeliculaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;


@Controller 
@RequestMapping("/peliculas")
public class PeliculasController {

    private final PeliculaService servicio;

    public PeliculasController(PeliculaService servicio){
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String buscar, Model model) {
        var peliculas = servicio.listar();
        if (buscar != null && !buscar.isBlank()) {
            String texto = buscar.trim().toLowerCase();
            peliculas = peliculas.stream()
                    .filter(p -> p.getTitulo() != null && p.getTitulo().toLowerCase().contains(texto))
                    .toList();
        }
        model.addAttribute("peliculas", peliculas);
        model.addAttribute("buscar", buscar);
        return "peliculas/lista";
    }
    
    @GetMapping("/{id}")
    public String ficha(@PathVariable Long id, Model modelo) {
        Pelicula pelicula = servicio.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));
        modelo.addAttribute("pelicula", pelicula);
        return "peliculas/ficha";
    }
    
    @GetMapping("/nueva")
    public String formularioNuevo(Model modelo) {
        modelo.addAttribute("pelicula", new Pelicula());
        return "peliculas/formulario";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model modelo) {
        Pelicula pelicula = servicio.buscarPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id));
        modelo.addAttribute("pelicula", pelicula);
        return "peliculas/formulario";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("pelicula") Pelicula pelicula,
                        BindingResult errores,
                        RedirectAttributes flash) {

        if (errores.hasErrors()) {
            return "peliculas/formulario";
        }
        
        servicio.crear(pelicula);
        flash.addFlashAttribute("aviso", "Pelicula añadida");
        return "redirect:/peliculas";
    }

    @PostMapping("/{id}")
    public String editar(@PathVariable Long id,
                        @Valid @ModelAttribute("pelicula") Pelicula pelicula,
                        BindingResult errores,
                        RedirectAttributes flash) {

        if (errores.hasErrors()) {
            return "peliculas/formulario";
        }
        servicio.editar(id, pelicula);
        flash.addFlashAttribute("aviso", "Cambios guardados");
        return "redirect:/peliculas/" + id;
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        servicio.borrar(id);
        flash.addFlashAttribute("aviso", "Película eliminada");
        return "redirect:/peliculas";
    }

}
