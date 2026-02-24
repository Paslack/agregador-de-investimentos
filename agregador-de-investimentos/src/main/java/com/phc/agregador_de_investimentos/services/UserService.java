package com.phc.agregador_de_investimentos.services;

import com.phc.agregador_de_investimentos.dtos.CreateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UpdateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UserResponseDTO;
import com.phc.agregador_de_investimentos.entities.User;
import com.phc.agregador_de_investimentos.exceptions.ResourceNotFoundException;
import com.phc.agregador_de_investimentos.mapper.UserMapper;
import com.phc.agregador_de_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO createUser(CreateUserDTO dto) {
        User user = userMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public User findUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.toDTO(user)).toList();
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UpdateUserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userMapper.updateUserFromDTO(dto, user);
        User updatedUser = userRepository.save(user);

        return userMapper.toDTO(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }
}
