package com.portfolio.fpa.controller.revenuecon;

import com.portfolio.fpa.dto.revenucedto.RevenueBudgetRequest;
import com.portfolio.fpa.dto.revenucedto.RevenueBudgetResponse;
import com.portfolio.fpa.service.revenueser.RevenueBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/revenue/budget")
public class RevenueBudgetController {

    @Autowired
    private RevenueBudgetService budgetService;

    @PostMapping
    public ResponseEntity<RevenueBudgetResponse> createBudget(@RequestBody RevenueBudgetRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetService.createBudget(request));
    }

    @GetMapping
    public ResponseEntity<List<RevenueBudgetResponse>> getAllBudgets() {
        return ResponseEntity.ok(budgetService.getAllBudgets());
    }

    @PostMapping("/import-excel")
    public ResponseEntity<?> importFromExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "File Excel tidak boleh kosong!"));
        }
        try {
            int count = budgetService.importExcelData(file);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Berhasil mengimpor data Revenue Budget dari Excel!",
                            "total_imported", count
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
