package com.example.examen_JPA_cascada.lineaFra.infraestructure.repository;

import com.example.examen_JPA_cascada.lineaFra.entity.LineaFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineasFraRepository extends JpaRepository<LineaFra, Integer> {
}