package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.ReminderDTO;
import com.care4memory.memorynest.dto.UserRoleDTO;
import com.care4memory.memorynest.error.UserNotFound;
import com.care4memory.memorynest.service.ReminderService;
import com.care4memory.memorynest.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reminders")
public class ReminderController {

    private final ReminderService reminderService;
    private final UserRoleService userRoleService;

    public ReminderController(ReminderService reminderService, UserRoleService userRoleService) {
        this.reminderService = reminderService;
        this.userRoleService = userRoleService;
    }

    @GetMapping()
    public ResponseEntity<List<ReminderDTO>> getReminders() {
        System.out.println("Received request to get all reminders");
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
    public ResponseEntity<ReminderDTO> addReminder(@RequestBody ReminderDTO reminderDTO) throws UserNotFound {
        UserRoleDTO userRoleDTO = userRoleService.getUserInfo();
        ReminderDTO saved = this.reminderService.addReminder(reminderDTO, userRoleDTO);
        return ResponseEntity.status(201).body(saved); // Return the saved reminder with HTTP 200 OK status
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReminderDTO> updateReminder(@PathVariable Long id, @RequestBody ReminderDTO reminderDTO) throws UserNotFound {
        // should come from logged in user details
        UserRoleDTO userRoleDTO = userRoleService.getUserInfo();
        ReminderDTO updated = this.reminderService.updateReminder(id, reminderDTO, userRoleDTO);
        return ResponseEntity.ok(updated); // Return the updated reminder with HTTP 200 OK status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReminder(@PathVariable Long id) {
        this.reminderService.deleteReminder(id);
        return ResponseEntity.ok().body("Successfully deleted the reminder."); // Return HTTP 204 No Content status after successful deletion
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> handlerUserNotFound(UserNotFound userNotFound) {
        return ResponseEntity.badRequest().body(userNotFound.getMessage());
    }

}