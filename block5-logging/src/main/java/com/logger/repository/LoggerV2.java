package com.logger.repository;

import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class LoggerV2 implements ILogger {
    org.slf4j.Logger log = LoggerFactory.getLogger(LoggerBack.class);

    @RequestMapping("/v2")
    public String index() {
        log.trace("Mensaje a nivel de TRACE");
        log.debug("Mensaje a nivel de DEBUG");
        log.info("Mensaje a nivel de INFO");
        log.warn("Mensaje a nivel de WARNING");
        log.error("Mensaje a nivel de ERROR");

        return "Hola! Mira los logs para ver los resultados";
    }


    public void filter() {

    }
}
