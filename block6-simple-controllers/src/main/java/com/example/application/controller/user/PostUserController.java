package com.example.application.controller.user;

import com.example.application.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostUserController {

    @RequestMapping(value = "/user/add", method = RequestMethod.POST, consumes = "application/json")
    public String addPerson(@RequestBody User user){
        user.setAge(user.getAge()+1);
        return user.toString();
    }
}
