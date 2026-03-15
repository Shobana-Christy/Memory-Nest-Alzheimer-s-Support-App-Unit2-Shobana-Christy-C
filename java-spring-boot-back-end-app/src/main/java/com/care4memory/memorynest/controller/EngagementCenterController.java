package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.EngagementCenterDTO;
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

}
