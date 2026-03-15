package com.care4memory.memorynest.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user_role")
public class UserRoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userRoleId;

    private String role;
    private String email;

    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private List<ReminderEntity> reminders;


    public Long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Long userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ReminderEntity> getReminders() {
        return reminders;
    }

    public void setReminders(List<ReminderEntity> reminders) {
        this.reminders = reminders;
    }
}
