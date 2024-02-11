package com.bilelhami.exam.auth;


import com.bilelhami.exam.models.User;
import com.bilelhami.exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserRepository  userRepository ;
    private final AuthenticationService authenticationService ;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<Optional<User>> find(@PathVariable String email){
        var user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }

}

