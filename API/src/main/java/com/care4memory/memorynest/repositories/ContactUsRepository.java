package com.care4memory.memorynest.repositories;

import com.care4memory.memorynest.model.ContactUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactUs, Integer> {
}
