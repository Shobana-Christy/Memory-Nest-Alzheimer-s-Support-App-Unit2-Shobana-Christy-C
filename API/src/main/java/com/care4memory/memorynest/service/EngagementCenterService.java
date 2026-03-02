package com.care4memory.memorynest.service;

import com.care4memory.memorynest.dto.EngagementCenterDTO;
import com.care4memory.memorynest.model.EngagementCenterEntity;
import com.care4memory.memorynest.repositories.EngagementCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngagementCenterService {


    private final EngagementCenterRepository engagementCenterRepository;

    public EngagementCenterService(EngagementCenterRepository engagementCenterRepository) {
        this.engagementCenterRepository = engagementCenterRepository;
    }

    public List<EngagementCenterDTO> getAllActivities() {
        return this.engagementCenterRepository.findAll()
                .stream()
                .map(this::convertToDto)//(e) => convertToDto(e) call back method for each element in the stream and collect the results into a list
                .toList();
    }

    public EngagementCenterDTO getActivityById(Long id) {
        return this.engagementCenterRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);

    }

    public EngagementCenterDTO addActivity(EngagementCenterDTO engagementCenterDTO) {
        EngagementCenterEntity entity = convertToEntity(engagementCenterDTO);
        EngagementCenterEntity savedEntity = this.engagementCenterRepository.save(entity);
        return convertToDto(savedEntity);
    }

    public EngagementCenterDTO updateActivity(Long id, EngagementCenterDTO engagementCenterDTO) {
        EngagementCenterEntity entity = convertToEntity(engagementCenterDTO);
        entity.setEngagementCenterId(id);
        EngagementCenterEntity updatedEntity = this.engagementCenterRepository.save(entity);
        return convertToDto(updatedEntity);
    }

    public void deleteActivity(Long id) {
        this.engagementCenterRepository.deleteById(id);
    }

    private EngagementCenterDTO convertToDto(EngagementCenterEntity entity) {
        EngagementCenterDTO engCenterDTO = new EngagementCenterDTO();
        engCenterDTO.setEngagementCenterId(entity.getEngagementCenterId());
        engCenterDTO.setName(entity.getName());
        engCenterDTO.setSrc(entity.getSrc());
        return engCenterDTO;
    }

    private EngagementCenterEntity convertToEntity(EngagementCenterDTO engagementCenterDTO) {
        EngagementCenterEntity entity = new EngagementCenterEntity();
        entity.setEngagementCenterId(engagementCenterDTO.getEngagementCenterId());
        entity.setName(engagementCenterDTO.getName());
        entity.setSrc(engagementCenterDTO.getSrc());
        return entity;
    }

}
