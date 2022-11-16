package com.example.examen_JPA_cascada.cliente.infraestructure.controller;


import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteInputDTO;
import com.example.examen_JPA_cascada.exceptions.CustomError;
import com.example.examen_JPA_cascada.cliente.service.IClienteService;
import com.example.examen_JPA_cascada.lineaFra.service.ILineasFraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Component
@RestController
public class ClienteController {

    @Autowired
    private IClienteService impClienteService;

    @Autowired
    private ILineasFraService impLineasFraService;


    @PostMapping(value = "/cliente/insert")
    public ResponseEntity<Object> clienteInsert(@Valid @RequestBody ClienteInputDTO clienteInputDTO) {
        impClienteService.createCliente(clienteInputDTO);
        return ResponseEntity.status(201).body(CustomError.build(201, "Cliente creado, success"));
    }
}
