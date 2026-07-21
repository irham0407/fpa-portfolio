package com.portfolio.fpa.controller.branchcontroll;

import com.portfolio.fpa.dto.branchdto.BranchRequest;
import com.portfolio.fpa.service.branchser.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
public class BranchController {

    @Autowired
    private BranchService branchService;

    // Endpoint untuk tambah 1 Branch
    @PostMapping
    public ResponseEntity<BranchRequest> createBranch(@RequestBody BranchRequest branchDto) {
        BranchRequest createdBranch = branchService.createBranch(branchDto);
        return new ResponseEntity<>(createdBranch, HttpStatus.CREATED);
    }

    // Endpoint untuk tambah banyak Branch sekaligus (Bulk)
    @PostMapping("/bulk")
    public ResponseEntity<List<BranchRequest>> createBulkBranches(@RequestBody List<BranchRequest> branchDtos) {
        List<BranchRequest> createdBranches = branchService.createBulkBranches(branchDtos);
        return new ResponseEntity<>(createdBranches, HttpStatus.CREATED);
    }

    // Endpoint untuk ambil semua Branch
    @GetMapping
    public ResponseEntity<List<BranchRequest>> getAllBranches() {
        List<BranchRequest> branches = branchService.getAllBranches();
        return ResponseEntity.ok(branches);
    }
}
