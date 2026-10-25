package com.project.cartelera.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.cartelera.model.Clasificacion;
import com.project.cartelera.model.Genero;
import com.project.cartelera.model.Pelicula;
import com.project.cartelera.repository.PeliculaRepository;

@Configuration
public class DatosPrueba {

    @Bean
    CommandLineRunner datosIniciales(PeliculaRepository repositorio) {
        return args -> {
            repositorio.save(new Pelicula(
                "Dune: Parte Dos",
                "Paul Atreides se une a los Fremen",
                "2h 46m",
                LocalDate.of(2026, 3, 15),
                Genero.AVENTURA,
                Clasificacion.MAYORES_DE_15));
                
            repositorio.save(new Pelicula(
                "Oppenheimer",
                "Historia del creador de la bomba atómica",
                "3h 0m",
                LocalDate.of(2023, 7, 20),
                Genero.DRAMA,
                Clasificacion.MAYORES_DE_15));

            repositorio.save(new Pelicula(
                "Spider-Man: A través del Spider-Verso",
                "Miles Morales viaja por el multiverso",
                "2h 20m",
                LocalDate.of(2023, 6, 1),
                Genero.ACCION,
                Clasificacion.TODO_PUBLICO));

            repositorio.save(new Pelicula(
                "El Conjuro",
                "Investigadores paranormales enfrentan una presencia oscura",
                "1h 52m",
                LocalDate.of(2013, 7, 19),
                Genero.TERROR,
                Clasificacion.MAYORES_DE_18));

            repositorio.save(new Pelicula(
                "Intensamente 2",
                "Riley entra a la adolescencia con nuevas emociones",
                "1h 37m",
                LocalDate.of(2024, 6, 13),
                Genero.COMEDIA,
                Clasificacion.TODO_PUBLICO));
        };
    }
}
