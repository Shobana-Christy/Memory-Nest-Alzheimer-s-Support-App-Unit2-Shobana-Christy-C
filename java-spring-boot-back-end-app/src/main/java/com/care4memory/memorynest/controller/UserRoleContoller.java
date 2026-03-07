package com.care4memory.memorynest.controller;

import com.care4memory.memorynest.dto.UserRoleDTO;
import com.care4memory.memorynest.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user-roles")
public class UserRoleContoller {

    private  final UserRoleService userRoleService;

    public UserRoleContoller(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<List<UserRoleDTO>> getAllUserRoles() {
        List<UserRoleDTO> userRoles = this.userRoleService.getAllUserRoles();
        // Return the list of user roles with HTTP 200 OK status
        return ResponseEntity.ok(userRoles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleDTO> getUserRoleById(@PathVariable Long id){
        UserRoleDTO userRole = this.userRoleService.getUserRoleById(id);
        if(userRole == null){
            // Return HTTP 404 Not Found if user role is not found
            return ResponseEntity.notFound().build();
        }
        // Return the user role with HTTP 200 OK status
        return ResponseEntity.ok(userRole);
    }

}
