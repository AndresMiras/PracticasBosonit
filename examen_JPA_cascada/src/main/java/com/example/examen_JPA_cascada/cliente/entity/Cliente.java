package com.example.examen_JPA_cascada.cliente.entity;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
public class Cliente implements Serializable {

    @Id
    @Column(name="cli_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="nombre", nullable = false, length = 100)
    private String nombre;

    // Un cliente tiene varias facturas y una factura tiene una sola entidad.

    public ClientDTOResponse getClientDTOResponse() {
        return new ClientDTOResponse(id, nombre);
    }
}
