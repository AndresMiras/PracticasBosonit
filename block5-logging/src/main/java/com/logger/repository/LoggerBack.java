package com.logger.repository;

import lombok.Data;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class LoggerBack implements ILogger {

    Logger log = LoggerFactory.getLogger(LoggerBack.class);

    @RequestMapping("/")
    public String index() {
        log.trace("Mensaje a nivel de TRACE");
        log.debug("Mensaje a nivel de DEBUG");
        log.info("Mensaje a nivel de INFO");
        log.warn("Mensaje a nivel de WARNING");
        log.error("Mensaje a nivel de ERROR");

        return "Hola! Mira los logs para ver los resultados";
    }

    // usar la dirección http://localhost:8080/lombok para ver el resultado.
}
