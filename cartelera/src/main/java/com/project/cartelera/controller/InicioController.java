package com.project.cartelera.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/")
public class InicioController {
    
    @GetMapping
    public String bienvenida(@RequestParam(defaultValue = "usuario") String nombre) {
        return "Bienvenido " + nombre;
    }
    
}
