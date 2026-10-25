package com.project.cartelera.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.project.cartelera.exception.NoEncontradoException;
import com.project.cartelera.exception.ReglaNegocioException;
import com.project.cartelera.model.Pelicula;
import com.project.cartelera.repository.PeliculaRepository;

import jakarta.transaction.Transactional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    private final PeliculaRepository peliculas;

    public PeliculaServiceImpl(PeliculaRepository peliculaRepository) {
        this.peliculas = peliculaRepository;
    }

    @Override
    public List<Pelicula> listar() {
        return peliculas.findAll();
    }

    @Override
    public List<Pelicula> buscarPorTitulo(String texto) {
        if (texto == null || texto.isBlank()) {
            return listar();
        }
        String aguja = texto.toLowerCase();
        return peliculas.findByTituloContainingIgnoreCase(aguja);
    }

    @Override
    @Transactional 
    public Pelicula crear(Pelicula pelicula) {
        validarObligatorios(pelicula);
        Pelicula creada = peliculas.save(pelicula);
        return creada;
    }

    @Override
    @Transactional 
    public void borrar(Long id){
        if (!peliculas.existsById(id)) {
            throw new NoEncontradoException("Película no encontrada con id: " + id);
        }
        peliculas.deleteById(id);
    }

    @Override
    public Pelicula buscarPorId(Long id) {
        return peliculas.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Película no encontrada con id: " + id));
    }
    
    @Override 
    @Transactional 
    public Pelicula editar(Long id, Pelicula datos){

        validarObligatorios(datos);
        Pelicula existente = buscarPorId(id);
        
        existente.setTitulo(datos.getTitulo().trim());
        existente.setDescripcion(datos.getDescripcion());
        existente.setDuracion(datos.getDuracion());
        existente.setFechaEstreno(datos.getFechaEstreno());
        existente.setGenero(datos.getGenero());
        existente.setClasificacion(datos.getClasificacion());
        return existente;
    }

    private void validarObligatorios(Pelicula datos) {
        if (datos == null) {
            throw new ReglaNegocioException("Datos de película obligatorios");
        }
        if (datos.getTitulo() == null || datos.getTitulo().isBlank()) {
            throw new ReglaNegocioException("El título es obligatorio");
        }
        if (datos.getGenero() == null || datos.getClasificacion() == null || datos.getFechaEstreno() == null) {
            throw new ReglaNegocioException("Género, clasificación y fechaEstreno son obligatorios");
        }
    }
}
