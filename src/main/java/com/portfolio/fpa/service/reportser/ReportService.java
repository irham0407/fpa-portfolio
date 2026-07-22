package com.portfolio.fpa.service.reportser;

import com.portfolio.fpa.dto.reportdto.FinancialSummaryResponse;
import com.portfolio.fpa.dto.reportdto.FinancialVarianceDTO;
import com.portfolio.fpa.model.capexmodel.CapexActual;
import com.portfolio.fpa.model.capexmodel.CapexBudget;
import com.portfolio.fpa.model.opexmodel.OpexActual;
import com.portfolio.fpa.model.opexmodel.OpexBudget;
import com.portfolio.fpa.model.revenuemodel.RevenueActual;
import com.portfolio.fpa.model.revenuemodel.RevenueBudget;
import com.portfolio.fpa.repository.capexrepo.CapexActualRepository;
import com.portfolio.fpa.repository.capexrepo.CapexBudgetRepository;
import com.portfolio.fpa.repository.opexrepo.OpexActualRepository;
import com.portfolio.fpa.repository.opexrepo.OpexBudgetRepository;
import com.portfolio.fpa.repository.revenuerepo.RevenueActualRepository;
import com.portfolio.fpa.repository.revenuerepo.RevenueBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private OpexBudgetRepository opexBudgetRepository;
    @Autowired
    private OpexActualRepository opexActualRepository;

    @Autowired
    private CapexBudgetRepository capexBudgetRepository;
    @Autowired
    private CapexActualRepository capexActualRepository;

    @Autowired
    private RevenueBudgetRepository revenueBudgetRepository;
    @Autowired
    private RevenueActualRepository revenueActualRepository;

    public FinancialSummaryResponse getMonthlySummary(Integer periodYear, Integer periodMonth) {
        List<FinancialVarianceDTO> details = new ArrayList<>();

        // 1. Process OPEX
        List<OpexBudget> opexBudgets = opexBudgetRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);
        List<OpexActual> opexActuals = opexActualRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);

        BigDecimal totalOpexBudget = opexBudgets.stream()
                .map(OpexBudget::getBudgetAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalOpexActual = opexActuals.stream()
                .map(OpexActual::getActualAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        details.addAll(processOpexDetails("OPEX", opexBudgets, opexActuals, periodYear, periodMonth));

        // 2. Process CAPEX
        List<CapexBudget> capexBudgets = capexBudgetRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);
        List<CapexActual> capexActuals = capexActualRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);

        BigDecimal totalCapexBudget = capexBudgets.stream()
                .map(CapexBudget::getBudgetAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalCapexActual = capexActuals.stream()
                .map(CapexActual::getActualAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        details.addAll(processCapexDetails("CAPEX", capexBudgets, capexActuals, periodYear, periodMonth));

        // 3. Process REVENUE
        List<RevenueBudget> revenueBudgets = revenueBudgetRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);
        List<RevenueActual> revenueActuals = revenueActualRepository.findByPeriodYearAndPeriodMonth(periodYear, periodMonth);

        BigDecimal totalRevenueBudget = revenueBudgets.stream()
                .map(RevenueBudget::getBudgetAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalRevenueActual = revenueActuals.stream()
                .map(RevenueActual::getActualAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        details.addAll(processRevenueDetails("REVENUE", revenueBudgets, revenueActuals, periodYear, periodMonth));

        return FinancialSummaryResponse.builder()
                .periodMonth(periodMonth)
                .periodYear(periodYear)
                .totalOpexBudget(totalOpexBudget)
                .totalOpexActual(totalOpexActual)
                .totalOpexVariance(totalOpexActual.subtract(totalOpexBudget))
                .totalCapexBudget(totalCapexBudget)
                .totalCapexActual(totalCapexActual)
                .totalCapexVariance(totalCapexActual.subtract(totalCapexBudget))
                .totalRevenueBudget(totalRevenueBudget)
                .totalRevenueActual(totalRevenueActual)
                .totalRevenueVariance(totalRevenueActual.subtract(totalRevenueBudget))
                .details(details)
                .build();
    }

    private List<FinancialVarianceDTO> processOpexDetails(String category, List<OpexBudget> budgets, List<OpexActual> actuals, Integer year, Integer month) {
        List<FinancialVarianceDTO> list = new ArrayList<>();
        Map<String, BigDecimal> actualMap = new HashMap<>();

        for (OpexActual act : actuals) {
            String key = act.getBranch().getId() + "-" + act.getCoa().getId();
            actualMap.put(key, actualMap.getOrDefault(key, BigDecimal.ZERO).add(act.getActualAmount()));
        }

        for (OpexBudget bgt : budgets) {
            String key = bgt.getBranch().getId() + "-" + bgt.getCoa().getId();
            BigDecimal actualAmt = actualMap.getOrDefault(key, BigDecimal.ZERO);
            BigDecimal budgetAmt = bgt.getBudgetAmount();
            BigDecimal variance = actualAmt.subtract(budgetAmt);

            list.add(FinancialVarianceDTO.builder()
                    .category(category)
                    .branchId(bgt.getBranch().getId())
                    .branchCode(bgt.getBranch().getBranchCode())
                    .branchName(bgt.getBranch().getBranchName())
                    .coaId(bgt.getCoa().getId())
                    .coaCode(bgt.getCoa().getCoaCode())
                    .coaName(bgt.getCoa().getCoaName())
                    .periodMonth(month)
                    .periodYear(year)
                    .budgetAmount(budgetAmt)
                    .actualAmount(actualAmt)
                    .variance(variance)
                    .variancePercentage(calculatePercentage(actualAmt, budgetAmt))
                    .build());
        }
        return list;
    }

    private List<FinancialVarianceDTO> processCapexDetails(String category, List<CapexBudget> budgets, List<CapexActual> actuals, Integer year, Integer month) {
        List<FinancialVarianceDTO> list = new ArrayList<>();
        Map<String, BigDecimal> actualMap = new HashMap<>();

        for (CapexActual act : actuals) {
            String key = act.getBranch().getId() + "-" + act.getCoa().getId();
            actualMap.put(key, actualMap.getOrDefault(key, BigDecimal.ZERO).add(act.getActualAmount()));
        }

        for (CapexBudget bgt : budgets) {
            String key = bgt.getBranch().getId() + "-" + bgt.getCoa().getId();
            BigDecimal actualAmt = actualMap.getOrDefault(key, BigDecimal.ZERO);
            BigDecimal budgetAmt = bgt.getBudgetAmount();
            BigDecimal variance = actualAmt.subtract(budgetAmt);

            list.add(FinancialVarianceDTO.builder()
                    .category(category)
                    .branchId(bgt.getBranch().getId())
                    .branchCode(bgt.getBranch().getBranchCode())
                    .branchName(bgt.getBranch().getBranchName())
                    .coaId(bgt.getCoa().getId())
                    .coaCode(bgt.getCoa().getCoaCode())
                    .coaName(bgt.getCoa().getCoaName())
                    .periodMonth(month)
                    .periodYear(year)
                    .budgetAmount(budgetAmt)
                    .actualAmount(actualAmt)
                    .variance(variance)
                    .variancePercentage(calculatePercentage(actualAmt, budgetAmt))
                    .build());
        }
        return list;
    }

    private List<FinancialVarianceDTO> processRevenueDetails(String category, List<RevenueBudget> budgets, List<RevenueActual> actuals, Integer year, Integer month) {
        List<FinancialVarianceDTO> list = new ArrayList<>();
        Map<String, BigDecimal> actualMap = new HashMap<>();

        for (RevenueActual act : actuals) {
            String key = act.getBranch().getId() + "-" + act.getCoa().getId();
            actualMap.put(key, actualMap.getOrDefault(key, BigDecimal.ZERO).add(act.getActualAmount()));
        }

        for (RevenueBudget bgt : budgets) {
            String key = bgt.getBranch().getId() + "-" + bgt.getCoa().getId();
            BigDecimal actualAmt = actualMap.getOrDefault(key, BigDecimal.ZERO);
            BigDecimal budgetAmt = bgt.getBudgetAmount();
            BigDecimal variance = actualAmt.subtract(budgetAmt);

            list.add(FinancialVarianceDTO.builder()
                    .category(category)
                    .branchId(bgt.getBranch().getId())
                    .branchCode(bgt.getBranch().getBranchCode())
                    .branchName(bgt.getBranch().getBranchName())
                    .coaId(bgt.getCoa().getId())
                    .coaCode(bgt.getCoa().getCoaCode())
                    .coaName(bgt.getCoa().getCoaName())
                    .periodMonth(month)
                    .periodYear(year)
                    .budgetAmount(budgetAmt)
                    .actualAmount(actualAmt)
                    .variance(variance)
                    .variancePercentage(calculatePercentage(actualAmt, budgetAmt))
                    .build());
        }
        return list;
    }

    private Double calculatePercentage(BigDecimal actual, BigDecimal budget) {
        if (budget == null || budget.compareTo(BigDecimal.ZERO) == 0) {
            return 0.0;
        }
        BigDecimal variance = actual.subtract(budget);
        return variance.divide(budget, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();
    }
}
