package com.care4memory.memorynest.repositories;

import com.care4memory.memorynest.model.ReminderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository <ReminderEntity, Long> {
}
