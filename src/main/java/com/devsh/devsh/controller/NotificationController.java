package com.devsh.devsh.controller;

import com.devsh.devsh.dto.NotificationDTO;
import com.devsh.devsh.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {
    @Autowired
    private NotificationService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable Long id) {
        //NotificationDTO dto = service.findById(id);
        return ResponseEntity.ok().body(new NotificationDTO());
    }
    @PostMapping
    public ResponseEntity<NotificationDTO> insert(@RequestBody NotificationDTO dto) {
        //dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
