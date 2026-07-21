package com.portfolio.fpa.service.branchser;

import com.portfolio.fpa.dto.branchdto.BranchRequest;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    // 1. Simpan 1 Branch
    public BranchRequest createBranch(BranchRequest request) {
        if (branchRepository.existsByBranchCode(request.getBranchCode())) {
            return null; // atau lemparkan exception jika duplikat
        }
        Branch branch = Branch.builder()
                .branchCode(request.getBranchCode())
                .branchName(request.getBranchName())
                .location(request.getLocation())
                .build();

        Branch saved = branchRepository.save(branch);
        return mapToRequest(saved);
    }

    // 2. Simpan Banyak Branch (Bulk - Skip Duplikat)
    public List<BranchRequest> createBulkBranches(List<BranchRequest> requests) {
        List<Branch> branchesToSave = new ArrayList<>();

        for (BranchRequest req : requests) {
            if (!branchRepository.existsByBranchCode(req.getBranchCode())) {
                Branch branch = Branch.builder()
                        .branchCode(req.getBranchCode())
                        .branchName(req.getBranchName())
                        .location(req.getLocation())
                        .build();
                branchesToSave.add(branch);
            }
        }

        if (branchesToSave.isEmpty()) {
            return new ArrayList<>();
        }

        List<Branch> savedList = branchRepository.saveAll(branchesToSave);
        return savedList.stream().map(this::mapToRequest).collect(Collectors.toList());
    }

    // 3. Ambil Semua Branch
    public List<BranchRequest> getAllBranches() {
        return branchRepository.findAll().stream()
                .map(this::mapToRequest)
                .collect(Collectors.toList());
    }

    private BranchRequest mapToRequest(Branch branch) {
        return BranchRequest.builder()
                .branchCode(branch.getBranchCode())
                .branchName(branch.getBranchName())
                .location(branch.getLocation())
                .build();
    }
}
