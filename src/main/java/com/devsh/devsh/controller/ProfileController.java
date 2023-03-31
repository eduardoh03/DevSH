package com.devsh.devsh.controller;

import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.dto.UserDTO;
import com.devsh.devsh.services.ProfileService;
import com.devsh.devsh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {
    @Autowired
    private ProfileService service;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfileDTO> findById(@PathVariable Long id) {
        ProfileDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> insert(@RequestBody ProfileDTO dto) {
        UserDTO userDTO = dto.getUser();
        userDTO = userService.insert(userDTO);
        dto = service.insert(dto, userDTO.getId());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable Long id, @RequestBody ProfileDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
