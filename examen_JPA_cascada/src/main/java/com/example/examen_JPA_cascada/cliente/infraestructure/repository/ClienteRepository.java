package com.example.examen_JPA_cascada.cliente.infraestructure.repository;

import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}