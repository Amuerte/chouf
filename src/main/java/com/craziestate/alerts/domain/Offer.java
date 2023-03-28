package com.craziestate.alerts.domain;


import java.time.Instant;
import java.util.UUID;

public class Offer {

    Integer id;
    private UUID offerId;
    private Integer sellerId;
    private EstateType estateType;
    private String countryCode;
    private String regionCode;
    private String cityCode;
    private Integer price;
    private Integer floorArea;
    private Integer landArea;
    private Integer rooms;
    private Integer bedrooms;
    private Integer bathrooms;
    private Boolean stairs;
    private Boolean parking;
    private Boolean cellar;
    private Integer version;
    private Instant creationTime;
    private Instant updateTime;

    public Offer(Integer id, UUID offerId, Integer sellerId, EstateType estateType, String countryCode, String regionCode, String cityCode, Integer price, Integer floorArea, Integer landArea, Integer rooms, Integer bedrooms, Integer bathrooms, Boolean stairs, Boolean parking, Boolean cellar, Integer version, Instant creationTime, Instant updateTime) {
        this.id = id;
        this.offerId = offerId;
        this.sellerId = sellerId;
        this.estateType = estateType;
        this.countryCode = countryCode;
        this.regionCode = regionCode;
        this.cityCode = cityCode;
        this.price = price;
        this.floorArea = floorArea;
        this.landArea = landArea;
        this.rooms = rooms;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.stairs = stairs;
        this.parking = parking;
        this.cellar = cellar;
        this.version = version;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
    }

    public Integer getId() {
        return id;
    }

    public UUID getOfferId() {
        return offerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public EstateType getEstateType() {
        return estateType;
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

    public Integer getPrice() {
        return price;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public Integer getLandArea() {
        return landArea;
    }

    public Integer getRooms() {
        return rooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public Integer getBathrooms() {
        return bathrooms;
    }

    public Boolean getStairs() {
        return stairs;
    }

    public Boolean getParking() {
        return parking;
    }

    public Boolean getCellar() {
        return cellar;
    }

    public Integer getVersion() {
        return version;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }
}
