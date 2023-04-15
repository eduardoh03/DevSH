package com.devsh.devsh.services;
import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.repositories.UserRepository;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
        return new UserDTO(entity.getId(), entity.getLogin());
    }

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
