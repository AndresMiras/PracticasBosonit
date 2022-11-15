package com.example.examen_JPA_cascada;

import com.example.examen_JPA_cascada.cabeceraFra.infraestructure.dto.CabeceraFraDTO;
import com.example.examen_JPA_cascada.cabeceraFra.service.ICabeceraFraService;
import com.example.examen_JPA_cascada.cliente.infraestructure.dto.ClientDTO;
import com.example.examen_JPA_cascada.cliente.infraestructure.repository.ClienteRepository;
import com.example.examen_JPA_cascada.cliente.service.IClienteService;
import com.example.examen_JPA_cascada.lineaFra.infraestructure.dto.LineaFraDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationDataStarter implements CommandLineRunner {

    @Autowired
    private IClienteService impClienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ICabeceraFraService impCabeceraFraService;


    @Override
    public void run(String... args) throws Exception {
        // Cargo los datos para tenerlos disponibles en la aplicación.
        impClienteService.createCliente(new ClientDTO(1, "Juan"));
        impClienteService.createCliente(new ClientDTO(2, "Emilio"));

        // Cabeceras de las facturas.
        impCabeceraFraService.createCabeceraFra(new CabeceraFraDTO(1,0, clienteRepository.findById(1).orElseThrow()));

        // Añado las líneas actualizando la cabecera.
        List<LineaFraDTO> lineaFraDTOs = new ArrayList<>();
        lineaFraDTOs.add(new LineaFraDTO(1, "Guisantes tiernos", 2, 10));
        lineaFraDTOs.add(new LineaFraDTO(1, "Arroz", 2, 20));

        impCabeceraFraService.saveLineas(lineaFraDTOs, 3);
    }
}
