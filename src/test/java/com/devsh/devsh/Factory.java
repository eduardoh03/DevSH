package com.devsh.devsh;

import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.entities.User;

public class Factory {
    public static Profile createProfile(){
        Profile profile = new Profile(1L, "Profile", "http://profile.com/profile.png");
        profile.setUser(createUser());
        return profile;
    }

    public static ProfileDTO createProfileDTO(){
        ProfileDTO profileDTO = new ProfileDTO(1L, "Profile", "http://profile.com/profile.png");
        profileDTO.setUser(createUserDTO());
        return profileDTO;
    }

    public static User createUser(){
        return new User(1L, "user@user.com", "12345");
    }

    public static UserDTO createUserDTO(){
        return new UserDTO(1L, "user@user.com");
    }
}
