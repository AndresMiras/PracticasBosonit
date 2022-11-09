package com.example.block7crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// The following application has implemented a crud of People with an h2 database.
//
// To handle it, I have also implemented @Valid as a practice.

@SpringBootApplication
public class Block7CrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Block7CrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
