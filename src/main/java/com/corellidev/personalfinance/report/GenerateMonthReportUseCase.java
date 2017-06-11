package com.corellidev.personalfinance.report;

import com.corellidev.personalfinance.ExpenseRepository;
import com.corellidev.personalfinance.model.Expense;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kamil on 2017-05-03.
 */
@Component
public class GenerateMonthReportUseCase {

    public static final long MONTH_IN_MS = 2592000000L;

    @Autowired
    private ExpenseRepository expenseRepository;

    public GenerateMonthReportUseCase() {
    }

    public String getReport(long timestamp) {
        long monthAgoTimestamp = timestamp - MONTH_IN_MS;
        List<Expense> lastMonthExpenses = expenseRepository.findByTimeGreaterThan(monthAgoTimestamp);
        Map<String, Double> categoriesSum = new HashMap<>();

        for (Expense lastMonthExpense : lastMonthExpenses) {
            double value = lastMonthExpense.getValue();
            String category = lastMonthExpense.getCategory();
            if(categoriesSum.containsKey(category)) {
                categoriesSum.put(category, categoriesSum.get(category) + value);
            } else {
                categoriesSum.put(category, value);
            }
            }

        String reportText = getReportText(timestamp, categoriesSum);
        return reportText;
    }
    private String getReportText(long timestamp, Map<String, Double> categoriesSum) {
        double overallSum = categoriesSum.values().stream().mapToDouble(Double::doubleValue).sum();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("REPORT ").append(fmt.print(timestamp - MONTH_IN_MS)).append(" : ").append(fmt.print(timestamp)).append("<br>");
        sb.append("----------------------------------------------------------------------<br>");
        sb.append("Overall =                               ").append(overallSum).append("<br>");
        sb.append("----------------------------------------------------------------------<br>");
        for (String categoryName : categoriesSum.keySet()) {
            double categorySumValue = categoriesSum.get(categoryName);
            sb.append(categoryName + "                                  ").append(categorySumValue).append("<br>");
        }
        return sb.toString();
    }
}
