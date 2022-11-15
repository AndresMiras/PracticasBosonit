package com.example.examen_JPA_cascada.cabeceraFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraFraDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.FacturaOutputDTO;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaFraDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICabeceraFraService {
    public void createCabeceraFra(CabeceraFraDTO cabeceraFraDTO);
    public void deleteCabeceraFra(int id);
    public void saveLineas(List<LineaFraDTO> lineaFraDTOs, int id);
    public List<FacturaOutputDTO> getFacturas();
}
