package com.loggerApp;

import com.logger.repository.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("com.logger")
public class LoggerApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoggerApp.class, args);
	}

	// Se ha creado un logger básico con su clase loggerBack y un archivo logback.xml de configuración.
	// Está configurado para un máximo de 5 archivos "log" con la capacidad de 5KB.
	// Además registra logs por cada minuto.
	@Autowired
	@Qualifier("loggerBack")
	private ILogger log;

	@Override
	public void run(String... args) throws Exception {
		log.index();
	}
}
