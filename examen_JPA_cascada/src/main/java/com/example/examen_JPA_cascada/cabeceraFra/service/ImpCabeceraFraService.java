package com.example.examen_JPA_cascada.cabeceraFra.service;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraInputDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraOutputDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteOutputDTO;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaInputDTO;
import com.example.examen_JPA_cascada.exceptions.EntityNotFoundException;
import com.example.examen_JPA_cascada.lineaFra.entity.LineaFra;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.repository.CabeceraFraRepository;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaOutputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ImpCabeceraFraService implements ICabeceraFraService {

    @Autowired
    private CabeceraFraRepository cabeceraFraRepository;


    @Override
    public CabeceraOutputDTO createCabeceraFra(CabeceraInputDTO cabeceraInputDTO) {

        CabeceraFra cabeceraFra = cabeceraFraRepository.save(cabeceraInputDTO.getEntity());
        CabeceraOutputDTO cabeceraOutputDTO = new CabeceraOutputDTO();

        // Creo la cabecera de salida son su importe.
        cabeceraOutputDTO.setId(cabeceraFra.getId());
        cabeceraOutputDTO.setImporteFra(cabeceraFra.getImporteFra());

        // El cliente
        cabeceraOutputDTO.setClienteOutputDTO(new ClienteOutputDTO(
                cabeceraFra.getCliente().getId(),
                cabeceraFra.getCliente().getNombre()
        ));

        // la colección de líneas.
        cabeceraOutputDTO.setLineaOutputDTOList(cabeceraFra.getLineasFra().stream().map(lineaFra -> {

            // Creo un nuevo array de líneas y lo guardo.
            LineaOutputDTO lineaOutputDto = new LineaOutputDTO();
            lineaOutputDto.setIdFra(lineaFra.getIdFra());
            lineaOutputDto.setProNomb(lineaFra.getProNomb());
            lineaOutputDto.setCantidad(lineaFra.getCantidad());
            lineaOutputDto.setPrecio(lineaFra.getPrecio());

            return lineaOutputDto;
        }).collect(Collectors.toList()));

        log.info("Cabecera de Factura con id:" + cabeceraOutputDTO.getId() + " Creado con éxito!");
        return cabeceraOutputDTO;
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
    public void saveLineas(List<LineaInputDTO> lineaInputDTOS, int id) {

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

        lineaInputDTOS.forEach(lineaFacturaDTO -> {

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


    public List<CabeceraOutputDTO> getFacturas() {
        List<CabeceraOutputDTO> cabeceraOutputDTOS = new ArrayList<>();

        cabeceraFraRepository.findAll().forEach(cab -> {

            CabeceraOutputDTO cabeceraOutputDTO = new CabeceraOutputDTO();

            // Creo la cabecera de salida son su importe.
            cabeceraOutputDTO.setId(cab.getId());
            cabeceraOutputDTO.setImporteFra(cab.getImporteFra());

            // El cliente
            cabeceraOutputDTO.setClienteOutputDTO(new ClienteOutputDTO(
                    cab.getCliente().getId(),
                    cab.getCliente().getNombre()
            ));

            // la colección de líneas.
            cabeceraOutputDTO.setLineaOutputDTOList(cab.getLineasFra().stream().map(lineaFra -> {

                // Creo un nuevo array de líneas y lo guardo.
                LineaOutputDTO lineaOutputDto = new LineaOutputDTO();
                lineaOutputDto.setIdFra(lineaFra.getIdFra());
                lineaOutputDto.setProNomb(lineaFra.getProNomb());
                lineaOutputDto.setCantidad(lineaFra.getCantidad());
                lineaOutputDto.setPrecio(lineaFra.getPrecio());

                return lineaOutputDto;
            }).collect(Collectors.toList()));

            cabeceraOutputDTOS.add(cabeceraOutputDTO);
        });

        return cabeceraOutputDTOS;
    }
}
