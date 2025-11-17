package com.eshop.eshop.controller.docs;

import com.eshop.eshop.dto.authDto.JWTAuthResponse;
import com.eshop.eshop.dto.authDto.LoginDto;
import com.eshop.eshop.dto.authDto.RegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthControllerDocs {

    @Operation(
            summary = "Register a new user",
            description = "Create a new user account with the provided registration data",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "User successfully registered",
                            responseCode = "201",
                            content = @Content(schema = @Schema(implementation = String.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<String> register(RegisterDto registerDto);

    @Operation(
            summary = "Login user",
            description = "Authenticate a user and return a JWT token",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "Successfully authenticated",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = JWTAuthResponse.class))
                    ),
                    @ApiResponse(description = "Bad Request / Invalid credentials", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<JWTAuthResponse> login(LoginDto loginDto);
}
