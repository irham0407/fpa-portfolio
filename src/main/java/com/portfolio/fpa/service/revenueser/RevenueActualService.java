package com.portfolio.fpa.service.revenueser;

import com.portfolio.fpa.dto.revenucedto.RevenueActualRequest;
import com.portfolio.fpa.dto.revenucedto.RevenueActualResponse;
import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.model.revenuemodel.RevenueActual;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.CoaRepository;
import com.portfolio.fpa.repository.revenuerepo.RevenueActualRepository;
import com.portfolio.fpa.util.ExcelHelper;
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
public class RevenueActualService {

    @Autowired
    private RevenueActualRepository revenueActualRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CoaRepository coaRepository;

    @Transactional
    public RevenueActualResponse createActual(RevenueActualRequest request) {
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch tidak ditemukan ID: " + request.getBranchId()));
        Coa coa = coaRepository.findById(request.getCoaId())
                .orElseThrow(() -> new RuntimeException("COA tidak ditemukan ID: " + request.getCoaId()));

        RevenueActual revenueActual = RevenueActual.builder()
                .branch(branch)
                .coa(coa)
                .actualAmount(request.getActualAmount())
                .description(request.getDescription())
                .periodMonth(request.getPeriodMonth())
                .periodYear(request.getPeriodYear())
                .transactionDate(request.getTransactionDate())
                .build();

        RevenueActual saved = revenueActualRepository.save(revenueActual);
        return mapToResponse(saved);
    }

    public List<RevenueActualResponse> getAllActuals() {
        return revenueActualRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public int importExcelData(MultipartFile file) throws Exception {
        List<RevenueActual> list = new ArrayList<>();

        try (InputStream is = file.getInputStream(); Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) { // Skip header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                // Format Excel:
                // [0] Branch ID | [1] COA ID | [2] Nominal | [3] Deskripsi | [4] Bulan | [5] Tahun | [6] Tgl Transaksi
                Long branchId = (long) row.getCell(0).getNumericCellValue();
                Long coaId = (long) row.getCell(1).getNumericCellValue();

                Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new RuntimeException("Branch tidak ditemukan pada baris " + (i + 1)));
                Coa coa = coaRepository.findById(coaId)
                        .orElseThrow(() -> new RuntimeException("COA tidak ditemukan pada baris " + (i + 1)));

                RevenueActual actual = RevenueActual.builder()
                        .branch(branch)
                        .coa(coa)
                        .actualAmount(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()))
                        .description(ExcelHelper.getCellValueAsString(row.getCell(3)))
                        .periodMonth((int) row.getCell(4).getNumericCellValue())
                        .periodYear((int) row.getCell(5).getNumericCellValue())
                        .transactionDate(row.getCell(6).getLocalDateTimeCellValue().toInstant(java.time.ZoneOffset.UTC))
                        .build();

                list.add(actual);
            }
        }
        return revenueActualRepository.saveAll(list).size();
    }

    private RevenueActualResponse mapToResponse(RevenueActual actual) {
        return RevenueActualResponse.builder()
                .id(actual.getId())
                .actualCode(actual.getActualCode())
                .branchId(actual.getBranch().getId())
                .branchCode(actual.getBranch().getBranchCode())
                .branchName(actual.getBranch().getBranchName())
                .coaId(actual.getCoa().getId())
                .coaCode(actual.getCoa().getCoaCode())
                .coaName(actual.getCoa().getCoaName())
                .actualAmount(actual.getActualAmount())
                .description(actual.getDescription())
                .periodMonth(actual.getPeriodMonth())
                .periodYear(actual.getPeriodYear())
                .transactionDate(actual.getTransactionDate())
                .createdAt(actual.getCreatedAt())
                .build();
    }
}
