package com.care4memory.memorynest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EngagementCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer engagementCenterId;

    private String name;
    private String src;

    public EngagementCenter() {
    }

    public EngagementCenter(Integer engagementCenterId, String name, String src) {
        this.engagementCenterId = engagementCenterId;
        this.name = name;
        this.src = src;
    }

    public Integer getEngagementCenterId() {
        return engagementCenterId;
    }

    public void setEngagementCenterId(Integer engagementCenterId) {
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
