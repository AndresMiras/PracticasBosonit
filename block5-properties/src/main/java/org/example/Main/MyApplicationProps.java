package org.example.Main;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class MyApplicationProps {

    @Value("${greeting}")
    private String greeting;

    @Value("${my.number}")
    private int number;

    // Para la asignación de la variable del sistema deberemos hacer lo siguiente: buscar en windows, "Sistema/Configuración avanzada del sistema/variables de entorno/variables del sistema"
    // Una vez dentro crear una nueva variable con nombre "new.property" y posteriormente reiniciar el equipo para que intelliJ la coja por defecto.
    @Value("${new.property:No tiene valor}")
    private String newProperty;

    @Override
    public String toString() {
        return "El valor de greeting es: ( " + greeting + " ) " +
               "El valor de my.number es: ( " + number + " ) " +
               "El valor de new.property: ( " + newProperty + " ) ";
    }
}
