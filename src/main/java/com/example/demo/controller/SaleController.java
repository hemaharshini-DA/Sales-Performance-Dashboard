package com.example.demo.controller;
import com.example.demo.dto.RegionSalesDTO;
import com.example.demo.dto.MonthlySalesDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Sale;
import com.example.demo.service.SaleService;

@RestController
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping("/hello")
    public String hello() {
        return "Sales Dashboard Running";
    }

    @PostMapping("/save")
    public Sale saveSale(@RequestBody Sale sale) {
        return saleService.saveSale(sale);
    }
    @GetMapping("/sales")
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }
    @GetMapping("/total-sales")
    public Double getTotalSales() {
        return saleService.getTotalSales();
    }
    @GetMapping("/total-profit")
    public Double getTotalProfit() {
        return saleService.getTotalProfit();
    }
    @GetMapping("/sales-count")
    public Long getSalesCount() {
        return saleService.getSalesCount();
    }
    @GetMapping("/sales-by-region")
    public List<RegionSalesDTO> getSalesByRegion() {
        return saleService.getSalesByRegion();
    }
    @DeleteMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {

        saleService.deleteSale(id);

        return "Sale Deleted Successfully";

    }
    @PutMapping("/update/{id}")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale sale) {

        return saleService.updateSale(id, sale);

    }
    @GetMapping("/top-product")
    public Sale getTopSellingProduct() {
        return saleService.getTopSellingProduct();
    }
    @GetMapping("/monthly-sales")
    public List<MonthlySalesDTO> getMonthlySales() {
        return saleService.getMonthlySales();
    }
}