package com.example.examen_JPA_cascada.lineaFra.infraestructure.dto;

import com.example.examen_JPA_cascada.lineaFra.entity.LineaFra;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LineaOutputDTO implements Serializable {

    private int idFra;
    private String proNomb;
    private double cantidad;
    private double precio;

    @Override
    public String toString() {
        return "LineaOutputDTO{" +
                "idFra=" + idFra +
                ", proNomb='" + proNomb + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}
