package com.devsh.devsh.services;

import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
