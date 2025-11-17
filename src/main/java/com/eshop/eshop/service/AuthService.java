package com.eshop.eshop.service;

import com.eshop.eshop.dto.authDto.LoginDto;
import com.eshop.eshop.dto.authDto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
