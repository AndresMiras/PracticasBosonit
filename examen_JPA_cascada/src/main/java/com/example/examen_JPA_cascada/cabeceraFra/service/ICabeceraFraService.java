package com.example.examen_JPA_cascada.cabeceraFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraInputDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraOutputDTO;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaInputDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICabeceraFraService {
    public CabeceraOutputDTO createCabeceraFra(CabeceraInputDTO cabeceraInputDTO);
    public void deleteCabeceraFra(int id);
    public void saveLineas(List<LineaInputDTO> lineaInputDTOS, int id);
    public List<CabeceraOutputDTO> getFacturas();
}
