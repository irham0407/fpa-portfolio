package com.portfolio.fpa.service.opexser;

import com.portfolio.fpa.model.Branch;
import com.portfolio.fpa.model.Coa;
import com.portfolio.fpa.model.opexmodel.OpexActual;
import com.portfolio.fpa.repository.BranchRepository;
import com.portfolio.fpa.repository.CoaRepository;
import com.portfolio.fpa.repository.opexrepo.OpexActualRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpexActualService {

    @Autowired
    private OpexActualRepository opexActualRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private CoaRepository coaRepository;

    @Transactional
    public int importExcelData(MultipartFile file) throws Exception {
        List<OpexActual> listToSave = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String actualCode = row.getCell(0).getStringCellValue();
                BigDecimal amount = BigDecimal.valueOf(row.getCell(1).getNumericCellValue());
                int periodMonth = (int) row.getCell(2).getNumericCellValue();
                int periodYear = (int) row.getCell(3).getNumericCellValue();

                Cell dateCell = row.getCell(4);
                LocalDate transDate = dateCell.getCellType() == CellType.NUMERIC
                        ? dateCell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        : LocalDate.parse(dateCell.getStringCellValue());

                String description = row.getCell(5).getStringCellValue();
                Long branchId = (long) row.getCell(6).getNumericCellValue();
                Long coaId = (long) row.getCell(7).getNumericCellValue();

                Branch branch = branchRepository.findById(branchId)
                        .orElseThrow(() -> new RuntimeException("Branch ID " + branchId + " tidak ditemukan!"));
                Coa coa = coaRepository.findById(coaId)
                        .orElseThrow(() -> new RuntimeException("COA ID " + coaId + " tidak ditemukan!"));

                // 1. Cek apakah actual_code sudah ada di database
                OpexActual actual = opexActualRepository.findByActualCode(actualCode)
                        .orElse(new OpexActual()); // Jika ada, ambil data lama; jika tidak, buat objek baru

                // 2. Set/Update nilai field dari Excel
                actual.setActualCode(actualCode);
                actual.setActualAmount(amount);
                actual.setPeriodMonth(periodMonth);
                actual.setPeriodYear(periodYear);
                actual.setTransactionDate(transDate);
                actual.setDescription(description);
                actual.setBranch(branch);
                actual.setCoa(coa);

                listToSave.add(actual);
            }
        }

        // JPA akan otomatis melakukan UPDATE untuk data yang sudah ada (memiliki ID)
        // dan INSERT untuk data baru
        opexActualRepository.saveAll(listToSave);
        return listToSave.size();
    }
}
