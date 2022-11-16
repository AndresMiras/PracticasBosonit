package com.example.examen_JPA_cascada.lineaFra.infraestructure.controller;

import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import com.example.examen_JPA_cascada.exceptions.CustomError;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
public class LineaFraController {

    @Autowired
    ICabeceraFraService impCabeceraFraService;

    @PostMapping(value = "/factura/linea/{idFra}")
    public ResponseEntity<Object> setLineaFactura(@RequestBody LineaInputDTO lineaInputDTO, @PathVariable int idFra) {
        // Se modifica el método para que solo se admita una factura en cada una de las peticiones.
        List<LineaInputDTO> lineaInputDTOS = new ArrayList<>();
        lineaInputDTOS.add(lineaInputDTO);
        impCabeceraFraService.saveLineas(lineaInputDTOS, idFra);
        return ResponseEntity.ok().body(CustomError.build(200, "Líneas añadidas con éxito!"));
    }
}
