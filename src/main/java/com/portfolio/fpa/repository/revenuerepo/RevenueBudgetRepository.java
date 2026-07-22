package com.portfolio.fpa.repository.revenuerepo;

import com.portfolio.fpa.model.revenuemodel.RevenueBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevenueBudgetRepository extends JpaRepository<RevenueBudget, Long> {

    // Menemukan semua budget di cabang tertentu berdasarkan periode Bulan & Tahun
    List<RevenueBudget> findByBranchIdAndPeriodMonthAndPeriodYear(Long branchId, Integer periodMonth, Integer periodYear);

    // Menemukan semua budget di seluruh cabang pada periode Tahun tertentu
    List<RevenueBudget> findByPeriodYear(Integer periodYear);

    // Membantu mencegah duplikasi input budget dengan kategori & kode yang sama di cabang & periode yang sama
    boolean existsByBranchIdAndBudgetCodeAndPeriodMonthAndPeriodYear(
            Long branchId, String budgetCode, Integer periodMonth, Integer periodYear
    );

    // Contoh deklarasi query method yang ada di masing-masing Repository
    List<RevenueBudget> findByPeriodYearAndPeriodMonth(Integer periodYear, Integer periodMonth);
}
