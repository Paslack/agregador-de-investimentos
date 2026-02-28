package com.phc.agregador_de_investimentos.services;

import com.phc.agregador_de_investimentos.dtos.*;
import com.phc.agregador_de_investimentos.entities.Account;
import com.phc.agregador_de_investimentos.entities.BillingAddress;
import com.phc.agregador_de_investimentos.entities.User;
import com.phc.agregador_de_investimentos.exceptions.ResourceNotFoundException;
import com.phc.agregador_de_investimentos.mapper.AccountMapper;
import com.phc.agregador_de_investimentos.mapper.UserMapper;
import com.phc.agregador_de_investimentos.repository.AccountRepository;
import com.phc.agregador_de_investimentos.repository.BillingAddressRepository;
import com.phc.agregador_de_investimentos.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountRepository accountRepository;
    private final BillingAddressRepository billingAddressRepository;
    private final AccountMapper accountMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper, AccountRepository accountRepository, BillingAddressRepository billingAddressRepository, AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountRepository = accountRepository;
        this.billingAddressRepository = billingAddressRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public UserResponseDTO createUser(CreateUserDTO dto) {
        User user = userMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return userMapper.toDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.toDTO(user);
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

    @Transactional
    public AccountResponseDTO createAccount(Long userId, CreateAccountDTO createAccountDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Account account = accountMapper.toEntityAccount(createAccountDto);
        account.setUser(user);
        account = accountRepository.save(account);

        BillingAddress billingAddress = new BillingAddress(
                null,
                createAccountDto.street(),
                createAccountDto.number(),
                account
        );

        billingAddressRepository.save(billingAddress);
        return accountMapper.toAccountResponseDTO(account);
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> listAccounts(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if(user.getAccounts().isEmpty()) {
            throw new ResourceNotFoundException("Nenhuma conta econtrada");
        }
        return user.getAccounts().stream()
                .map(account -> accountMapper.toAccountResponseDTO(account)).toList();
    }
}
