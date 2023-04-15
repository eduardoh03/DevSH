package com.devsh.devsh.controller;

import com.devsh.devsh.dto.TokenJWTDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> doLogin(@RequestBody @Valid UserDTO userDTO) {
        TokenJWTDTO token = service.doLogin(userDTO);
        return ResponseEntity.ok().body(token);
    }
}
