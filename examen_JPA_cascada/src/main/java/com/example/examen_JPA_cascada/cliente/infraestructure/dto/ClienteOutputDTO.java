package com.example.examen_JPA_cascada.cliente.infraestructure.dto;

import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteOutputDTO implements Serializable {

    private int id;
    private String nombre;

    public ClienteOutputDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nombre = cliente.getNombre();
    }

    public Cliente entity() {
        return new Cliente(id, nombre);
    }
}
