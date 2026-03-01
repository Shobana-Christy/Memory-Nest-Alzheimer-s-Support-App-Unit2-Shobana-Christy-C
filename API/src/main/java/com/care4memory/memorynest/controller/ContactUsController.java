package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.ContactUsDTO;
import com.care4memory.memorynest.model.ContactUsEntity;
import com.care4memory.memorynest.service.ContactUsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactus")
public class ContactUsController {

    private final ContactUsService contactUsService;

    public ContactUsController(ContactUsService contactUsService) {
        this.contactUsService = contactUsService;
    }

    @PostMapping
    public ResponseEntity<ContactUsDTO> addContact(@RequestBody ContactUsDTO contactUsDTO) {
        ContactUsDTO savedContact = this.contactUsService.addContact(contactUsDTO);
        return ResponseEntity.status(201).body(savedContact); // Return the saved contact with HTTP 201 Created status

    }
}
