package org.example.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


// La documentación indica que para poder trabajar aplicaciones en consola deberemos implementar la interfaz CommandLineRunner.
@SpringBootApplication
public class Main implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public static Logger log = LoggerFactory.getLogger(Main.class);

	// Para poder usar la interfaz, deberemos de sobreescribir el método que contiene, el método run.
	@Override
	public void run(String... args) throws Exception {
		runner3("Estamos dentro del metodo Final");
		runner1();
		runner2();
	}


	// El método PostConstructor siempre se ejecuta al comienzo de la ejecución.
	// Este método siempre se ejecutará antes de que el Bean esté listo para ser usado. No debe recibir argumentos.
	@PostConstruct
	public void runner1() {
		log.info("Hola desde clase inicial");
	}

	public void runner2() {
		log.info("Hola desde clase secundaria");
	}

	public void runner3(String saludo) {

		log.info(saludo);
	}
}
