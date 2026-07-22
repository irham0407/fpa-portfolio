package com.portfolio.fpa.service.capexser;

import com.portfolio.fpa.dto.capexdto.CapexBudgetRequest;
import com.portfolio.fpa.dto.capexdto.CapexBudgetResponse;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.model.capexmodel.CapexBudget;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.CoaRepository;
import com.portfolio.fpa.repository.capexrepo.CapexBudgetRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CapexBudgetService {

    @Autowired
    private CapexBudgetRepository capexBudgetRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CoaRepository coaRepository;

    @Transactional
    public CapexBudgetResponse createBudget(CapexBudgetRequest request) {
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch tidak ditemukan ID: " + request.getBranchId()));
        Coa coa = coaRepository.findById(request.getCoaId())
                .orElseThrow(() -> new RuntimeException("COA tidak ditemukan ID: " + request.getCoaId()));

        CapexBudget budget = CapexBudget.builder()
                .branch(branch)
                .coa(coa)
                .budgetAmount(request.getBudgetAmount())
                .periodMonth(request.getPeriodMonth())
                .periodYear(request.getPeriodYear())
                .build();

        CapexBudget saved = capexBudgetRepository.save(budget);
        return mapToResponse(saved);
    }

    public List<CapexBudgetResponse> getAllBudgets() {
        return capexBudgetRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public int importExcelData(MultipartFile file) throws Exception {
        List<CapexBudget> list = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Format Excel:
                // [0] Branch ID | [1] COA ID | [2] Budget Amount | [3] Month (1-12) | [4] Year
                Long branchId = (long) row.getCell(0).getNumericCellValue();
                Long coaId = (long) row.getCell(1).getNumericCellValue();

                Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new RuntimeException("Branch tidak ditemukan pada baris " + (i + 1)));
                Coa coa = coaRepository.findById(coaId)
                        .orElseThrow(() -> new RuntimeException("COA tidak ditemukan pada baris " + (i + 1)));

                CapexBudget budget = CapexBudget.builder()
                        .branch(branch)
                        .coa(coa)
                        .budgetAmount(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()))
                        .periodMonth((int) row.getCell(3).getNumericCellValue())
                        .periodYear((int) row.getCell(4).getNumericCellValue())
                        .build();

                list.add(budget);
            }
        }
        return capexBudgetRepository.saveAll(list).size();
    }

    private CapexBudgetResponse mapToResponse(CapexBudget budget) {
        return CapexBudgetResponse.builder()
                .id(budget.getId())
                .budgetCode(budget.getBudgetCode())
                .branchId(budget.getBranch().getId())
                .branchCode(budget.getBranch().getBranchCode())
                .branchName(budget.getBranch().getBranchName())
                .coaId(budget.getCoa().getId())
                .coaCode(budget.getCoa().getCoaCode())
                .coaName(budget.getCoa().getCoaName())
                .budgetAmount(budget.getBudgetAmount())
                .periodMonth(budget.getPeriodMonth())
                .periodYear(budget.getPeriodYear())
                .createdAt(budget.getCreatedAt())
                .build();
    }
}
