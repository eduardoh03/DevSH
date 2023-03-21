package com.devsh.devsh.services;

import com.devsh.devsh.dto.ProfileDto;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.repositories.ProfileRepository;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;

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
