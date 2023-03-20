package com.example.devsh.controller;

import com.example.devsh.dto.ProfileDto;
import com.example.devsh.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @GetMapping(value = "/{id}")
    public ProfileDto findById(@PathVariable Long id) {
        return service.findById(id);
    }
}
