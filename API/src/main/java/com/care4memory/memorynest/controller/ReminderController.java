package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.ReminderDTO;
import com.care4memory.memorynest.dto.UserRoleDTO;
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
        List<ReminderDTO> reminders = this.reminderService.viewReminders();
        return ResponseEntity.ok(reminders); // Return the list of reminders with HTTP 200 OK status
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReminderDTO> getReminderById(@PathVariable Long id) {
        ReminderDTO reminder = this.reminderService.getReminderById(id);
        if (reminder == null) {

            return ResponseEntity.notFound().build(); // Return HTTP 404 Not Found if reminder is not found
        }
        return ResponseEntity.ok(reminder); // Return the reminder with HTTP 200 OK status
    }

    @PostMapping()
    public ResponseEntity<ReminderDTO> addReminder(@RequestBody ReminderDTO reminderDTO) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(1L); // hard code for demo
        userRoleDTO.setEmail("john.doe@gmail.com");
        userRoleDTO.setRole("CareTaker");
        ReminderDTO saved = this.reminderService.addReminder(reminderDTO, userRoleDTO);
        return ResponseEntity.status(201).body(saved); // Return the saved reminder with HTTP 200 OK status
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> updateReminder(@PathVariable Long id, @RequestBody ReminderDTO reminderDTO) {
        // should come from logged in user details
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(1L); // hard code for demo
        userRoleDTO.setEmail("shobichristy07@gmail.com");
        userRoleDTO.setRole("CareTaker");
        ReminderDTO updated = this.reminderService.updateReminder(id, reminderDTO, userRoleDTO);
        return ResponseEntity.ok(updated); // Return the updated reminder with HTTP 200 OK status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        this.reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build(); // Return HTTP 204 No Content status after successful deletion
    }
}