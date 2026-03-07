package com.care4memory.memorynest.dto;

public class EngagementCenterDTO {

    private Long engagementCenterId;
    private String name;
    private String src;

    public EngagementCenterDTO() {
    }

    public EngagementCenterDTO(Long engagementCenterId, String name, String src) {
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
