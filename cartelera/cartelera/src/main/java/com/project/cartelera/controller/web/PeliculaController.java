package com.project.cartelera.controller.web;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.cartelera.exception.ReglaNegocioException;
import com.project.cartelera.model.Pelicula;
import com.project.cartelera.service.PeliculaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService servicio;

    public PeliculaController(PeliculaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "") String buscar, Model model) {
        model.addAttribute("peliculas", servicio.buscarPorTitulo(buscar));
        model.addAttribute("buscar", buscar);
        return "peliculas/lista";
    }

    @GetMapping("/{id}")
    public String ficha(@PathVariable Long id, Model model) {
        model.addAttribute("pelicula", servicio.buscarPorId(id));
        return "peliculas/ficha";
    }

    @GetMapping("/nueva")
    public String formularioNuevo(Model model) {
        model.addAttribute("pelicula", new Pelicula());
        return "peliculas/formulario";
    }

    @PostMapping
    public String crear(@Valid @ModelAttribute("pelicula") Pelicula pelicula,
                        BindingResult errores, 
                        RedirectAttributes flash) {
        if (errores.hasErrors()) {
            return "peliculas/formulario"; 
        }

        try {
            servicio.crear(pelicula);
            flash.addFlashAttribute("aviso", "Película «" + pelicula.getTitulo() + "» añadida");
        } catch (ResponseStatusException e) {
            errores.rejectValue("titulo", "error", e.getReason());
            return "peliculas/formulario";
        }
        return "redirect:/peliculas"; 
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("pelicula", servicio.buscarPorId(id));
        return "peliculas/formulario";
    }

    @PostMapping("/{id}")
    public String editar(@PathVariable Long id,
                         @Valid @ModelAttribute("pelicula") Pelicula pelicula,
                         BindingResult errores,
                         RedirectAttributes flash) {

        pelicula.setId(id);

        if (errores.hasErrors()) {
            return "peliculas/formulario";
        }

        try {
            servicio.editar(id, pelicula);
            flash.addFlashAttribute("aviso", "Cambios guardados");
            return "redirect:/peliculas/" + id;
        } catch (ReglaNegocioException e) {
            errores.reject("regla", e.getMessage());
            return "peliculas/formulario";
        }
    }

    @PostMapping("/{id}/borrar")
    public String borrar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            servicio.borrar(id);
            flash.addFlashAttribute("aviso", "Película borrada");
        } catch (Exception e) {
            flash.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/peliculas";
    }

}
