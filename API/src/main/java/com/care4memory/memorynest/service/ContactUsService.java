package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.ContactUsDTO;
import com.care4memory.memorynest.model.ContactUsEntity;
import com.care4memory.memorynest.repositories.ContactUsRepository;
import org.springframework.stereotype.Service;


@Service
public class ContactUsService {

    private ContactUsRepository contactUsRepository;

        public ContactUsService(ContactUsRepository contactUsRepository) {
            this.contactUsRepository = contactUsRepository;
        }

        public ContactUsDTO addContact(ContactUsDTO contactUsDTO) {
            ContactUsEntity contactUsEntity = convertToEntity(contactUsDTO);
            ContactUsEntity savedEntity = this.contactUsRepository.save(contactUsEntity);
            return convertToDto(savedEntity);
        }

        private ContactUsDTO convertToDto(ContactUsEntity contactUsEntity) {
            ContactUsDTO contactUsDTO = new ContactUsDTO();
            contactUsDTO.setContactId(contactUsEntity.getContactId());
            contactUsDTO.setFirstName(contactUsEntity.getFirstName());
            contactUsDTO.setLastName(contactUsEntity.getLastName());
            contactUsDTO.setEmail(contactUsEntity.getEmail());
            contactUsDTO.setMessage(contactUsEntity.getMessage());
            return contactUsDTO;
        }

        private ContactUsEntity convertToEntity(ContactUsDTO contactUsDTO) {
            ContactUsEntity contactUsEntity = new ContactUsEntity();
            contactUsEntity.setContactId(contactUsDTO.getContactId());
            contactUsEntity.setFirstName(contactUsDTO.getFirstName());
            contactUsEntity.setLastName(contactUsDTO.getLastName());
            contactUsEntity.setEmail(contactUsDTO.getEmail());
            contactUsEntity.setMessage(contactUsDTO.getMessage());
            return contactUsEntity;
        }
}
