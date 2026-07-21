package com.portfolio.fpa.service.branchser;

import com.portfolio.fpa.dto.branchdto.BranchRequest;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    // Simpan 1 Branch
    public BranchRequest createBranch(BranchRequest request) {
        Branch branch = Branch.builder()
                .branchCode(request.getBranchCode())
                .branchName(request.getBranchName())
                .location(request.getLocation())
                .build();

        Branch saved = branchRepository.save(branch);
        return mapToRequest(saved);
    }

    // Simpan Banyak Branch Sekaligus (Bulk)
    public List<BranchRequest> createBulkBranches(List<BranchRequest> requests) {
        List<Branch> branches = requests.stream()
                .map(req -> Branch.builder()
                        .branchCode(req.getBranchCode())
                        .branchName(req.getBranchName())
                        .location(req.getLocation())
                        .build())
                .collect(Collectors.toList());

        List<Branch> savedList = branchRepository.saveAll(branches);
        return savedList.stream().map(this::mapToRequest).collect(Collectors.toList());
    }

    // Ambil Semua Branch
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
