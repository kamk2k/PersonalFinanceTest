package com.corellidev.personalfinance.report;

import com.corellidev.personalfinance.TokenVerifier;
import com.corellidev.personalfinance.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by Kamil on 2017-05-03.
 */
@RestController
public class GetReportController {

    @Autowired
    private GenerateMonthReportUseCase generateMonthReportUseCase;
    @Autowired
    private TokenVerifier tokenVerifier;

    @RequestMapping("/report")
    public String generateReportForCurrentMonth() {
        return generateMonthReportUseCase.getReport(new Date().getTime());
    }

    @RequestMapping("/all")
    public List<Expense> getAllExpenses(@RequestHeader(value = "Google-Token", required = false) String token) {
        System.out.println("token = " + tokenVerifier.verifyAndGetEmail(token));
        return generateMonthReportUseCase.getAllExpenses();
    }

}
