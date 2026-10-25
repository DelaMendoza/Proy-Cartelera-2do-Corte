package com.project.cartelera.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.project.cartelera.exception.NoEncontradoException;

@ControllerAdvice
public class ManejadorDeErrores {

    // Un id que no existe deja de ser una página blanca de Spring. 
    @ExceptionHandler(NoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String noEncontrado(NoEncontradoException e, Model modelo) {
        modelo.addAttribute("mensaje", e.getMessage());
        return "error/404";
    }
}
