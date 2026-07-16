package com.portfolio.fpa.repository.opexrepo;

import com.portfolio.fpa.model.opexmodel.OpexActual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OpexActualRepository extends JpaRepository<OpexActual, Long> {

    // Menemukan semua pengeluaran riil di cabang tertentu berdasarkan periode Bulan & Tahun
    List<OpexActual> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan pengeluaran berdasarkan kode kategori tertentu di satu cabang pada tahun berjalan
    List<OpexActual> findByBranchIdAndOpexCodeCategoryAndPeriodYear(Long branchId, String opexCodeCategory, Integer periodYear);
}
