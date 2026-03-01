package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.model.ContactUs;
import com.care4memory.memorynest.service.ContactService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contactus")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ContactUs addContact(@RequestBody ContactUs contactUs) {
        return contactService.addContact(contactUs);
    }
}
