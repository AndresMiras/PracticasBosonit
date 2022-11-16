package com.example.examen_JPA_cascada.cliente.service;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteInputDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteOutputDTO;

public interface IClienteService {

    ClienteOutputDTO createCliente(ClienteInputDTO user);
    void deleteCliente(int id);

    ClienteOutputDTO getClienteById(int id);
}
