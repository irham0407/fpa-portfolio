package com.portfolio.fpa.repository.opexrepo;

import com.portfolio.fpa.model.opexmodel.OpexActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface OpexActualRepository extends JpaRepository<OpexActual, Long> {

    // Menemukan semua pengeluaran riil di cabang tertentu berdasarkan periode Bulan & Tahun
    List<OpexActual> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan pengeluaran berdasarkan kode kategori tertentu di satu cabang pada tahun berjalan
    List<OpexActual> findByBranchIdAndActualCodeAndPeriodYear(Long branchId, String actualCode, Integer periodYear);

    // Method untuk mencari data berdasarkan actualCode
    Optional<OpexActual> findByActualCode(String actualCode);
}
