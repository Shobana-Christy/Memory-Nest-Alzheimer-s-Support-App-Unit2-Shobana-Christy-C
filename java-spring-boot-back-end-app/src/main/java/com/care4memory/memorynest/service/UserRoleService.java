package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.UserRoleDTO;
import com.care4memory.memorynest.model.UserRoleEntity;
import com.care4memory.memorynest.repositories.UserRoleRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRoleDTO> getAllUserRoles() {
        return this.userRoleRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    public UserRoleDTO getUserRoleById(Long userRoleId) {
        return this.userRoleRepository.findById(userRoleId)
                .map(this::convertToDto)
                .orElse(null);
    }

    public UserRoleDTO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() != null) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String email = oauth2User.getAttribute("email");
            return getUserRoleByEmail(email);
        }
        return null;
    }

    public UserRoleDTO getUserRoleByEmail(String email) {
        return this.userRoleRepository.findByEmail(email)
                .map(this::convertToDto)
                .orElse(null);
    }

    private UserRoleDTO convertToDto(UserRoleEntity userRoleEntity) {
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO.setUserRoleId(userRoleEntity.getUserRoleId());
        userRoleDTO.setEmail(userRoleEntity.getEmail());
        userRoleDTO.setRole(userRoleEntity.getRole());
        return userRoleDTO;
    }

    private UserRoleEntity convertToEntity(UserRoleDTO userRoleDTO) {
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleId(userRoleDTO.getUserRoleId());
        userRoleEntity.setEmail(userRoleDTO.getEmail());
        userRoleEntity.setRole(userRoleDTO.getRole());
        return userRoleEntity;
    }

}
