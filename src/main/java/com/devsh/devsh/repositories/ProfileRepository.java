package com.devsh.devsh.repositories;

import com.devsh.devsh.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}