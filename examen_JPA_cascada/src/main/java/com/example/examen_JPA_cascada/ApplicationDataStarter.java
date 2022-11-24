package com.example.examen_JPA_cascada;

import com.example.examen_JPA_cascada.cabeceraFra.entity.CabeceraFra;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraInputDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraOutputDTO;
import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.repository.CabeceraFraRepository;
import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import com.example.examen_JPA_cascada.cliente.entity.Cliente;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteInputDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClienteOutputDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.repository.ClienteRepository;
import com.example.examen_JPA_cascada.cliente.service.IClienteService;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaInputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ApplicationDataStarter implements CommandLineRunner {

    @Autowired
    private IClienteService impClienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CabeceraFraRepository cabeceraFraRepository;

    @Autowired
    private ICabeceraFraService impCabeceraFraService;


    @Override
    public void run(String... args) throws Exception {

        // Cargo los datos para tenerlos disponibles en la aplicación.
        ClienteOutputDTO clienteOutputDTO = impClienteService.createCliente(new ClienteInputDTO(1, "Juan"));
        impClienteService.createCliente(new ClienteInputDTO(2, "Emilio"));

        // Creo la cabecera de la factura.
        Cliente cliente = new Cliente(clienteOutputDTO.getId(), clienteOutputDTO.getNombre());
        CabeceraInputDTO cabeceraInputDTO = new CabeceraInputDTO(cliente);

        // Creo las líneas para la factura.
        List<LineaInputDTO> lineaInputDTOS = new ArrayList<>();
        lineaInputDTOS.add(new LineaInputDTO("Guisantes tiernos", 2, 10));
        lineaInputDTOS.add(new LineaInputDTO("Arroz", 2, 20));

        // Recreo una entrada de DTO input con la cabecera y sus outputDTO.
        cabeceraInputDTO.setLineaInputDtoList(lineaInputDTOS);

        // Los paso a la identidad.
        CabeceraFra cabeceraFra = cabeceraInputDTO.getEntity();

        // Visión global de las facturas.
        log.info("Esta es mi cabecera: " + cabeceraFra.toString());

        // Creo la factura, con su cabecera y sus líneas.
        cabeceraFraRepository.save(cabeceraFra);

        // ----------------------------------------------- Ejemplo con cálculo de la factura en la cabecera ( suma las líneas ), añadiendo las líneas una por una. --------------------------------

        // Creación de un cliente.
        ClienteOutputDTO clienteOutputDTO2 = impClienteService.createCliente(new ClienteInputDTO(2, "Alberto"));

        // Creo una cabecera.
        CabeceraOutputDTO cabeceraOutputDTO = impCabeceraFraService.createCabeceraFra(new CabeceraInputDTO(clienteOutputDTO2.entity()));

        // Añado las líneas actualizando la cabecera.
        List<LineaInputDTO> lineaFraDTOs = new ArrayList<>();
        lineaFraDTOs.add(new LineaInputDTO( "Tomates", 2, 10));
        lineaFraDTOs.add(new LineaInputDTO( "Arroz", 2, 20));

        impCabeceraFraService.saveLineas(lineaFraDTOs, cabeceraOutputDTO.getId());

        // ------------------------------------------------ Probar con el postman.

        // http://localhost:8080/factura
        // http://localhost:8080/cliente/insert
        // http://localhost:8080/factura/linea/3
        // http://localhost:8080/factura/3

    }
}
