package com.example.demo.service;
import java.time.Month;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MonthlySalesDTO;
import com.example.demo.dto.RegionSalesDTO;
import com.example.demo.entity.Sale;
import com.example.demo.repository.SaleRepository;

@Service
public class SaleService {
	public List<RegionSalesDTO> getSalesByRegion() {

	    Map<String, Double> regionMap = new HashMap<>();

	    for (Sale sale : saleRepository.findAll()) {

	        regionMap.put(
	                sale.getRegion(),
	                regionMap.getOrDefault(sale.getRegion(), 0.0)
	                        + sale.getSales());
	    }

	    List<RegionSalesDTO> result = new ArrayList<>();

	    for (String region : regionMap.keySet()) {
	        result.add(
	                new RegionSalesDTO(
	                        region,
	                        regionMap.get(region)));
	    }

	    return result;
	}

    @Autowired
    private SaleRepository saleRepository;

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }
    public Double getTotalSales() {
        return saleRepository.findAll()
                .stream()
                .mapToDouble(Sale::getSales)
                .sum();
    }
    public Double getTotalProfit() {
        return saleRepository.findAll()
                .stream()
                .mapToDouble(Sale::getProfit)
                .sum();
    }
    public Long getSalesCount() {
        return saleRepository.count();
    }
    public void deleteSale(Long id) {

        saleRepository.deleteById(id);

    }
    public Sale updateSale(Long id, Sale updatedSale) {

        Sale existingSale = saleRepository.findById(id).orElse(null);

        if (existingSale != null) {

            existingSale.setProductName(updatedSale.getProductName());
            existingSale.setSales(updatedSale.getSales());
            existingSale.setProfit(updatedSale.getProfit());
            existingSale.setRegion(updatedSale.getRegion());

            return saleRepository.save(existingSale);
        }

        return null;
    }

    // New method goes here (outside updateSale)
    public Sale getTopSellingProduct() {
        return saleRepository.getTopSellingProduct();
    }
    public List<MonthlySalesDTO> getMonthlySales() {

        Map<Integer, Double> monthMap = new TreeMap<>();

        for (Sale sale : saleRepository.findAll()) {

            if (sale.getSaleDate() != null) {

                int month = sale.getSaleDate().getMonthValue();

                monthMap.put(
                        month,
                        monthMap.getOrDefault(month, 0.0) + sale.getSales());

            }

        }

        List<MonthlySalesDTO> result = new ArrayList<>();

        for (Integer month : monthMap.keySet()) {

            result.add(
                    new MonthlySalesDTO(
                            Month.of(month).name(),
                            monthMap.get(month)));

        }

        return result;
    }
}