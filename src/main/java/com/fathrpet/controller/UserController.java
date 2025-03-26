package com.fathrpet.controller;

import com.fathrpet.model.entity.User;
import com.fathrpet.service.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private final ServiceUser userService;

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {

        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping
    public String ola(){
        return "Ola";
    }

}
