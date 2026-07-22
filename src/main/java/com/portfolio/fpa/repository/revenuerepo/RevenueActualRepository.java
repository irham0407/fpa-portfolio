package com.portfolio.fpa.repository.revenuerepo;

import com.portfolio.fpa.model.revenuemodel.RevenueActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueActualRepository  extends JpaRepository<RevenueActual, Long> {

    // Menemukan semua pengeluaran riil di cabang tertentu berdasarkan periode Bulan & Tahun
    List<RevenueActual> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan pengeluaran berdasarkan kode kategori tertentu di satu cabang pada tahun berjalan
    List<RevenueActual> findByBranchIdAndActualCodeAndPeriodYear(Long branchId, String actualCode, Integer periodYear);

    // Method untuk mencari data berdasarkan actualCode
    Optional<RevenueActual> findByActualCode(String actualCode);
}
