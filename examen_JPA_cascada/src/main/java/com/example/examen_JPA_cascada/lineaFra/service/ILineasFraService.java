package com.example.examen_JPA_cascada.lineaFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.FacturaOutputDTO;

import java.util.List;

public interface ILineasFraService {

    List<FacturaOutputDTO> getFacturas();

    void deleteFactura();
}
