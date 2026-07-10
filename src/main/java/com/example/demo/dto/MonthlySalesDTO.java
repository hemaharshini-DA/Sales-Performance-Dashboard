package com.example.demo.dto;

public class MonthlySalesDTO {

    private String month;
    private Double totalSales;

    public MonthlySalesDTO() {
    }

    public MonthlySalesDTO(String month, Double totalSales) {
        this.month = month;
        this.totalSales = totalSales;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(Double totalSales) {
        this.totalSales = totalSales;
    }
}