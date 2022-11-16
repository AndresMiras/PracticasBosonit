package com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto;

import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteOutputDTO;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CabeceraOutputDTO implements Serializable {

    int id;

    double importeFra;

    ClienteOutputDTO clienteOutputDTO; // rev

    List<LineaOutputDTO> lineaOutputDTOList;

}


// Los DTOs deben estar siempre limpios.

//    public CabeceraFra getEntity() {
//        return new CabeceraFra(id, importeFra, clienteOutputDTO.getEntity(), lineaOutputDTOList.stream().map(lineaOutputDTO -> {
//            LineaFra lineaFra = new LineaFra(lineaOutputDTO.getIdFra(), lineaOutputDTO.getProNomb(), lineaOutputDTO.getCantidad(), lineaOutputDTO.getPrecio());
//            return lineaFra;
//        }).collect(Collectors.toList()));
//    }
