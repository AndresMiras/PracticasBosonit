package org.example.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// En esta aplicación básica trataremos de pasar valores desde el fichero application.properties.
@SpringBootApplication
public class Main implements CommandLineRunner {
	public static Logger log = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Autowired
	private MyApplicationProps myProps;

	@Override
	public void run(String... args) throws Exception {
		log.info(myProps.toString());
	}
}
