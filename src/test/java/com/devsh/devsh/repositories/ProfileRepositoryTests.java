package com.devsh.devsh.repositories;

import com.devsh.devsh.entities.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ProfileRepositoryTests {
    private long existingId;
    private long nonExistingId;

    @Autowired
    private ProfileRepository repository;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 2L;
        nonExistingId = 1000L;
    }

    @Test
    public void getProfileShouldOptionalNotNullWhenIdExists(){
        Optional<Profile> result = repository.findById(existingId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void getProfileShouldOptionalNullWhenIdNotExist(){
        Optional<Profile> result = repository.findById(nonExistingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        repository.deleteById(existingId);
        Optional<Profile> result = repository.findById(existingId);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdNotExist() {
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonExistingId);
        });
    }

    @Test
    public void saveShouldPersistWhenIdIsNull() {
        Profile profile = new Profile(1L, "teste", "teste");
        profile.setId(null);

        profile = repository.save(profile);

        Assertions.assertNotNull(profile.getId());
    }
}
