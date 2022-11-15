package com.example.examen_JPA_cascada.lineaFra.infraestructure.dto;

import com.example.examen_JPA_cascada.lineaFra.model.LineaFra;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LineaFraDTO {
    private int idFra;
    private String proNomb;
    private double cantidad;
    private double precio;

    public LineaFra getEntity() {
        return new LineaFra(idFra, proNomb, cantidad, precio);
    }
}

