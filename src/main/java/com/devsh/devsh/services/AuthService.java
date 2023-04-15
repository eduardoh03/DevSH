package com.devsh.devsh.services;

import com.devsh.devsh.dto.TokenJWTDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.infra.security.TokenService;
import com.devsh.devsh.repositories.UserRepository;
import com.devsh.devsh.services.exceptions.AuthorizationErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    public TokenJWTDTO doLogin(UserDTO userDTO) {
        UserDetails db_user = userRepository.findByLogin(userDTO.getLogin());
        boolean isAuthenticated = false;

        if (db_user != null) {
            isAuthenticated = BCrypt.checkpw(userDTO.getPassword(), db_user.getPassword());
        } else {
            throw new AuthorizationErrorException("User not found!");
        }

        if (isAuthenticated) {
            var token = new UsernamePasswordAuthenticationToken(userDTO.getLogin(), userDTO.getPassword());
            try {
                var authentication = manager.authenticate(token);
                String tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

                return new TokenJWTDTO(tokenJWT, ((User) authentication.getPrincipal()).getId());
            } catch (AuthenticationException e) {
                throw new AuthorizationErrorException("Bearer Error");
            }
        } else {
            throw new AuthorizationErrorException("Incorrect Password.");
        }
    }
}
