package com.phc.agregador_de_investimentos.mapper;

import com.phc.agregador_de_investimentos.dtos.CreateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UpdateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UserResponseDTO;
import com.phc.agregador_de_investimentos.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-24T17:00:06-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponseDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String username = null;
        String email = null;

        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();

        UserResponseDTO userResponseDTO = new UserResponseDTO( id, username, email );

        return userResponseDTO;
    }

    @Override
    public User toEntity(CreateUserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );

        return user;
    }

    @Override
    public void updateUserFromDTO(UpdateUserDTO dto, User user) {
        if ( dto == null ) {
            return;
        }
    }
}
