package com.demoapp.main;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

	// Default configurations:
	// Abrir Maven y limpiar el Target para crear el package con el .jar nuevo.
	// go -> cd block5-profiles/target

	// Using JVM

	// With local properties.
	// java -jar -Dspring.profiles.active=local block5-profiles-0.0.1-SNAPSHOT.jar

	// With integration properties.
	// java -jar -Dspring.profiles.active=integration block5-profiles-0.0.1-SNAPSHOT.jar

	// With local production.
	// java -jar -Dspring.profiles.active=production block5-profiles-0.0.1-SNAPSHOT.jar

	// Exit on CTRL+C

	// También deberíamos poder probar nuestra applicación con las opciones de configuración.
	// Habilitando en las opciones de "build and run" la opción de lanzamiento "add VM Options"
	// Ahora podremos pasar la configuración: -Dspring.profiles.active=production (Esta configuración con -D aplastará la variable de entorno si existe y la volverá a declarar).

	// Tener en cuenta que de tener un perfil application.properties, por defecto siempre va a cargar este perfil si existe.
	// Además también cargará los perfiles que le indiquemos.

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// or can chargue directly with this example.
		// new SpringApplicationBuilder(Application.class).profiles("local").run(args);
		new SpringApplicationBuilder(Application.class).run(args);
	}
}


