package com.example.demo.controller;

import com.example.demo.entity.Sale;
import com.example.demo.repository.SaleRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
public class ExcelController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportExcel() {

        try {

            List<Sale> sales = saleRepository.findAll();

            XSSFWorkbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Sales Report");

            Row header = sheet.createRow(0);

            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Product");
            header.createCell(2).setCellValue("Sales");
            header.createCell(3).setCellValue("Profit");
            header.createCell(4).setCellValue("Region");
            header.createCell(5).setCellValue("Sale Date");

            int rowNum = 1;

            for (Sale sale : sales) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(sale.getId());
                row.createCell(1).setCellValue(sale.getProductName());
                row.createCell(2).setCellValue(sale.getSales());
                row.createCell(3).setCellValue(sale.getProfit());
                row.createCell(4).setCellValue(sale.getRegion());

                if (sale.getSaleDate() != null) {
                    row.createCell(5).setCellValue(sale.getSaleDate().toString());
                } else {
                    row.createCell(5).setCellValue("");
                }

            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            workbook.write(outputStream);

            workbook.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=sales_report.xlsx")
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(outputStream.toByteArray());

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();

        }

    }

}