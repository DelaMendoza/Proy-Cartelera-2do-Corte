package com.project.cartelera.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cartelera.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
    
    List<Pelicula> findByTituloContainingIgnoreCase(String titulo);

    boolean existsByTituloIgnoreCase(String titulo);

    Optional<Pelicula> findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);
    
}
