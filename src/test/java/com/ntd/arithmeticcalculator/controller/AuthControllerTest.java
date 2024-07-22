package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.config.JwtUtil;
import com.ntd.arithmeticcalculator.model.dto.LoginDto;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthControllerTest {

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
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("user");
        loginDto.setPassword("password");

        // Configurar comportamiento de los mocks
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtUtil.create(anyString())).thenReturn("token");

        // Llamar al método bajo prueba
        ResponseEntity<Void> response = authController.login(loginDto);

        // Verificar el resultado
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("token", response.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

        // Verificar las interacciones con los mocks
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtUtil).create(anyString());
    }
}