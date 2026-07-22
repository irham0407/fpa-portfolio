package com.portfolio.fpa.controller.opexcon;

import com.portfolio.fpa.service.opexser.OpexBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/opex-budgets")
public class OpexBudgetController {

    @Autowired
    private OpexBudgetService opexBudgetService;

    @PostMapping("/import-excel")
    public ResponseEntity<?> importFromExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File Excel tidak boleh kosong!");
        }

        try {
            int count = opexBudgetService.importExcelData(file);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Berhasil mengimpor data OPEX Budget dari Excel!",
                            "total_imported", count
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
