package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.controller;

import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import com.example.examen_JPA_cascada.exceptions.CustomError;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Component
@RestController
public class CabeceraFraController {

    @Autowired
    private ICabeceraFraService impCabeceraFraService;

    @PostMapping(value = "/cabecera/insert")
    public ResponseEntity<Object> clienteInsert(CabeceraInputDTO cabeceraInputDTO) {
        impCabeceraFraService.createCabeceraFra(cabeceraInputDTO);
        return ResponseEntity.status(201).body(CustomError.build(201, "Cliente creado, success" ));
    }

    @DeleteMapping(value = "factura/{idFra}")
    public ResponseEntity<Object> deleteFactura(@PathVariable int idFra) {
        impCabeceraFraService.deleteCabeceraFra(idFra);
        return ResponseEntity.ok().body(CustomError.build(200, "Factura eliminada, success!"));
    }

    @GetMapping(value = "/factura")
    public ResponseEntity<Object> getFacturas() {
        return ResponseEntity.ok().body(CustomError.buildWithObject(200, "Facturas encontradas, success!", impCabeceraFraService.getFacturas()));
    }

}
