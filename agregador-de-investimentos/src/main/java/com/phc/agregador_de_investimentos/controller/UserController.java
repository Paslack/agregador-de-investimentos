package com.phc.agregador_de_investimentos.controller;

import com.phc.agregador_de_investimentos.dtos.*;
import com.phc.agregador_de_investimentos.entities.User;
import com.phc.agregador_de_investimentos.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid CreateUserDTO dto) {
        UserResponseDTO user = userService.createUser(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.id())
                .toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findUserById(id));
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody  @Valid UpdateUserDTO dto) {
       return ResponseEntity.ok().body(userService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/accounts")
    public ResponseEntity<AccountResponseDTO> createAccount(@PathVariable Long userId, @RequestBody @Valid CreateAccountDTO dto) {
        return ResponseEntity.ok().body(userService.createAccount(userId, dto));
    }

    @GetMapping("/{userId}/accounts")
    public ResponseEntity<List<AccountResponseDTO>> listAccounts(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.listAccounts(userId));
    }

}
