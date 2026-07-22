package com.portfolio.fpa.service.opexser;

import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.model.opexmodel.OpexBudget;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.CoaRepository;
import com.portfolio.fpa.repository.opexrepo.OpexBudgetRepository;
import com.portfolio.fpa.util.ExcelHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpexBudgetService {

    @Autowired
    private OpexBudgetRepository opexBudgetRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CoaRepository coaRepository;

    public int importExcelData(MultipartFile file) throws Exception {
        List<OpexBudget> list = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header (baris 0)
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Long branchId = (long) row.getCell(0).getNumericCellValue();
                Long coaId = (long) row.getCell(1).getNumericCellValue();

                Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new RuntimeException("Branch tidak ditemukan untuk ID: " + branchId));

                Coa coa = coaRepository.findById(coaId)
                        .orElseThrow(() -> new RuntimeException("COA tidak ditemukan untuk ID: " + coaId));

                OpexBudget budget = OpexBudget.builder()
                        .branch(branch)
                        .coa(coa)
                        .budgetAmount(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()))
                        .periodMonth((int) row.getCell(3).getNumericCellValue())
                        .periodYear((int) row.getCell(4).getNumericCellValue())
                        .build();

                list.add(budget);
            }
        }

        return opexBudgetRepository.saveAll(list).size();
    }
}
