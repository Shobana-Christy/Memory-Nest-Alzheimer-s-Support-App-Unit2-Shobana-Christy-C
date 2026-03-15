package com.care4memory.memorynest.model;

import jakarta.persistence.*;


@Entity
@Table(name = "contact_us")
public class ContactUsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String firstName;
    private String lastName;
    private String email;
    private String message;

    public ContactUsEntity() {
    }

    public ContactUsEntity(Long contactId, String firstName, String lastName, String email, String message) {
        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.message = message;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
