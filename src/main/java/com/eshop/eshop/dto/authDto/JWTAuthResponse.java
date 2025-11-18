package com.eshop.eshop.dto.authDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {

    @NotEmpty
    @Size(min = 5, message = "Username should have at least 5 characters")
    @Schema(description = "Login Username")
    private String username;
    private String token;
}