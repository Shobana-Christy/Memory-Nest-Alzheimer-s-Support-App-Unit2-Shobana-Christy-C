package com.care4memory.memorynest.service;

import com.care4memory.memorynest.model.Reminder;
import com.care4memory.memorynest.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;


    public List<Reminder> viewReminders() {
        return reminderRepository.findAll();
    }

    public Reminder getReminderById(Integer id){
        return reminderRepository.findById(id).orElse(null);
    }

    public Reminder addReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public Reminder updateReminder(Integer id, Reminder reminder) {
        reminder.setReminderId(id);
        return reminderRepository.save(reminder);
    }

    public void deleteReminder(Integer id) {
        reminderRepository.deleteById(id);
    }
}

