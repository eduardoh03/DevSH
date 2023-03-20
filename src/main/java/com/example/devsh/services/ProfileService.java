package com.example.devsh.services;

import com.example.devsh.dto.ProfileDto;
import com.example.devsh.entities.Profile;
import com.example.devsh.repositories.ProfileRepository;

import com.example.devsh.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileDto findById(Long id) {
        Optional<Profile> optionalProfile = profileRepository.findById(id);

        if (optionalProfile.isPresent()) {
            Profile result = optionalProfile.get();
            return new ProfileDto(result);
        } else {
            throw new ResourceNotFoundException("Profile not found with id " + id);
        }
    }
}
