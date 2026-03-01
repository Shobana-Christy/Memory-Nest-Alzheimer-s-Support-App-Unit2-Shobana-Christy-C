package com.care4memory.memorynest.service;

import com.care4memory.memorynest.model.ContactUs;
import com.care4memory.memorynest.repositories.ContactRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactService {

    private ContactRepository contactRepository;

        public ContactService(ContactRepository contactRepository) {
            this.contactRepository = contactRepository;
        }

        public ContactUs addContact(ContactUs contactUs) {
            return contactRepository.save(contactUs);
        }
}
