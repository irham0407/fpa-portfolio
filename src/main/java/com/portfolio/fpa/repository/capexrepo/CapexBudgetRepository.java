package com.portfolio.fpa.repository.capexrepo;

import com.portfolio.fpa.model.capexmodel.CapexBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CapexBudgetRepository extends JpaRepository<CapexBudget, Long> {

    // Menemukan semua budget di cabang tertentu berdasarkan periode Bulan & Tahun
    List<CapexBudget> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan semua budget di seluruh cabang pada periode Tahun tertentu
    List<CapexBudget> findByPeriodYear(Integer periodYear);

    // Membantu mencegah duplikasi input budget dengan kategori & kode yang sama di cabang & periode yang sama
    boolean existsByBranchIdAndBudgetCodeAndPeriodMonthAndPeriodYear(
            Long branchId, String budgetCode, Integer periodMonth, Integer periodYear
    );
}
