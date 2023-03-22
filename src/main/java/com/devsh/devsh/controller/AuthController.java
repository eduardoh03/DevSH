package com.devsh.devsh.controller;

import com.devsh.devsh.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid UserDTO userDTO) {
        var token = new UsernamePasswordAuthenticationToken(userDTO.login(), userDTO.password());
        var authentication = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }
}
