package com.example.application.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetUserController {

    @GetMapping("user/name")
    @ResponseBody
    public String getUser(@RequestParam String name) {
        return "Hola! " + name;
    }

}
