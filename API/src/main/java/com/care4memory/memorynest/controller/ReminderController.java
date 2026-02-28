package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.model.Reminder;
import com.care4memory.memorynest.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    @Autowired
    public ReminderService reminderService;


    @GetMapping()
    public List<Reminder> getReminders() {
        return reminderService.viewReminders();
    }

    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Integer id) {
        return reminderService.getReminderById(id);
    }

    @PostMapping()
    public Reminder addReminder(@RequestBody Reminder reminder) {
        return reminderService.addReminder(reminder);
    }


    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Integer id, @RequestBody Reminder reminder) {
        return reminderService.updateReminder(id, reminder);
    }

    @DeleteMapping("/{id}")
    public void deleteReminder(@PathVariable Integer id) {
        reminderService.deleteReminder(id);
    }
}