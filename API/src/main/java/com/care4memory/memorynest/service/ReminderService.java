package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.ReminderDTO;
import com.care4memory.memorynest.dto.UserRoleDTO;
import com.care4memory.memorynest.model.ReminderEntity;
import com.care4memory.memorynest.model.UserRoleEntity;
import com.care4memory.memorynest.repositories.ReminderRepository;
import com.care4memory.memorynest.repositories.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    private ReminderRepository reminderRepository;
    private final UserRoleRepository userRoleRepository;

    public ReminderService(ReminderRepository reminderRepository, UserRoleRepository userRoleRepository) {
        this.reminderRepository = reminderRepository;
        this.userRoleRepository = userRoleRepository;
    }

    public List<ReminderDTO> viewReminders() {
        return this.reminderRepository.findAll()
                .stream()
                .map(this::convertToDto)   //(re) => convertToDto(re)
                .toList();
    }

    public ReminderDTO getReminderById(Long reminderId) {
        return this.reminderRepository.findById(reminderId)
                .map(this::convertToDto)
                .orElse(null);
    }

    public ReminderDTO addReminder(ReminderDTO reminderDTO, UserRoleDTO userRoleDTO) {
        ReminderEntity entity = convertToEntity(reminderDTO);
        entity.setUserRole(convertToUserRoleEntity(userRoleDTO));
        ReminderEntity savedEntity = this.reminderRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public ReminderDTO updateReminder(Long reminderId, ReminderDTO reminderDTO, UserRoleDTO userRoleDTO) {
        ReminderEntity entity = convertToEntity(reminderDTO);
        entity.setReminderId(reminderId);
        entity.setUserRole(convertToUserRoleEntity(userRoleDTO));
        ReminderEntity updatedEntity = this.reminderRepository.save(entity);
        return convertToDto(updatedEntity);
    }

    public void deleteReminder(Long reminderId) {
        this.reminderRepository.deleteById(reminderId);
    }

    private ReminderDTO convertToDto(ReminderEntity reminderEntity) {
        ReminderDTO reminderDTO = new ReminderDTO();
        reminderDTO.setReminderId(reminderEntity.getReminderId());
        reminderDTO.setName(reminderEntity.getName());
        reminderDTO.setDate(reminderEntity.getDate());
        reminderDTO.setTime(reminderEntity.getTime());
        reminderDTO.setNotes(reminderEntity.getNotes());
        reminderDTO.setUserRoleId(reminderEntity.getUserRole().getUserRoleId());
        return reminderDTO;
    }

    private ReminderEntity convertToEntity(ReminderDTO reminderDTO) {
        ReminderEntity reminderEntity = new ReminderEntity();
        reminderEntity.setReminderId(reminderDTO.getReminderId());
        reminderEntity.setName(reminderDTO.getName());
        reminderEntity.setDate(reminderDTO.getDate());
        reminderEntity.setTime(reminderDTO.getTime());
        reminderEntity.setNotes(reminderDTO.getNotes());

        return reminderEntity;
    }

    private UserRoleEntity convertToUserRoleEntity(UserRoleDTO userRoleDTO) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleId(userRoleDTO.getUserRoleId());
        userRoleEntity.setRole(userRoleDTO.getRole());
        userRoleEntity.setEmail(userRoleDTO.getEmail());
        return userRoleEntity;
    }
}

