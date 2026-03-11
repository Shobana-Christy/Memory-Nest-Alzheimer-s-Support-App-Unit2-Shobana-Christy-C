package com.care4memory.memorynest.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReminderDTO {

    private Long id;
    private Long userRoleId;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String notes;


    public ReminderDTO() {
    }

    public ReminderDTO(Long id, Long userRoleId, String name, LocalDate date, LocalTime time, String notes) {
        this.id = id;
        this.userRoleId = userRoleId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserRoleId() {
        return userRoleId;
    }
    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
