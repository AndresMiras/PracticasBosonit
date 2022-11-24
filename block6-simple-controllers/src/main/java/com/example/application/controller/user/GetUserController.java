package com.example.application.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
public class GetUserController {

    @GetMapping("user/name")
    @ResponseBody
    public String getUser(@RequestParam String name) {
        return "Hola! " + name;
    }

//    @GetMapping("{arg1}")
//    public String getParam2(@PathVariable String arg1) {
//        return String.format("Llamando getParam2 con parametro %s", arg1);
//    }
//
//    @GetMapping("{arg2}")
//    public String getParam1(@PathVariable String arg2) {
//        System.out.println(arg2);
//        return String.format("Llamando getParam1 con parametro %s", arg2);
//    }

    @GetMapping("{arg1}")
    public ResponseEntity<?> getParam1(@PathVariable String arg1) {
        System.out.println(arg1);
        return ResponseEntity.status(201).body(String.format("Llamando getParam1 con parametro %s", arg1));
    }

}
