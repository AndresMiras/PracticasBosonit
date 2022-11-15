package com.example.examen_JPA_cascada.cabeceraFra.entity;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.FacturaOutputDTO;
import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDto;
import com.example.examen_JPA_cascada.lineaFra.model.LineaFra;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "CabeceraFra")
@NoArgsConstructor
@AllArgsConstructor
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
    private List<LineaFra> lineasFra;

    public CabeceraFra(int id, double importeFra, Cliente cliente) {
        this.id = id;
        this.importeFra = importeFra;
        this.cliente = cliente;
    }

    public CabeceraFra(int id, double importeFra, List<LineaFra> lineasFra) {
        this.id = id;
        this.importeFra = importeFra;
        this.lineasFra = lineasFra;
    }

    public FacturaOutputDTO getFacturaOutputDTO() {

        return new FacturaOutputDTO(id, importeFra, cliente.getClientDTOResponse(), lineasFra.stream().map(lineaFra -> lineaFra.getLineaOutputDto()).collect(Collectors.toList()));
    }

}
