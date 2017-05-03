package com.corellidev.personalfinance.report;

import com.corellidev.personalfinance.ExpenseRepository;
import com.corellidev.personalfinance.model.ExpenseModel;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    // TODO: 2017-05-03 allow using custom categories
    public String getReport(long timestamp) {
        long monthAgoTimestamp = timestamp - MONTH_IN_MS;
        List<ExpenseModel> lastMonthExpenses = expenseRepository.findByTimestampGreaterThan(monthAgoTimestamp);
        double overallSum = 0;
        double foodSum = 0;
        double billsSum = 0;
        double clothesSum = 0;
        double entertainmentSum = 0;
        double uncategorizedSum = 0;

        for (ExpenseModel lastMonthExpense : lastMonthExpenses) {
            double value = lastMonthExpense.getValue();
            overallSum += value;
            switch (lastMonthExpense.getCategory()) {
                case ExpenseModel.FOOD_CATEGORY:
                    foodSum += value;
                    break;
                case ExpenseModel.BILLS_CATEGORY:
                    billsSum += value;
                    break;
                case ExpenseModel.CLOTHES_CATEGORY:
                    clothesSum += value;
                    break;
                case ExpenseModel.ENTERTAINMENT_CATEGORY:
                    entertainmentSum += value;
                    break;
                case ExpenseModel.NONE_CATEGORY:
                default:
                    uncategorizedSum += value;
                    break;
            }
        }

        String reportText = getReportText(timestamp, overallSum, foodSum, billsSum, clothesSum, entertainmentSum, uncategorizedSum);
        return reportText;
    }

    private String getReportText(long timestamp, double overallSum, double foodSum, double billsSum, double clothesSum, double entertainmentSum, double uncategorizedSum) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MM-yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append("REPORT ").append(fmt.print(timestamp - MONTH_IN_MS)).append(" : ").append(fmt.print(timestamp)).append("<br>");
        ;
        sb.append("----------------------------------------------------------------------<br>");
        sb.append("Overall =                               ").append(overallSum).append("<br>");
        sb.append("----------------------------------------------------------------------<br>");
        sb.append("Food =                                  ").append(foodSum).append("<br>");
        sb.append("Bills =                                 ").append(billsSum).append("<br>");
        sb.append("Clothes =                               ").append(clothesSum).append("<br>");
        sb.append("Entertainment =                         ").append(entertainmentSum).append("<br>");
        sb.append("Other =                                 ").append(uncategorizedSum).append("<br>");

        return sb.toString();
    }
}
