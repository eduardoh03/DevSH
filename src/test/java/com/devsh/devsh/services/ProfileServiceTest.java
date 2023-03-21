package com.devsh.devsh.services;

import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.repositories.ProfileRepository;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.devsh.devsh.dto.ProfileDTO;

class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        // Given
        Long id = 1L;
        Profile profile = new Profile();
        profile.setId(id);
        profile.setName("John Doe");
        when(profileRepository.findById(id)).thenReturn(Optional.of(profile));

        // When
        ProfileDTO result = profileService.findById(id);

        // Then
        assertEquals(profile.getName(), result.getName());
        assertEquals(profile.getId(), result.getId());
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        Long id = 1L;
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(ResourceNotFoundException.class, () -> {
            profileService.findById(id);
        });
    }
}