package com.devsh.devsh.controller;

import com.devsh.devsh.dto.TokenJWTDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
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

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> doLogin(@RequestBody @Valid UserDTO userDTO) {
        var token = new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword());
        try {
            var authentication = manager.authenticate(token);
            String tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());
            return ResponseEntity.ok(new TokenJWTDTO("Bearer ", tokenJWT, ((User) authentication.getPrincipal()).getId()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
