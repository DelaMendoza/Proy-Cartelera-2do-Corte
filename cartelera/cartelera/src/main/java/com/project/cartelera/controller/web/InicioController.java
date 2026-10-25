package com.project.cartelera.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.cartelera.service.PeliculaService;

@Controller
public class InicioController {

    private final PeliculaService servicio;

    public InicioController(PeliculaService servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("total", servicio.listar().size());
        return "index";
    }
}
