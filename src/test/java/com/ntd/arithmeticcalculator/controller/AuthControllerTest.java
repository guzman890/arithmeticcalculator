package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.config.JwtUtil;
import com.ntd.arithmeticcalculator.model.dto.UserDto;
import com.ntd.arithmeticcalculator.model.request.LoginRequest;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test para el login exitoso")
    public void testLoginSuccess() {
        // Preparar datos de entrada
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("user");
        loginRequest.setPassword("password");

        // Configurar comportamiento de los mocks
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtUtil.create(anyString())).thenReturn("token");
        UserEntity user= new UserEntity();
        user.setUsername("user") ;
        user.setCredits(200D);
        user.setPassword("asdasdsadasddsfgfwe");
        user.setId(1L);
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(user));
        // Llamar al método bajo prueba
        ResponseEntity<UserDto> response = authController.login(loginRequest);

        // Verificar el resultado
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("token", response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        // Verificar las interacciones con los mocks
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil).create(anyString());
    }
}