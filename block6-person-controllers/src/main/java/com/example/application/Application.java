package com.example.application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// El listado de ciudades lo he guardado en el archivo citys.json, se encuentra en la raiz del ejercicio.
@SpringBootApplication
@ComponentScan("com.example.*")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
