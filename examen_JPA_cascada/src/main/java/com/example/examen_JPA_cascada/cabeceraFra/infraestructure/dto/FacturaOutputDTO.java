package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTOResponse;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class FacturaOutputDTO implements Serializable {

    int id;

    double importeFra = 0;

    ClientDTOResponse clienteDTOResponse;

    List<LineaOutputDto> lineaOutputDtoList;

}
