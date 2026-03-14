package com.phc.agregador_de_investimentos.dtos;

import com.phc.agregador_de_investimentos.entities.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}
