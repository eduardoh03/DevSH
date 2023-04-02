package com.devsh.devsh.services;

import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.entities.User;
import com.devsh.devsh.repositories.ProfileRepository;
import com.devsh.devsh.repositories.UserRepository;
import com.devsh.devsh.services.exceptions.DatabaseException;
import com.devsh.devsh.services.exceptions.EmptyResultDataAccessException;
import com.devsh.devsh.services.exceptions.EntityNotFoundException;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public ProfileDTO findById(Long id) {
        Optional<Profile> obj = repository.findById(id);
        Profile entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
        return new ProfileDTO(entity.getId(), entity.getName(), entity.getImgUrl());
    }

    @Transactional
    public ProfileDTO insert(ProfileDTO dto, long userId) {
        Profile entity = new Profile();
        copyDtoToEntity(dto, entity);
        entity.setUser(new User(userId, null, null));
        entity = repository.save(entity);
        return new ProfileDTO(entity.getId(), entity.getName(), entity.getImgUrl());
    }

    @Transactional
    public ProfileDTO update(Long id, ProfileDTO dto) {
        try {
            Profile entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new ProfileDTO(entity.getId(), entity.getName(), entity.getImgUrl());
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id (" + id + ") not found.");
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id (" + id + ") not found");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity Violation");
        }
    }

    private void copyDtoToEntity(ProfileDTO dto, Profile entity) {
        entity.setName(dto.getName() != null ? dto.getName() : entity.getName());
        entity.setImgUrl(dto.getImgUrl() != null ? dto.getImgUrl() : entity.getImgUrl());
    }
}
