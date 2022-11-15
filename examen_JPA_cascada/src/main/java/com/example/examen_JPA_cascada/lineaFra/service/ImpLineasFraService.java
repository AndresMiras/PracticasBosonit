package com.example.examen_JPA_cascada.lineaFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.FacturaOutputDTO;
import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ImpLineasFraService implements ILineasFraService {

    @Autowired
    private ILineasFraService iLineasFraService;

    @Autowired
    private ICabeceraFraService iCabeceraFraService;


    @Override
    public List<FacturaOutputDTO> getFacturas() {
        List<FacturaOutputDTO> facturaOutputDTOS= new ArrayList<>();
//        facturaOutputDTOS.add(iCabeceraFraService.);
        return null;
    }

    @Override
    public void deleteFactura() {

    }
}
