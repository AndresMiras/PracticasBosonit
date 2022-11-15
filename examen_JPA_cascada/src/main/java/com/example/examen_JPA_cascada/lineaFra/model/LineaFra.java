package com.example.examen_JPA_cascada.lineaFra.model;

import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "LineaFra")
@NoArgsConstructor
@AllArgsConstructor
public class LineaFra implements Serializable {

    @Id
    @Column(name="IdFra")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFra;

    @Column(name="ProNomb", nullable = false)
    private String proNomb;

    @Column(name="Cantidad")
    private double cantidad;

    @Column(name="precio")
    private double precio;

    // Una factura tiene muchas líneas y muchas líneas tienen una sola factura.

    public LineaOutputDto getLineaOutputDto() {
        return new LineaOutputDto(idFra ,proNomb ,cantidad ,precio);
    }
}
