package com.example.examen_JPA_cascada.cliente.service;

import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteInputDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteOutputDTO;
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
    public ClienteOutputDTO createCliente(ClienteInputDTO clienteInputDTO) {
        Cliente cliente = new Cliente(clienteInputDTO.getId(), clienteInputDTO.getNombre());
        ClienteOutputDTO clienteOutputDTO = new ClienteOutputDTO(clienteRepository.save(cliente));
        log.info("Cliente con id:" + clienteInputDTO.getId() + " Creado con éxito!");
        return clienteOutputDTO;
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
    public ClienteOutputDTO getClienteById(int id) {
        return new ClienteOutputDTO(clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Client not found")));
    }
}
