package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.ReminderDTO;
import com.care4memory.memorynest.model.ReminderEntity;
import com.care4memory.memorynest.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;


    public List<ReminderDTO> viewReminders() {
        return reminderRepository.findAll()
                .stream()
                .map(this::convertToDto)   //(re) => convertToDto(re)
                .toList();

    }

    public ReminderDTO getReminderById(Long reminderId) {
        return reminderRepository.findById(reminderId)
                        .map(this::convertToDto)
                        .orElse(null);
    }

    public ReminderDTO addReminder(ReminderDTO reminderDTO) {
        ReminderEntity entity = convertToEntity(reminderDTO);
        ReminderEntity savedEntity = reminderRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public ReminderDTO updateReminder(Long reminderId, ReminderDTO reminderDTO) {
       ReminderEntity entity = convertToEntity(reminderDTO);
       entity.setReminderId(reminderId);
       ReminderEntity updatedEntity = reminderRepository.save(entity);
         return convertToDto(updatedEntity);
    }

    public void deleteReminder(Long reminderId) {
        reminderRepository.deleteById(reminderId);
    }

    private ReminderDTO convertToDto(ReminderEntity reminderEntity) {
        ReminderDTO reminderDTO = new ReminderDTO();
        reminderDTO.setReminderId(reminderEntity.getReminderId());
        reminderDTO.setName(reminderEntity.getName());
        reminderDTO.setDate(reminderEntity.getDate());
        reminderDTO.setTime(reminderEntity.getTime());
        reminderDTO.setNotes(reminderEntity.getNotes());
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
}

