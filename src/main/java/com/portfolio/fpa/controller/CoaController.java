package com.portfolio.fpa.controller;

import com.portfolio.fpa.dto.coadto.CoaRequest;
import com.portfolio.fpa.service.coaser.CoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coas")
public class CoaController {

    @Autowired
    private CoaService coaService;

    // Endpoint untuk tambah 1 COA
    @PostMapping
    public ResponseEntity<CoaRequest> createCoa(@RequestBody CoaRequest coaDto) {
        CoaRequest createdCoa = coaService.createCoa(coaDto);
        return new ResponseEntity<>(createdCoa, HttpStatus.CREATED);
    }

    // Endpoint untuk tambah banyak COA sekaligus (Bulk)
    @PostMapping("/bulk")
    public ResponseEntity<List<CoaRequest>> createBulkCoas(@RequestBody List<CoaRequest> coaDtos) {
        List<CoaRequest> createdCoas = coaService.createBulkCoas(coaDtos);
        return new ResponseEntity<>(createdCoas, HttpStatus.CREATED);
    }

    // Endpoint untuk ambil semua COA
    @GetMapping
    public ResponseEntity<List<CoaRequest>> getAllCoas() {
        List<CoaRequest> coas = coaService.getAllCoas();
        return ResponseEntity.ok(coas);
    }
}
