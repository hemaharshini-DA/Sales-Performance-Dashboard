package com.example.demo.controller;

import com.example.demo.entity.Sale;
import com.example.demo.repository.SaleRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
public class PdfController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPDF() {

        try {

            List<Sale> sales = saleRepository.findAll();

            Document document = new Document();

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

            document.add(new Paragraph("Sales Performance Report", titleFont));

            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(6);

            table.addCell(new Phrase("ID"));
            table.addCell(new Phrase("Product"));
            table.addCell(new Phrase("Sales"));
            table.addCell(new Phrase("Profit"));
            table.addCell(new Phrase("Region"));
            table.addCell(new Phrase("Sale Date"));

            for (Sale sale : sales) {

                table.addCell(String.valueOf(sale.getId()));
                table.addCell(sale.getProductName());
                table.addCell(String.valueOf(sale.getSales()));
                table.addCell(String.valueOf(sale.getProfit()));
                table.addCell(sale.getRegion());

                if (sale.getSaleDate() != null) {
                    table.addCell(sale.getSaleDate().toString());
                } else {
                    table.addCell("");
                }

            }

            document.add(table);

            document.close();

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=sales_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(out.toByteArray());

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.internalServerError().build();

        }

    }

}