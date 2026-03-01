package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.EngagementCenterDTO;
import com.care4memory.memorynest.model.EngagementCenterEntity;
import com.care4memory.memorynest.service.EngagementCenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/engagementcenter")
public class EngagementCenterController {

    public EngagementCenterService engagementCenterService;

    public EngagementCenterController(EngagementCenterService engagementCenterService) {
        this.engagementCenterService = engagementCenterService;
    }

    @GetMapping
    public ResponseEntity<List<EngagementCenterDTO>> getAllEngagementActivities() {
        List<EngagementCenterDTO> engagementCenter = this.engagementCenterService.getAllActivities();
        // Return the list of engagement activities with HTTP 200 OK status
        return ResponseEntity.ok(engagementCenter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EngagementCenterDTO> getEngagementActivity(@PathVariable Long id) {
        EngagementCenterDTO engagementCenter = this.engagementCenterService.getActivityById(id);
        if (engagementCenter == null) {
            // Return HTTP 404 Not Found if activity is not found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(engagementCenter); // Return the activity with HTTP 200 OK status
    }

    @PostMapping
    public ResponseEntity<EngagementCenterDTO> addEngagementActivity(@RequestBody EngagementCenterDTO engagementCenterDTO) {
        EngagementCenterDTO saved = this.engagementCenterService.addActivity(engagementCenterDTO);
        return ResponseEntity.status(201).body(saved); // Return the saved activity with HTTP 201 Created status
    }

    @PutMapping("/{id}")
    public ResponseEntity<EngagementCenterDTO> updateEngagementActivity(@PathVariable Long id, @RequestBody EngagementCenterDTO engagementCenterDTO) {
        EngagementCenterDTO updated = this.engagementCenterService.updateActivity(id, engagementCenterDTO);
        if (updated == null) {
            // Return HTTP 404 Not Found if activity is not found for update
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated); // Return the updated activity with HTTP 200 OK status
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEngagementActivity(@PathVariable Long id) {
        this.engagementCenterService.deleteActivity(id);
        return ResponseEntity.noContent().build(); //Return HTTP 204 No Content status after successful deletion
    }
}
