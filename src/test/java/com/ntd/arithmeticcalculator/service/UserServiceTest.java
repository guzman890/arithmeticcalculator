package com.ntd.arithmeticcalculator.service;

import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.repository.UserRepository;
import com.ntd.arithmeticcalculator.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guardar un usuario")
    public void testSaveUser() {
        UserEntity user = new UserEntity();
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);

        UserEntity result = userService.saveUser(new UserEntity());

        assertEquals(user, result);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Buscar un usuario por ID")
    public void testFindById() {
        UserEntity user = new UserEntity();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserEntity> result = userService.findById(1L);

        assertEquals(Optional.of(user), result);
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Listar todos los usuarios")
    public void testFindAll() {
        List<UserEntity> users = Arrays.asList(new UserEntity(), new UserEntity());
        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> result = userService.findAll();

        assertEquals(users, result);
        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Actualizar un usuario")
    public void testUpdate() {
        UserEntity existingUser = new UserEntity();
        existingUser.setId(1L);

        UserEntity updatedDetails = new UserEntity();
        updatedDetails.setId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(UserEntity.class))).thenReturn(updatedDetails);

        Optional<UserEntity> result = userService.update(1L, updatedDetails);

        assertEquals(Optional.of(updatedDetails), result);
        verify(userRepository).findById(1L);
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("Eliminar un usuario")
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }
}