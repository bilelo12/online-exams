package com.bilelhami.exam.controllers;

import com.bilelhami.exam.models.Question;
import com.bilelhami.exam.models.User;
import com.bilelhami.exam.services.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService ;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser = userService.create(user);
        return ResponseEntity.status(CREATED).body(createdUser);
    }
}
