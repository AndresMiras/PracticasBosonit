package com.example.examen_JPA_cascada.cabeceraFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraFraDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.FacturaOutputDTO;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaFraDTO;
import com.example.examen_JPA_cascada.exceptions.EntityNotFoundException;
import com.example.examen_JPA_cascada.lineaFra.model.LineaFra;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.repository.CabeceraFraRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class ImpCabeceraFraService implements ICabeceraFraService {

    @Autowired
    private CabeceraFraRepository cabeceraFraRepository;


    @Override
    public void createCabeceraFra(CabeceraFraDTO cabeceraFraDTO) {
        cabeceraFraRepository.save(cabeceraFraDTO.getEntity());
        log.info("Cabecera de Factura con id:" + cabeceraFraDTO.getId() + " Creado con éxito!");
    }

    @Override
    public void deleteCabeceraFra(int id) {
        if (cabeceraFraRepository.findById(id).isPresent()) {
            cabeceraFraRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("EntityNotFoundException ImpCabeceraFraService can't be deleted... Id:" + id + " Not found.");
        }
    }

    @Override
    public void saveLineas(List<LineaFraDTO> lineaFraDTOs, int id) {

        // Almacena el valor importe del encabezado de las facturas.
        AtomicReference<Double> totalImporteLinea = new AtomicReference<>((double) 0);

        // Guardo una copia local de la cabecera de facturas, si no existe la cabecera arrojo una excepción.
        CabeceraFra cabeceraFra = new CabeceraFra();

        // Guardo una copia del cuerpo de la factura.
        List<LineaFra> bodyFra = new ArrayList<>();

        // Recorro el listado de líneas para así poder almacenar las líneas de factura.
        CabeceraFra cabeceraFraRepo = cabeceraFraRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Error EntityNotFoundException, can't save lineaFra in Cabecera Id:" + id + " Doesn't exists"));
        cabeceraFraRepo.getLineasFra().forEach(lineaFactura -> {

            // También guardo la línea de factura del cuerpo original.
            bodyFra.add(lineaFactura);
        });

        lineaFraDTOs.forEach(lineaFacturaDTO -> {

            // Sumo los importes de las nuevas líneas.
            totalImporteLinea.updateAndGet(v -> v + lineaFacturaDTO.getCantidad() * lineaFacturaDTO.getPrecio());

            // Guardo las nuevas facturas.
            bodyFra.add(lineaFacturaDTO.getEntity());
        });

        // Usaré este objeto para actualizar la factura (cabeceraFra).
        cabeceraFra.setId(id);
        cabeceraFra.setCliente(cabeceraFraRepo.getCliente());
        cabeceraFra.setLineasFra(bodyFra);
        cabeceraFra.setImporteFra(totalImporteLinea.get() + cabeceraFraRepo.getImporteFra());

        // Guardo la factura actualizada.
        cabeceraFraRepository.save(cabeceraFra);
        log.info("CabeceraFraRepository actualizado, se han añadido nuevas líneas! " + cabeceraFra);
    }


    public List<FacturaOutputDTO> getFacturas() {
        List<FacturaOutputDTO> facturaOutputDTOS = new ArrayList<>();

        cabeceraFraRepository.findAll().forEach(cab -> {
            facturaOutputDTOS.add(cab.getFacturaOutputDTO());
        });

        return facturaOutputDTOS;
    }




}
