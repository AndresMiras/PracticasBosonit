package com.example.examen_JPA_cascada.lineaFra.entity;

import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class LineaFra implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idFra;

    @Column(nullable = false)
    private String proNomb;

    @Column
    private double cantidad;

    @Column
    private double precio;

    // Una factura tiene muchas líneas y muchas líneas tienen una sola factura.
}
