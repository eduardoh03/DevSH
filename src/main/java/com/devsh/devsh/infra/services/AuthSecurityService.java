package com.devsh.devsh.infra.services;

import com.devsh.devsh.entities.Role;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.infra.projections.UserDetailsProjection;
import com.devsh.devsh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(login);
        if (result.size() == 0) {
            throw new UsernameNotFoundException("Email not found");
        }
        User user = new User();
        user.setLogin(result.get(0).getUsername());
        user.setPassword(result.get(0).getPassword());
        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }
        return user;
    }
}
