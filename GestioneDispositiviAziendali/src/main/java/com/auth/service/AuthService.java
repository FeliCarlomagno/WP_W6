package com.auth.service;

import com.auth.payload.LoginDto;
import com.auth.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto); // <-- QUANDO OMETTIAMO LA LOCAZIONE ESSO E' PROTECTED RISPETTO AD ALTRI WORKSPACE MA PUBLIC 
									//NEL WORKSPACE INSERITO
    String register(RegisterDto registerDto);
    
}
