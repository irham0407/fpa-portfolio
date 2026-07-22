package com.portfolio.fpa.controller;

import com.portfolio.fpa.dto.reportdto.FinancialSummaryResponse;
import com.portfolio.fpa.service.reportser.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/summary")
    public ResponseEntity<FinancialSummaryResponse> getMonthlySummary(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {

        FinancialSummaryResponse response = reportService.getMonthlySummary(year, month);
        return ResponseEntity.ok(response);
    }
}
