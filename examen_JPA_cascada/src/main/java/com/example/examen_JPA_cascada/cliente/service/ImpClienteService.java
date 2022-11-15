package com.example.examen_JPA_cascada.cliente.service;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTOResponse;
import com.example.examen_JPA_cascada.exceptions.EntityNotFoundException;
import com.example.examen_JPA_cascada.cliente.infraestructure.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImpClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void createCliente(ClientDTO clientDTO) {
        clienteRepository.save(clientDTO.getEntity());
        log.info("Cliente con id:" + clientDTO.getId() + " Creado con éxito!");
    }

    @Override
    public void deleteCliente(int id) {
        if (clienteRepository.findById(id).isPresent()) {
            clienteRepository.deleteById(id);
            log.info("Cliente con id:" + id + " Borrado con éxito!");
        } else {
            throw new EntityNotFoundException("Cliente con Id: " + id + " No encontrado!");
        }
    }

    @Override
    public ClientDTOResponse getClienteById(int id) {
        return new ClientDTOResponse(clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found")));
    }
}
