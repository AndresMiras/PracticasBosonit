package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.repository;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CabeceraFraRepository extends JpaRepository<CabeceraFra, Integer> {

}