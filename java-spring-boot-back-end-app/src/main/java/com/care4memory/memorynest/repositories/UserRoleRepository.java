package com.care4memory.memorynest.repositories;

import com.care4memory.memorynest.model.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    java.util.Optional<UserRoleEntity> findByEmail(String email);
}
