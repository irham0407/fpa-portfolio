package com.portfolio.fpa.repository.opexrepo;

import com.portfolio.fpa.model.opexmodel.OpexBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpexBudgetRepository extends JpaRepository<OpexBudget, Long> {

    // Menemukan semua budget di cabang tertentu berdasarkan periode Bulan & Tahun
    List<OpexBudget> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan semua budget di seluruh cabang pada periode Tahun tertentu
    List<OpexBudget> findByPeriodYear(Integer periodYear);

    // Membantu mencegah duplikasi input budget dengan kategori & kode yang sama di cabang & periode yang sama
    boolean existsByBranchIdAndOpexCodeCategoryAndPeriodMonthAndPeriodYear(
            Long branchId, String opexCodeCategory, Integer periodMonth, Integer periodYear
    );
}
