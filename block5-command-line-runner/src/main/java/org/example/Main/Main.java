package org.example.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;


// La documentación indica que para poder trabajar aplicaciones en consola deberemos implementar la interfaz CommandLineRunner.
@SpringBootApplication
public class Main implements CommandLineRunner, InitializingBean {

	private String runer3Dependency = "Soy la tercera clase";

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	public static Logger log = LoggerFactory.getLogger(Main.class);

	// Para poder usar la interfaz, deberemos de sobreescribir el método que contiene, el método run.
	// Los parámetros podremos pasarlos por consola.
	@Override
	public void run(String... args) throws Exception {
	}

	@Bean
	public CommandLineRunner runner2() {
		return p -> log.info("Hola desde clase secundaria");
	}

	@Bean
	public CommandLineRunner runner3(String saludo) {
		return p -> log.info(saludo);
	}


	// El método PostConstructor siempre se ejecuta al comienzo.
	// Este método siempre se ejecutará antes de que el Bean esté listo para ser usado. No debe recibir argumentos.
	@PostConstruct
	public void runner1() {
		log.info("Hola desde clase inicial");
	}

	// Con la implementación de la interfaz InitializingBean, podemos sobreescribir el método "afterPropertiesSet()"
	// Así podremos dotar a nuestro Bean con información adicional pasando por params en este caso.
	@Override
	public void afterPropertiesSet() throws Exception {
		runner3(runer3Dependency);
	}
}
