package com.example.examen_JPA_cascada.lineaFra.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineaOutputDto {
    int id;
    String producto;
    double cantidad;
    double importe;
}
