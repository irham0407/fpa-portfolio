package com.portfolio.fpa.repository.capexrepo;

import com.portfolio.fpa.model.capexmodel.CapexActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CapexActualRepository extends JpaRepository<CapexActual, Long> {

    // Menemukan semua pengeluaran riil di cabang tertentu berdasarkan periode Bulan & Tahun
    List<CapexActual> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan pengeluaran berdasarkan kode kategori tertentu di satu cabang pada tahun berjalan
    List<CapexActual> findByBranchIdAndActualCodeAndPeriodYear(Long branchId, String actualCode, Integer periodYear);

    // Method untuk mencari data berdasarkan actualCode
    Optional<CapexActual> findByActualCode(String actualCode);

    // Contoh deklarasi query method yang ada di masing-masing Repository
    List<CapexActual> findByPeriodYearAndPeriodMonth(Integer periodYear, Integer periodMonth);
}
