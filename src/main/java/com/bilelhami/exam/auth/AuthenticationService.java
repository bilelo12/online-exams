package com.bilelhami.exam.auth;


import com.bilelhami.exam.config.JwtService;
import com.bilelhami.exam.models.Role;
import com.bilelhami.exam.models.User;
import com.bilelhami.exam.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager ;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode( request.getPassword()))
                .role(Role.Student)
                .enabled(true)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse.builder().token(jwtToken).build();


    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail()+"   "+request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        System.out.println(user.isPresent());

        var jwt = jwtService.generateToken(user.get());
        return AuthenticationResponse.builder().token(jwt).build();

    }
}
