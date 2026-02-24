package com.phc.agregador_de_investimentos.dtos;

import com.phc.agregador_de_investimentos.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateUserDTO {
    @NotBlank(message = "Campo não pode estar em branco")
    private String username;

    @Email
    private String email;

    @NotBlank(message = "Campo não pode estar em branco")
    @Size(min = 8, message = "Password deve ter no mínimo 8 caracteres")
    private String password;

    public CreateUserDTO() {
    }

    public CreateUserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public CreateUserDTO(User entity) {
        username = entity.getUsername();
        email = entity.getEmail();
        password = entity.getPassword();
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
