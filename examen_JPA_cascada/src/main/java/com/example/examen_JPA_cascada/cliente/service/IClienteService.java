package com.example.examen_JPA_cascada.cliente.service;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTOResponse;

public interface IClienteService {

    void createCliente(ClientDTO user);
    void deleteCliente(int id);

    ClientDTOResponse getClienteById(int id);
}
