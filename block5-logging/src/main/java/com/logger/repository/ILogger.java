package com.logger.repository;
import org.springframework.stereotype.Component;

// Creo una interfaz con los contratos necesarios que van a implementar obligatoriamente las clases Logger...
@Component
public interface ILogger {
    public String index();
}
