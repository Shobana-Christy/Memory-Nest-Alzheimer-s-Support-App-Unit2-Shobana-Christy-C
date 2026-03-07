package com.care4memory.memorynest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EngagementCenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long engagementCenterId;
    private String name;
    private String src;

    public EngagementCenterEntity() {
    }

    public EngagementCenterEntity(Long engagementCenterId, String name, String src) {
        this.engagementCenterId = engagementCenterId;
        this.name = name;
        this.src = src;
    }

    public Long getEngagementCenterId() {
        return engagementCenterId;
    }

    public void setEngagementCenterId(Long engagementCenterId) {
        this.engagementCenterId = engagementCenterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


}
