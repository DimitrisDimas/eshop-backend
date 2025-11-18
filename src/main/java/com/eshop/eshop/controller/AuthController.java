package com.eshop.eshop.controller;

import com.eshop.eshop.controller.docs.AuthControllerDocs;
import com.eshop.eshop.dto.authDto.JWTAuthResponse;
import com.eshop.eshop.dto.authDto.LoginDto;
import com.eshop.eshop.dto.authDto.RegisterDto;
import com.eshop.eshop.security.JwtTokenProvider;
import com.eshop.eshop.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/auth")
@Tag( name = "REST APIs for Authentication Resource" )
public class AuthController implements AuthControllerDocs {

    private AuthService authService;
    private JwtTokenProvider jwtTokenProvider;
    private UserDetailsService userDetailsService;

    public AuthController(AuthService authService, JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailsService) {
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }


    @PostMapping(value = {"/login"})
    public ResponseEntity<JWTAuthResponse> login(@Valid @RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setUsername(loginDto.getUsername());
        jwtAuthResponse.setToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDetails> getUserDetails(@RequestHeader("Authorization") String tokenHeader){
        String token = extractTokenFromHeader(tokenHeader);
        if(token != null){
            String username = jwtTokenProvider.getUsername(token);
            log.info(username);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return new ResponseEntity<>(userDetails, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    private String extractTokenFromHeader(String tokenHeader) {
        if(tokenHeader != null && tokenHeader.startsWith("Bearer ")){
            return tokenHeader.substring(7);
        }
        return null;
    }


}
