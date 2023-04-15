package com.devsh.devsh.services;

import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.dto.TokenJWTDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.infra.security.TokenService;
import com.devsh.devsh.repositories.UserRepository;
import com.devsh.devsh.services.exceptions.AuthorizationErrorException;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Transactional
    public UserDTO insert(UserDTO dto) {
        String salGerado = BCrypt.gensalt();
        User entity = new User();
        entity.setLogin(dto.getLogin());
        String passwordHash = BCrypt.hashpw(dto.getPassword(), salGerado);
        entity.setPassword(passwordHash);
        entity = userRepository.save(entity);
        return new UserDTO(entity);
    }

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
                User user = (User) authentication.getPrincipal();
                String tokenJWT = tokenService.gerarToken(user);
                ProfileDTO profileDTO = findProfileByUserId(user.getId());
                return new TokenJWTDTO(tokenJWT, profileDTO.getId());
            } catch (AuthenticationException e) {
                throw new AuthorizationErrorException("Bearer Error");
            }
        } else {
            throw new AuthorizationErrorException("Incorrect Password.");
        }
    }

    private ProfileDTO findProfileByUserId(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
        return new ProfileDTO(entity.getProfile());
    }
}
