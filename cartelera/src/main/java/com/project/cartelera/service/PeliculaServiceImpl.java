package com.project.cartelera.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.cartelera.model.Clasificacion;
import com.project.cartelera.model.Genero;
import com.project.cartelera.model.Pelicula;

@Service
public class PeliculaServiceImpl implements PeliculaService{

    private final Map<Long, Pelicula> peliculas = new LinkedHashMap<>();
    private final AtomicLong siguienteId = new AtomicLong(1);

    public PeliculaServiceImpl() {
        crear(new Pelicula(null, "Resident Evil", "Alice despierta sin memoria en la mansión Spencer y junto a un comando de Umbrella desciende a La Colmena para detener la propagación del Virus-T, que ha convertido al personal científico en zombis.", "1h 40m", LocalDate.of(2002, 3, 15), Genero.TERROR, Clasificacion.MAYORES_DE_18));
        crear(new Pelicula(null, "Looney Tunes", "Bugs Bunny, el Pato Lucas y Porky viven enredos cómicos para salvar su mundo de una invasión alienígena con mucho humor y persecuciones.", "1h 31m", LocalDate.of(2024, 6, 11), Genero.COMEDIA, Clasificacion.TODO_PUBLICO));
        crear(new Pelicula(null, "Evil Dead Burn", "Una familia se muda a una cabaña aislada donde el Necronomicón despierta a una entidad demoníaca que posee a los vivos y los enfrenta entre sí.", "1h 36m", LocalDate.of(2026, 7, 24), Genero.TERROR, Clasificacion.MAYORES_DE_18));
        crear(new Pelicula(null, "Paw Patrol", "Ryder y los cachorros viajan a Ciudad Aventura para detener al alcalde Humdinger y salvar a los ciudadanos con trabajo en equipo.", "1h 26m", LocalDate.of(2021, 8, 20), Genero.AVENTURA, Clasificacion.TODO_PUBLICO));
        crear(new Pelicula(null, "Obsession", "Un ejecutivo aparentemente feliz se ve atrapado en un peligroso juego de manipulación cuando una joven colega desarrolla una obsesión enfermiza con él.", "1h 38m", LocalDate.of(2015, 4, 24), Genero.SUSPENSO, Clasificacion.MAYORES_DE_15));
    }

    @Override
    public List<Pelicula> listar() {
        return List.copyOf(peliculas.values());
    }

    @Override
    public Pelicula crear(Pelicula datos) {
        validarObligatorios(datos);
        Long id = siguienteId.getAndIncrement();
        Pelicula peliculaCreada = new Pelicula(id, datos.getTitulo().trim(), datos.getDescripcion(), datos.getDuracion(), datos.getFechaEstreno(), datos.getGenero(), datos.getClasificacion());
        peliculas.put(id, peliculaCreada);
        return peliculaCreada;
    }

    @Override
    public void borrar(Long id) {
        if (!peliculas.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id);
        }
        peliculas.remove(id);
    }

    @Override
    public Optional<Pelicula> buscarPorId(Long id) {
        return Optional.ofNullable(peliculas.get(id));
    }

    @Override
    public Pelicula editar(Long id, Pelicula datos) {
        Pelicula existe = peliculas.get(id);
        if (existe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada con id: " + id);
        }

        validarObligatorios(datos);
        existe.setTitulo(datos.getTitulo().trim());
        existe.setDescripcion(datos.getDescripcion());
        existe.setDuracion(datos.getDuracion());
        existe.setFechaEstreno(datos.getFechaEstreno());
        existe.setGenero(datos.getGenero());
        existe.setClasificacion(datos.getClasificacion());
        return existe;
    }
    
    private void validarObligatorios(Pelicula datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de película obligatorios");
        }
        if (datos.getTitulo() == null || datos.getTitulo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título es obligatorio");
        }
        if (datos.getDescripcion() == null || datos.getDescripcion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción es obligatoria");
        }
        if (datos.getDuracion() == null || datos.getDuracion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La duración es obligatoria");
        }
        if (datos.getGenero() == null || datos.getClasificacion() == null || datos.getFechaEstreno() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Género, clasificación y fechaEstreno son obligatorios");
        }
    }
}
