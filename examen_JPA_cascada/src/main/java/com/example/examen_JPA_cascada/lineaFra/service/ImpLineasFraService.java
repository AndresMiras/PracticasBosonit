package com.example.examen_JPA_cascada.lineaFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ImpLineasFraService implements ILineasFraService {

    @Autowired
    private ILineasFraService iLineasFraService;

    @Autowired
    private ICabeceraFraService iCabeceraFraService;

    @Override
    public void deleteFactura() {

    }
}
