package com.example.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Para este ejercicio se pide crear un método POST para mandar un paquete tipo JSON con un objeto tipo user al cual sumaremos uno a la edad y lo leeremos
// para poder trabajar con el.
// También haremos un método GET para pasar el nombre de un usuario, en mi caso lo he pasado por params.

@Slf4j
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
