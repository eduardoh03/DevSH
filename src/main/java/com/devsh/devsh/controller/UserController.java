package com.devsh.devsh.controller;

import com.devsh.devsh.dto.TokenJWTDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/login")
public class UserController {
    @Autowired
    private UserService service;
    @PostMapping
    public ResponseEntity<TokenJWTDTO> doLogin(@RequestBody @Valid UserDTO userDTO) {
        TokenJWTDTO token = service.doLogin(userDTO);
        return ResponseEntity.ok().body(token);
    }
}
