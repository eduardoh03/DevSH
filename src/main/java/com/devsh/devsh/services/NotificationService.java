package com.devsh.devsh.services;

import com.devsh.devsh.dto.NotificationDTO;
import com.devsh.devsh.dto.ProfileDTO;
import com.devsh.devsh.entities.Notification;
import com.devsh.devsh.entities.Profile;
import com.devsh.devsh.repositories.NotificationRepository;
import com.devsh.devsh.services.exceptions.DatabaseException;
import com.devsh.devsh.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository repository;
    @Transactional(readOnly = true)
    public Page<NotificationDTO> findAllPaged(PageRequest pageRequest) {
        Page<Notification> list = repository.findAll(pageRequest);
        return list.map(x -> new NotificationDTO(x.getId(), x.getTitle(), x.getMessage(), x.getCreatedAt().toInstant(),
                x.getRead()));
    }

    @Transactional(readOnly = true)
    public NotificationDTO findById(Long id) {
        Optional<Notification> obj = repository.findById(id);
        Notification entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
        return new NotificationDTO(entity.getId(), entity.getTitle(), entity.getMessage(),
                entity.getCreatedAt().toInstant(), entity.getRead());
    }

    @Transactional
    public NotificationDTO insert(NotificationDTO dto) {
        Notification entity = new Notification();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        ProfileDTO profileDTO = new ProfileDTO(entity.getProfile().getId());
        return new NotificationDTO(entity.getId(), entity.getTitle(), entity.getMessage(),
                entity.getCreatedAt().toInstant(), entity.getRead(), profileDTO);
    }

    @Transactional
    public void setRead(Long id) {
        Optional<Notification> obj = repository.findById(id);
        Notification entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not Found."));
        entity.setRead(true);
        try {
            repository.save(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private void copyDtoToEntity(NotificationDTO dto, Notification entity) {
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setMessage(dto.getMessage());
        entity.setRead(dto.getRead());
        Profile profile = new Profile();
        profile.setId(dto.getProfile().getId());
        entity.setProfile(profile);
    }
}
