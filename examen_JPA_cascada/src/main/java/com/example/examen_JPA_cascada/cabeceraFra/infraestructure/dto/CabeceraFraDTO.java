package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class CabeceraFraDTO implements Serializable {
    private int id;

    private double importeFra;

    private Cliente cliente;

    public CabeceraFra getEntity() {
        return new CabeceraFra(id, importeFra, cliente);
    }
}
