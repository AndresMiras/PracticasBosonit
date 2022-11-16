package com.example.examen_JPA_cascada.cliente.infraestructure.dto;

import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ClienteInputDTO implements Serializable {

    private int id;

    private String nombre;

}
