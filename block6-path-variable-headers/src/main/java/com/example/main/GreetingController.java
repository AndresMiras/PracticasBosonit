package com.example.main;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // http://localhost:8080/greeting
    // http://localhost:8080/greeting?name=Alejandro
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    // http://localhost:8080/user
    // Para usar el método POST deberemos pasarle cualquier descentiende de OBJ y se intentará parsear, en caso de no poder, saltará una excepción.
    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> usePost(@RequestBody Object obj) {
        String objJson = "";

        try {
            objJson = new Gson().toJson(obj);
            log.info("Sending user Object JSON: " + objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(objJson);
    }

    // Ejemplo GET con path variable...
    // http://localhost:8080/user/myname
    @GetMapping(value= "/user/{id}")
    public String hola(@PathVariable(value="id") String id) {
        return "Mi nombre es " + id;
    }

    // Ejemplo PUT con dos variables... ( reemplaza el elemento o lo crea )
    // http://localhost:8080/post?var1=1&var2=2
    @PutMapping(value = "/post")
    @ResponseBody
    public String greeting(@RequestParam(value = "var1", defaultValue = "olvidaste el primer param") String val1, @RequestParam(value = "var2", defaultValue = "olvidaste el segundo param") String val2) {
        log.info("PUT respuesta: ( val1=" + val1 + ", val2=" + val2 + " )");
        return "PUT respuesta: ( val1=" + val1 + ", val2=" + val2 + " )";
    }
}
