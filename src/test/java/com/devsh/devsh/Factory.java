package com.devsh.devsh;

import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.entities.User;

public class Factory {
    public static Profile createProfile(){
        Profile profile = new Profile(1L, "Profile", "http://profile.com/profile.png");
        profile.setUser(createUser());
        return profile;
    }

    public static User createUser(){
        User user = new User(1L, "user@user.com", "12345");
        return user;
    }
}
