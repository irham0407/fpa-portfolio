package com.portfolio.fpa.controller.capexcon;

import com.portfolio.fpa.dto.capexdto.CapexActualRequest;
import com.portfolio.fpa.dto.capexdto.CapexActualResponse;
import com.portfolio.fpa.service.capexser.CapexActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/capex/actual")
public class CapexActualController {

    @Autowired
    private CapexActualService actualService;

    @PostMapping
    public ResponseEntity<CapexActualResponse> createActual(@RequestBody CapexActualRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actualService.createActual(request));
    }

    @GetMapping
    public ResponseEntity<List<CapexActualResponse>> getAllActuals() {
        return ResponseEntity.ok(actualService.getAllActuals());
    }

    @PostMapping("/import-excel")
    public ResponseEntity<?> importFromExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "File Excel tidak boleh kosong!"));
        }
        try {
            int count = actualService.importExcelData(file);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                            "message", "Berhasil mengimpor data CAPEX Actual dari Excel!",
                            "total_imported", count
                    ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
