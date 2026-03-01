package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.ReminderDTO;
import com.care4memory.memorynest.model.ReminderEntity;
import com.care4memory.memorynest.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {


    private final ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }


    @GetMapping()
    public ResponseEntity<List<ReminderDTO>> getReminders() {
        List<ReminderDTO> reminders = reminderService.viewReminders();
        return ResponseEntity.ok(reminders); // Return the list of reminders with HTTP 200 OK status
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getReminderById(@PathVariable Long id) {
        ReminderDTO reminder = reminderService.getReminderById(id);
        if(reminder == null) {

            return ResponseEntity.notFound().build(); // Return HTTP 404 Not Found if reminder is not found
        }
        return ResponseEntity.ok(reminder); // Return the reminder with HTTP 200 OK status
    }

    @PostMapping()
    public ResponseEntity<ReminderDTO> addReminder(@RequestBody ReminderDTO reminderDTO) {
        ReminderDTO saved = reminderService.addReminder(reminderDTO);
        return ResponseEntity.status(201).body(saved); // Return the saved reminder with HTTP 200 OK status
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> updateReminder(@PathVariable Long id, @RequestBody ReminderDTO reminderDTO) {
         ReminderDTO updated = reminderService.updateReminder(id, reminderDTO);
         return ResponseEntity.ok(updated); // Return the updated reminder with HTTP 200 OK status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build(); // Return HTTP 204 No Content status after successful deletion
    }
}