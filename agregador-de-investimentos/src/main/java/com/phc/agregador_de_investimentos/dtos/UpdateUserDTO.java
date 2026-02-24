package com.phc.agregador_de_investimentos.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateUserDTO {

    @Size(min = 3)
    @Pattern(regexp = "^(?!\\s*$).+", message = "Username cannot be blank")
    private String username;

    @Email
    private String email;

    @Size(min = 3)
    private String password;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
