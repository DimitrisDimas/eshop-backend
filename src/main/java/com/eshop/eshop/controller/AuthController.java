package com.eshop.eshop.controller;

import com.eshop.eshop.dto.authDto.LoginDto;
import com.eshop.eshop.dto.authDto.RegisterDto;
import com.eshop.eshop.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto){
        String msg = authService.login(loginDto);
        return ResponseEntity.ok(msg);
    }



}
