package com.devsh.devsh;

import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.repositories.ProfileRepository;
import com.devsh.devsh.services.ProfileService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.InjectMocks;
import org.mockito.Mock;

@SpringBootTest
class DevShApplicationTests {

    @Mock
    private ProfileRepository repository;

    @InjectMocks
    private ProfileService service;

    @Test
    public void testFindById() {
        // Arrange
        Long id = 1L;
        Profile profile = new Profile(id, "John Doe", "http://example.com/img.jpg");
        when(repository.findById(id)).thenReturn(Optional.of(profile));

        // Act
        ProfileDTO result = service.findById(id);

        // Assert
        assertEquals(profile.getId(), result.getId());
        assertEquals(profile.getName(), result.getName());
        assertEquals(profile.getImgUrl(), result.getImgUrl());

        verify(repository, times(1)).findById(id);
    }
    @Test
    public void shouldInsertProfile() {
        // Arrange
        ProfileDTO dto = new ProfileDTO(null, "John Doe", "http://imageurl.com");
        Profile profile = new Profile(1L, dto.getName(), dto.getImgUrl());
        when(repository.save(any(Profile.class))).thenReturn(profile);

        // Act
        ProfileDTO result = service.insert(dto);

        // Assert
        assertNotNull(result.getId());
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getImgUrl(), result.getImgUrl());
        verify(repository, times(1)).save(any(Profile.class));
    }
}
