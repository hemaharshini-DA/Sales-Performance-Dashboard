package com.example.demo.dto;

public class RegionSalesDTO {

    private String region;
    private Double totalSales;

    public RegionSalesDTO(String region, Double totalSales) {
        this.region = region;
        this.totalSales = totalSales;
    }

    public String getRegion() {
        return region;
    }

    public Double getTotalSales() {
        return totalSales;
    }
}