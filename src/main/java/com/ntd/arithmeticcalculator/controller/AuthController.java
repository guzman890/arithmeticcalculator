package com.ntd.arithmeticcalculator.controller;

import com.ntd.arithmeticcalculator.config.JwtUtil;
import com.ntd.arithmeticcalculator.model.dto.UserDto;
import com.ntd.arithmeticcalculator.model.request.LoginRequest;
import com.ntd.arithmeticcalculator.model.entity.UserEntity;
import com.ntd.arithmeticcalculator.service.UserService;
import com.ntd.arithmeticcalculator.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());

        String jwt = this.jwtUtil.create(loginRequest.getUsername());
        UserEntity user = userService.findByUsername(loginRequest.getUsername()).get();
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).body(UserMapper.toDto(user));
    }
}