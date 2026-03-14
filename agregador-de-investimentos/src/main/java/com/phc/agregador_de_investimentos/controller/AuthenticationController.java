package com.phc.agregador_de_investimentos.controller;

import com.phc.agregador_de_investimentos.dtos.AuthenticationDTO;
import com.phc.agregador_de_investimentos.dtos.LoginResponseDTO;
import com.phc.agregador_de_investimentos.dtos.RegisterDTO;
import com.phc.agregador_de_investimentos.entities.User;
import com.phc.agregador_de_investimentos.repository.UserRepository;
import com.phc.agregador_de_investimentos.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO dto) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO dto) {
        if(this.userRepository.findByEmail(dto.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        User newUser = new User(dto.email(), encryptedPassword, dto.role());
        this.userRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
