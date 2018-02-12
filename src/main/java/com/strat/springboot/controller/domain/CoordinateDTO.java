package com.strat.springboot.controller.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by Administrator on 2017/8/23.
 */
public class CoordinateDTO {
    
    private String zipCode;
    
    private Double lat;
    
    private Double lng;
    
    public CoordinateDTO() {
    }
    
    public CoordinateDTO(String zipCode, Double lat, Double lng) {
        this.zipCode = zipCode;
        this.lat = lat;
        this.lng = lng;
    }
    
    public Double getLat() {
        return lat;
    }
    
    public void setLat(Double lat) {
        this.lat = lat;
    }
    
    public Double getLng() {
        return lng;
    }
    
    public void setLng(Double lng) {
        this.lng = lng;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
