package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test para obtener todos los usuarios")
    public void testGetAllUsers() {
        // Configurar el mock para devolver una lista esperada
        UserEntity user1 = new UserEntity(); // Configura los atributos según sea necesario
        UserEntity user2 = new UserEntity();
        List<UserEntity> expectedUsers = Arrays.asList(user1, user2);
        when(userService.findAll()).thenReturn(expectedUsers);

        // Llamar al método bajo prueba
        ResponseEntity<List<UserEntity>> response = userController.getAllUsers();

        // Verificar el resultado
        assertEquals(expectedUsers, response.getBody());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("Test para crear un usuario")
    public void testCreateUser() {
        UserEntity user = new UserEntity();
        when(userService.saveUser(any(UserEntity.class))).thenReturn(user);

        ResponseEntity<UserEntity> response = userController.createUser(new UserEntity());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService).saveUser(any(UserEntity.class));
    }

    @Test
    @DisplayName("Test para obtener un usuario por ID")
    public void testGetUserById() {
        UserEntity user = new UserEntity();
        when(userService.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<UserEntity> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService).findById(1L);
    }

    @Test
    @DisplayName("Test para actualizar un usuario")
    public void testUpdateUser() {
        UserEntity user = new UserEntity();
        when(userService.update(eq(1L), any(UserEntity.class))).thenReturn(Optional.of(user));

        ResponseEntity<UserEntity> response = userController.updateUser(1L, new UserEntity());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        verify(userService).update(eq(1L), any(UserEntity.class));
    }

    @Test
    @DisplayName("Test para eliminar un usuario")
    public void testDeleteUser() {
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).deleteUser(1L);
    }
}