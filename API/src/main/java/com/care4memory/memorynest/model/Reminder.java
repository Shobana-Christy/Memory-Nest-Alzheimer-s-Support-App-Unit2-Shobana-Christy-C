package com.care4memory.memorynest.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reminderId;

//    @ManyToOne
//    @JoinColumn(name = "user_role_id", nullable = false)
//    private UserRole userRoleId;
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String notes;

    public Reminder() {
    }

    public Reminder(Integer reminderId, String name, LocalDate date, LocalTime time, String notes) {
        this.reminderId = reminderId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;

    }

    public Integer getReminderId() {
        return reminderId;
    }

    public void setReminderId(Integer reminderId) {
        this.reminderId = reminderId;
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

