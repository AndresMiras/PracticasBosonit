package com.example.examen_JPA_cascada.cabeceraFra.entity;

import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import com.example.examen_JPA_cascada.lineaFra.entity.LineaFra;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "CabeceraFra")
@NoArgsConstructor
public class CabeceraFra {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cab_id")
    private int id;

    @Column
    private double importeFra;

    // Un cliente tiene varias facturas y una factura tiene una sola entidad.
    // Cuando hagamos un select sobre cabecera factura se ejecutará -> (Select from factura left join cliente)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cli_id", nullable = false)
    private Cliente cliente;

    // Una factura tiene muchas líneas y muchas líneas tienen una sola factura.
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "lineas_fra")
    private List<LineaFra> lineasFra;

    public CabeceraFra(int id, double importeFra, Cliente cliente) {
        this.id = id;
        this.importeFra = importeFra;
        this.cliente = cliente;
    }

    public CabeceraFra(int id, double importeFra, Cliente cliente, List<LineaFra> lineasFra) {
        this.id = id;
        this.importeFra = importeFra;
        this.cliente = cliente;
        this.lineasFra = lineasFra;
    }

    public CabeceraFra(int id, double importeFra, List<LineaFra> lineasFra) {
        this.id = id;
        this.importeFra = importeFra;
        this.lineasFra = lineasFra;
    }

}
