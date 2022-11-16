package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaInputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class CabeceraInputDTO implements Serializable {
    private int id;

    private double importeFra = 0;

    private Cliente cliente;

    List<LineaInputDTO> lineaInputDtoList;

    public CabeceraFra getEntity() {
        return new CabeceraFra(id, importeFra, cliente, lineaInputDtoList.stream().map(lineaInputDto -> lineaInputDto.getEntity()).collect(Collectors.toList()));
    }

    public CabeceraInputDTO( Cliente cliente) {
        this.id = 0;
        this.cliente = cliente;
        lineaInputDtoList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CabeceraInputDTO{" +
                "id=" + id +
                ", importeFra=" + importeFra +
                ", cliente=" + cliente +
                ", lineaOutputDtoList=" + lineaInputDtoList.toString() +
                '}';
    }
}
