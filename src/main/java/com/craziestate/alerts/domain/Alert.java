package com.craziestate.alerts.domain;

import java.time.Instant;
import java.util.UUID;

public class Alert {
    Integer id;
    private UUID alertId;
    private Integer customerId;
    private EstateType estateYype;
    private String countryCode;
    private String regionCode;
    private String cityCode;
    private String criteria;
    private boolean active;
    private Instant creationTime;
    private Instant updateTime;

    public Alert(Integer id, UUID alertId, Integer customerId, EstateType estateYype, String countryCode, String regionCode, String cityCode, String criteria, boolean active, Instant creationTime, Instant updateTime) {
        this.id = id;
        this.alertId = alertId;
        this.customerId = customerId;
        this.estateYype = estateYype;
        this.countryCode = countryCode;
        this.regionCode = regionCode;
        this.cityCode = cityCode;
        this.criteria = criteria;
        this.active = active;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public UUID getAlertId() {
        return alertId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public EstateType getEstateYype() {
        return estateYype;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public String getCriteria() {
        return criteria;
    }

    public boolean isActive() {
        return active;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }
}
