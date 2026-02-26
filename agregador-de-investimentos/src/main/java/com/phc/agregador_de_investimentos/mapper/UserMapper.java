package com.phc.agregador_de_investimentos.mapper;


import com.phc.agregador_de_investimentos.dtos.CreateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UpdateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UserResponseDTO;
import com.phc.agregador_de_investimentos.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDTO(User user);

    User toEntity(CreateUserDTO dto);

    // "Se algum campo vier vazio (null), não mexa nele, deixa como estava."
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    // "Pega os dados novos (UpdateUserDTO) e aplica em cima de um User que já existe."
    void updateUserFromDTO(UpdateUserDTO dto, @MappingTarget User user);
}
