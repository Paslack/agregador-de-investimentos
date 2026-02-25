package com.phc.agregador_de_investimentos.services;

import com.phc.agregador_de_investimentos.dtos.CreateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UpdateUserDTO;
import com.phc.agregador_de_investimentos.dtos.UserResponseDTO;
import com.phc.agregador_de_investimentos.entities.User;
import com.phc.agregador_de_investimentos.exceptions.ResourceNotFoundException;
import com.phc.agregador_de_investimentos.mapper.UserMapper;
import com.phc.agregador_de_investimentos.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserResponseDTO userResponseDTO;
    private CreateUserDTO createUserDTO;
    private UpdateUserDTO updateUserDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setEmail("user@email.com");
        user.setPassword("password123");
        user.setCreationTimestamp(Instant.now());
        user.setUpdateTimestamp(Instant.now());

        userResponseDTO = new UserResponseDTO(1L, "username", "user@email.com");
        createUserDTO = new CreateUserDTO("username", "user@email.com", "password123");
        updateUserDTO = new UpdateUserDTO("newusername", "newuser@email.com", "newpassword123");
    }

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a user when data is valid")
        void shouldCreateUserWhenDataIsValid() {
            // Arrange
            Mockito.when(userMapper.toEntity(createUserDTO)).thenReturn(user);
            Mockito.when(userRepository.save(user)).thenReturn(user);
            Mockito.when(userMapper.toDTO(user)).thenReturn(userResponseDTO);

            // Act
            UserResponseDTO result = userService.createUser(createUserDTO);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(1L, result.id());
            Assertions.assertEquals("username", result.username());
            Assertions.assertEquals("user@email.com", result.email());

            Mockito.verify(userMapper).toEntity(createUserDTO);
            Mockito.verify(userRepository).save(user);
            Mockito.verify(userMapper).toDTO(user);
        }
    }

    @Nested
    class findUserById {

        @Test
        @DisplayName("Should return user when ID is valid")
        void shouldFindUserByIdWhenIDIsValid() {
            // Arrange
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

            // Act
            User result = userService.findUserById(1L);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(1L, result.getId());
            Mockito.verify(userRepository).findById(1L);
        }

        @Test
        @DisplayName("Should return throw ResourceNotFoundException when user ID not exist")
        void shouldThrowResourceNotFoundExceptionWhenUserIdNotFound() {
            // Arrange
            Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

            // Act e Assert
            Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findUserById(2L));
            Mockito.verify(userRepository).findById(2L);
        }
    }

    @Nested
    class findAllUsers {

        @Test
        @DisplayName("Should return List All Users")
        void shouldReturnAllUserResponseDTO() {
            // Arrange
            Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
            Mockito.when(userMapper.toDTO(user)).thenReturn(userResponseDTO);

            // Act
            List<UserResponseDTO> result = userService.findAllUsers();

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(List.of(userResponseDTO), result);
            Assertions.assertFalse(result.isEmpty());

            Mockito.verify(userRepository).findAll();
            Mockito.verify(userMapper).toDTO(user);
        }

        @Test
        @DisplayName("Should return List Empty")
        void shouldReturnListEmptyResponseDTO() {
            // Arrange
            Mockito.when(userRepository.findAll()).thenReturn(List.of());


            // Act e Assert
            Assertions.assertTrue(userService.findAllUsers().isEmpty());

            Mockito.verify(userRepository).findAll();
            Mockito.verifyNoInteractions(userMapper);
        }
    }

    @Nested
    class updateUser {

        @Test
        @DisplayName("Should update user when ID is valid")
        void shouldUpdateUserWhenIDIsValid() {
            // Arrange
            userResponseDTO = new UserResponseDTO(1L, "newusername", "newuser@email.com");
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            Mockito.doNothing().when(userMapper).updateUserFromDTO(updateUserDTO, user);
            Mockito.when(userRepository.save(user)).thenReturn(user);
            Mockito.when(userMapper.toDTO(user)).thenReturn(userResponseDTO);

            // Act
            UserResponseDTO result = userService.updateUser(1L, updateUserDTO);

            // Assert
            Assertions.assertNotNull(result);
            Assertions.assertEquals(1L, result.id());
            Assertions.assertEquals("newusername", result.username());
            Assertions.assertEquals("newuser@email.com", result.email());

            Mockito.verify(userRepository).findById(1L);
            Mockito.verify(userMapper).updateUserFromDTO(updateUserDTO, user);
            Mockito.verify(userRepository).save(user);
            Mockito.verify(userMapper).toDTO(user);
        }

        @Test
        @DisplayName("Should not update user when ID is invalid")
        void shouldNotUpdateUserWhenIDIsInvalid() {
            // Arrange
            Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

            // Act e Assert
            Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.updateUser(2L, updateUserDTO));

            Mockito.verify(userRepository).findById(2L);
            Mockito.verifyNoMoreInteractions(userRepository);
            Mockito.verifyNoInteractions(userMapper);
        }


    }

    @Nested
    class deleteUser {

        @Test
        @DisplayName("Should delete user with success when ID is valid")
        void shouldDeleteUserWhenIDIsValid() {
            // Arrange
            Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
            Mockito.doNothing().when(userRepository).delete(user);

            // Act
            userService.deleteUser(1L);

            Mockito.verify(userRepository).findById(1L);
            Mockito.verify(userRepository).delete(user);
        }

        @Test
        @DisplayName("Not delete user when ID is invalid")
        void shouldNotDeleteUserWhenIDIsInvalid() {
            // Arrange
            Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());

            // Act e Assert
            Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(2L));

            Mockito.verify(userRepository).findById(2L);
            Mockito.verifyNoMoreInteractions(userRepository);
        }
    }
}