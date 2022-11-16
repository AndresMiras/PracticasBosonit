package com.example.examen_JPA_cascada.lineaFra.infraestructure.dto;

import com.example.examen_JPA_cascada.lineaFra.entity.LineaFra;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LineaInputDTO implements Serializable {
    private int idFra;
    private String proNomb;
    private double cantidad;
    private double precio;

    public LineaFra getEntity() {
        return new LineaFra(idFra, proNomb, cantidad, precio);
    }

    public LineaInputDTO(String proNomb, double cantidad, double precio) {
        this.proNomb = proNomb;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public LineaOutputDTO getCabeceraOutputDTO() {
        return new LineaOutputDTO(idFra, proNomb, cantidad, precio);
    }
}

